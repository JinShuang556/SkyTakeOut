package com.qrs.service;

import com.qrs.dto.SetmealPageDTO;
import com.qrs.dto.SetmealWithSetmealDishDTO;
import com.qrs.entity.Setmeal;
import com.qrs.vo.DishItemVO;
import com.qrs.vo.PageVO;
import com.qrs.vo.SetmealWithSetmealDishVO;

import java.util.List;

public interface SetmealService {
    /**
     * 套餐分页查询
     * @param setmealPageDTO 分页参数
     * @return 分页结果
     */
    PageVO page(SetmealPageDTO setmealPageDTO);

    /**
     * 新增套餐和套餐关联菜品
     * @param setmealWithSetmealDishDTO 套餐和套餐关联菜品
     */
    void addSetmealWithSetmealDish(SetmealWithSetmealDishDTO setmealWithSetmealDishDTO);

    /**
     * 批量删除套餐和套餐关联菜品
     * @param ids 套餐id集合
     */
    void deleteSetmealWithSetmealDish(List<Long> ids);

    /**
     * 根据套餐id查询套餐和套餐关联菜品
     * @param id 套餐id
     * @return 套餐和套餐关联菜品
     */
    SetmealWithSetmealDishVO selectSetmealWithSetmealDishById(Long id);

    /**
     * 更新套餐和套餐关联菜品
     * @param setmealWithSetmealDishDTO 套餐和套餐关联菜品
     */
    void updateSetmealWithSetmealDish(SetmealWithSetmealDishDTO setmealWithSetmealDishDTO);

    /**
     * 更新套餐状态
     * @param id 套餐id
     * @param status 状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 根据分类id查询套餐
     * @param categoryId 分类id
     * @return 套餐列表
     */
    List<Setmeal> getSetmealsBycategoryId(Long categoryId);

    /**
     * 根据套餐id查询包含的菜品
     * @param id 套餐id
     * @return 菜品列表
     */
    List<DishItemVO> selectDishesById(Long id);
}
