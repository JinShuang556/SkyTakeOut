package com.qrs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qrs.dto.SetmealPageDTO;
import com.qrs.dto.SetmealWithSetmealDishDTO;
import com.qrs.entity.Setmeal;
import com.qrs.entity.SetmealDish;
import com.qrs.mapper.SetmealDishMapper;
import com.qrs.mapper.SetmealMapper;
import com.qrs.service.SetmealService;
import com.qrs.vo.DishItemVO;
import com.qrs.vo.PageVO;
import com.qrs.vo.SetmealPageVO;
import com.qrs.vo.SetmealWithSetmealDishVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SetmealServiceImpl implements SetmealService {

    private final SetmealMapper setmealMapper;
    private final SetmealDishMapper setmealDishMapper;
    private final CacheManager cacheManager;

    @Override
    public PageVO page(SetmealPageDTO setmealPageDTO) {
        PageHelper.startPage(setmealPageDTO.getPage(), setmealPageDTO.getPageSize());
        Page<SetmealPageVO> page = setmealMapper.page(setmealPageDTO);
        return new PageVO(page.getTotal(), page.getResult());
    }

    @CacheEvict(cacheNames = "UserSetmeal",key = "'list:'+#setmealWithSetmealDishDTO.categoryId")
    @Transactional
    @Override
    public void addSetmealWithSetmealDish(SetmealWithSetmealDishDTO setmealWithSetmealDishDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealWithSetmealDishDTO, setmeal);
        log.info("新增套餐：{}", setmeal);
        setmealMapper.insert(setmeal);
        List<SetmealDish> setmealDishes = setmealWithSetmealDishDTO.getSetmealDishes();
        if(setmealDishes==null||setmealDishes.isEmpty()){
            throw new RuntimeException("套餐菜品不能为空");
        }
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmeal.getId());
        }
        log.info("套餐关联的菜品：{}", setmealDishes);
        setmealDishMapper.insertBatch(setmealDishes);
        log.info("新增套餐成功");
    }

    @Transactional
    @Override
    public void deleteSetmealWithSetmealDish(List<Long> ids) {
        //1.判断套餐是否起售
        log.info("检查套餐是否起售...");
        List<Setmeal> setmeals = setmealMapper.getSetmealByIds(ids);
        Set<Long> categoryIds = new HashSet<>();
        for (Setmeal setmeal : setmeals) {
            if(setmeal.getStatus() == 1){
                throw new RuntimeException("套餐已起售，无法删除");
            }
            categoryIds.add(setmeal.getCategoryId());
        }
        //2.删除套餐关联的菜品
        setmealMapper.deleteBatch(ids);
        log.info("正在删除套餐关联的菜品...");
        //3.删除套餐
        setmealDishMapper.deleteSetmealDishBySetmealIds(ids);
        log.info("删除套餐成功");
        //4.删除套餐分类缓存：
        clearSetmealListCache(categoryIds);
    }

    private void clearSetmealListCache(Set<Long> categoryIds) {
        Cache cache = cacheManager.getCache("UserSetmeal");
        if(cache == null){
            log.info("UserSetmeal::list缓存不存在");
            return;
        }
        for (Long categoryId : categoryIds) {
            cache.evict("list:" + categoryId);
        }
    }

    @Override
    public SetmealWithSetmealDishVO selectSetmealWithSetmealDishById(Long id) {
        return setmealMapper.getSetmealWithSetmealDishById(id);
    }

    @Transactional
    @Override
    public void updateSetmealWithSetmealDish(SetmealWithSetmealDishDTO setmealWithSetmealDishDTO) {
        //先获得原来的套餐分类id,为后面的删除缓存做准备
        Set<Long> categoryIds = new HashSet<>();
        Setmeal oldsetmeal = setmealMapper.getSetmealById(setmealWithSetmealDishDTO.getId());
        categoryIds.add(oldsetmeal.getCategoryId());
        //更新套餐信息：
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealWithSetmealDishDTO, setmeal);
        setmealMapper.updateSetmealById(setmeal);
        log.info("正在删除原来套餐关联的菜品...");
        //根据套餐id获得原来的菜品：
        Long id = setmealWithSetmealDishDTO.getId();
        List<SetmealDish> oldSetmealDishes = setmealDishMapper.selectSetmealDishesBySetmealId(id);
        if(oldSetmealDishes == null || oldSetmealDishes.isEmpty()){
            log.info("旧套餐关联的菜品为空,无需删除");
        }else {
            List<Long> setmealIds = new ArrayList<>();
            //获得旧套餐关联的菜品id
            oldSetmealDishes.forEach(setmealDish -> setmealIds.add(setmealDish.getSetmealId()));
            setmealDishMapper.deleteSetmealDishBySetmealIds(setmealIds);
        }
        //获取套餐修改后的菜品：
        List<SetmealDish> newSetmealDishes = setmealWithSetmealDishDTO.getSetmealDishes();
        if(newSetmealDishes == null || newSetmealDishes.isEmpty()){
            throw new RuntimeException("套餐菜品不能为空");
        }else{
            //设置套餐id：
            for (SetmealDish setmealDish : newSetmealDishes) {
                setmealDish.setSetmealId(id);
            }
            //插入新的套餐菜品：
            setmealDishMapper.insertBatch(newSetmealDishes);
        }
        log.info("更新套餐成功");
        //删除套餐分类缓存：
        log.info("正在删除套餐分类缓存：UserSetmeal::list:{}", setmeal.getCategoryId());
        categoryIds.add(setmeal.getCategoryId());
        //获得修改后的套餐分类id
        categoryIds.add(setmealWithSetmealDishDTO.getCategoryId());
        clearSetmealListCache(categoryIds);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Setmeal setmeal = new Setmeal();
        setmeal.setId(id);
        setmeal.setStatus(status);
        setmealMapper.updateSetmealById(setmeal);
        log.info("套餐状态修改成功，当前状态：{}", status);
        //删除套餐分类缓存：
        setmeal = setmealMapper.getSetmealById(id);
        Set<Long> categoryIds = new HashSet<>();
        categoryIds.add(setmeal.getCategoryId());
        clearSetmealListCache(categoryIds);
    }

    @Cacheable(cacheNames = "UserSetmeal" , key = "'list:'+#categoryId")
    @Override
    public List<Setmeal> getSetmealsBycategoryId(Long categoryId) {
        return setmealMapper.getSetmealsBycategoryId(categoryId);
    }

//    @Cacheable(cacheNames = "UserSetmeal" , key = "'detail:'+#id")
    @Override
    public List<DishItemVO> selectDishesById(Long id) {
        return setmealMapper.getDishItemsById(id);
    }


}
