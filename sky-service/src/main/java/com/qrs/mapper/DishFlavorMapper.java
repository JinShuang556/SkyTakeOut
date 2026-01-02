package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.annotation.AutoFill;
import com.qrs.dto.DishPageDTO;
import com.qrs.entity.Dish;
import com.qrs.entity.DishFlavor;
import com.qrs.enumeration.OperationType;
import com.qrs.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

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
     * 根据菜品ID删除菜品口味
     * @param ids 菜品ID
     */
    void deleteDishFlavorByDishIds(List<Long> ids);
}
