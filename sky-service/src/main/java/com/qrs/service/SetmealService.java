package com.qrs.service;

import com.qrs.dto.SetmealPageDTO;
import com.qrs.dto.SetmealWithSetmealDishDTO;
import com.qrs.vo.PageVO;

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
}
