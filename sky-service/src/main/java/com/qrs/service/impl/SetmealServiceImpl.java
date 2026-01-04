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
import com.qrs.vo.PageVO;
import com.qrs.vo.SetmealPageVO;
import com.qrs.vo.SetmealWithSetmealDishVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SetmealServiceImpl implements SetmealService {

    private final SetmealMapper setmealMapper;
    private final SetmealDishMapper setmealDishMapper;

    @Override
    public PageVO page(SetmealPageDTO setmealPageDTO) {
        PageHelper.startPage(setmealPageDTO.getPage(), setmealPageDTO.getPageSize());
        Page<SetmealPageVO> page = setmealMapper.page(setmealPageDTO);
        return new PageVO(page.getTotal(), page.getResult());
    }

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
        log.info("新增套餐菜品：{}", setmealDishes);
        setmealDishMapper.insertBatch(setmealDishes);
        log.info("新增套餐成功");
    }

    @Transactional
    @Override
    public void deleteSetmealWithSetmealDish(List<Long> ids) {
        setmealMapper.deleteBatch(ids);
        setmealDishMapper.deleteSetmealDishBySetmealIds(ids);
    }

    @Override
    public SetmealWithSetmealDishVO selectSetmealWithSetmealDishById(Long id) {
        return setmealMapper.selectSetmealWithSetmealDishById(id);
    }

    @Transactional
    @Override
    public void updateSetmealWithSetmealDish(SetmealWithSetmealDishDTO setmealWithSetmealDishDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealWithSetmealDishDTO, setmeal);
        //更新套餐信息：
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
    }
}
