package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.annotation.AutoFill;
import com.qrs.dto.DishPageDTO;
import com.qrs.entity.Dish;
import com.qrs.enumeration.OperationType;
import com.qrs.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 批量删除菜品
     * @param ids 菜品id
     */
    void deleteDishByIds(List<Long> ids);

    /**
     * 根据id批量查询菜品
     * @param ids 菜品id
     * @return 菜品列表
     */
    List<Dish> selectDishByIds(List<Long> ids);

    /**
     * 根据id查询菜品
     */
    @Select("select * from dish where id = #{id}")
    Dish selectDishById(Long id);

    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> selectDishByCategoryId(Long categoryId);
}
