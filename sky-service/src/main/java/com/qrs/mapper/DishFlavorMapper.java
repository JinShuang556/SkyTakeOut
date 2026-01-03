package com.qrs.mapper;

import com.qrs.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 添加菜品口味
     * @param flavor 口味
     */
    void addDishFlavor(DishFlavor flavor);

    /**
     * 批量添加菜品口味
     * @param flavors 口味
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品ID批量删除菜品口味
     * @param ids 菜品ID
     */
    void deleteDishFlavorByDishIds(List<Long> ids);

    /**
     * 根据菜品ID查询菜品口味
     * @param dishId 菜品ID
     * @return 菜品口味列表
     */
    List<DishFlavor> selectDishFlavorByDishId(Long dishId);

    /**
     * 查询菜品是否有口味
     * @param id 菜品ID
     * @return 是否有口味
     */
    @Select("SELECT COUNT(*) FROM dish_flavor WHERE dish_id = #{id}")
    Integer selectDishIdInDishFlavor(Long id);

    /**
     * 根据菜品ID删除菜品口味
     * @param id 菜品ID
     */
    @Delete("DELETE FROM dish_flavor WHERE dish_id = #{id}")
    void deleteDishFlavorByDishId(Long id);
}
