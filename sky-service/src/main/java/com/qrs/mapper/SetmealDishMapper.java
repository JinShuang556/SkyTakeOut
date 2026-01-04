package com.qrs.mapper;

import com.qrs.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 查询菜品是否关联了套餐
     * @param ids 菜品id
     */
    Integer checkDishIdsInSetmealDish(List<Long> ids);

    /**
     * 批量插入套餐菜品
     * @param setmealDishes 套餐菜品
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id删除套餐菜品
     * @param ids 套餐id
     */
    void deleteSetmealDishBySetmealIds(List<Long> ids);
}
