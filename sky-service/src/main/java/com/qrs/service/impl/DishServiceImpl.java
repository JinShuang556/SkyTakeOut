package com.qrs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qrs.dto.DishDTO;
import com.qrs.dto.DishPageDTO;
import com.qrs.dto.DishUpdateDTO;
import com.qrs.entity.Dish;
import com.qrs.entity.DishFlavor;
import com.qrs.mapper.DishFlavorMapper;
import com.qrs.mapper.DishMapper;
import com.qrs.mapper.SetmealDishMapper;
import com.qrs.service.DishService;
import com.qrs.vo.DishPageVO;
import com.qrs.vo.DishWithFlavorVO;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishMapper dishMapper;
    private final DishFlavorMapper dishFlavorMapper;
    private final SetmealDishMapper setMealDishMapper;

    @Override
    public PageVO page(DishPageDTO dishPageDTO) {
        PageHelper.startPage(dishPageDTO.getPage(),dishPageDTO.getPageSize());
        Page<DishPageVO> p = dishMapper.page(dishPageDTO);
        return PageVO.builder()
                .total(p.getTotal())
                .records(p.getResult())
                .build();
    }

    @Transactional
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        //1.添加菜品
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.addDish(dish);
        //2.添加菜品口味
        List<DishFlavor> flavors = dishDTO.getFlavors();

        if (flavors != null && !flavors.isEmpty()) {
            flavors.forEach(flavor -> {
                //获得菜品id
                flavor.setDishId(dish.getId());
                //单个插入：
//                dishFlavorMapper.addDishFlavor(flavor);
            });
            //批量插入：
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Transactional
    @Override
    public void removeWithFlavor(List<Long> ids) {
        //1.判断菜品是否被套餐关联
        log.info("检查菜品是否关联套餐...");
        Integer count = setMealDishMapper.checkDishIdsInSetmealDish(ids);
        if(count >0){
            throw new RuntimeException("有" + count + "个菜品已被套餐关联，无法删除");
        }
        //2.检查菜品是否起售
        log.info("检查菜品是否起售...");
        List<Dish> dishes = dishMapper.selectDishByIds(ids);
        for (Dish dish : dishes) {
            if(dish.getStatus()==1)
                throw new RuntimeException(dish.getName() + "已起售，无法删除");
        }
        //3.删除菜品
        log.info("删除菜品...");
        dishMapper.deleteDishByIds(ids);
        //4.删除菜品口味
        log.info("删除菜品口味...");
        dishFlavorMapper.deleteDishFlavorByDishIds(ids);
    }

    @Override
    public List<Dish> selectDishByCategoryId(Long categoryId) {

        return dishMapper.selectDishByCategoryId(categoryId);
    }

    @Override
    public DishWithFlavorVO selectDishWithFlavorById(Long id) {
        return dishMapper.selectDishWithFlavorById(id);
    }

    @Transactional
    @Override
    public void updateDishWithFlavor(DishUpdateDTO dishUpdateDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishUpdateDTO,dish);
        log.info("正在修改菜品...");
        dishMapper.updateDish(dish);
        log.info("正在修改菜品口味...");
        // 先删除，再插入
        log.info("正在查询菜品是否有口味...");
        Integer i = dishFlavorMapper.checkDishIdInDishFlavor(dish.getId());
        if(i>0){
            log.info("该菜品有{}个口味，正在删除...", i);
            dishFlavorMapper.deleteDishFlavorByDishId(dish.getId());
        }else {
            log.info("菜品口味为空，无需删除");
        }
        log.info("正在插入新的口味...");
        List<DishFlavor> flavors = dishUpdateDTO.getFlavors();
        if(flavors == null || flavors.isEmpty()){
            log.info("修改的菜品口味为空，无需插入");
            return ;
        }
        flavors.forEach(flavor -> {
            flavor.setDishId(dish.getId());
        });
        dishFlavorMapper.insertBatch(flavors);
        log.info("修改成功");
    }

    @Override
    public void DishStatusChange(Long id, Integer status) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setStatus(status);
        dishMapper.updateDish(dish);
    }

}
