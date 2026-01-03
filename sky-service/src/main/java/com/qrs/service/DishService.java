package com.qrs.service;

import com.qrs.dto.DishDTO;
import com.qrs.dto.DishPageDTO;
import com.qrs.entity.Dish;
import com.qrs.vo.DishWithFlavorVO;
import com.qrs.vo.PageVO;

import java.util.List;

public interface DishService {

    /**
     * 分页查询菜品
     * @param dishPageDTO 分页参数
     * @return 分页结果
     */
    PageVO page(DishPageDTO dishPageDTO);

    /**
     * 新增菜品并保存口味
     * @param dishDTO 菜品信息
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 批量删除菜品并删除口味
     * @param ids 菜品ID列表
     */
    void removeWithFlavor(List<Long> ids);

    /**
     * 根据分类ID查询菜品
     * @param categoryId 分类ID
     * @return 菜品列表
     */
    List<Dish> selectDishByCategoryId(Long categoryId);

    /**
     * 根据菜品ID查询菜品和菜品口味
     * @param id 菜品ID
     * @return 菜品信息
     */
    DishWithFlavorVO selectDishWithFlavorById(Long id);
}
