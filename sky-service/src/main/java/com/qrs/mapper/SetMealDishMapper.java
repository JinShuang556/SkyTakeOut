package com.qrs.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {
    /**
     * 查询菜品是否关联了套餐
     * @param ids 菜品id
     */
    Integer checkDishIdsInSetmealDish(List<Long> ids);


}
