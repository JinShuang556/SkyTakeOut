package com.qrs.mapper;

import com.qrs.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 根据套餐id查询套餐菜品
     * @param id 套餐id
     * @return 套餐菜品列表
     */
    @Select("SELECT * FROM setmeal_dish WHERE setmeal_id = #{id}")
    List<SetmealDish> selectSetmealDishesBySetmealId(Long id);
}
