package com.qrs.dto;

import com.qrs.entity.Dish;
import com.qrs.entity.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * 套餐数据传输对象(DTO)类
 * 用于在系统不同层之间传递套餐相关数据
 */
@Data  // Lombok注解，自动生成getter、setter等方法
public class SetmealWithSetmealDishDTO {
    private Long id; // 套餐id，唯一标识套餐
    private String name; // 套餐名称，用于展示给用户
    private Long categoryId; // 分类id
    private String categoryName; // 分类名称
    private Long idType;// 套餐id
    private String code;// 套餐编码
    private String description; // 套餐描述
    private Integer status; //套餐状态 0：停售 1：起售
    private String image; //照片URL
    private Double price; //价格

    private List<SetmealDish> setmealDishes; //套餐包含的菜品


    private List<Dish> dishList;// 菜品列表
}
