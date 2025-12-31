package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.annotation.AutoFill;
import com.qrs.dto.DishPageDTO;
import com.qrs.entity.Dish;
import com.qrs.entity.DishFlavor;
import com.qrs.enumeration.OperationType;
import com.qrs.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {
    /**
     * 分页查询菜品
     * @param dishPageDTO 分页参数
     * @return 分页结果
     */
    Page<DishVO> page(DishPageDTO dishPageDTO);

    /**
     * 添加菜品
     * @param dish 菜品
     */
    @AutoFill(OperationType.INSERT)
    void addDish(Dish dish);
}
