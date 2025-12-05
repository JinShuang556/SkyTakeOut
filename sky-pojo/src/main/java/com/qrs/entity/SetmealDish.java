package com.qrs.entity;

import lombok.Data;

/**
 * 套餐菜品关联实体类
 * 用于表示套餐与菜品之间的关联关系
 */
@Data
public class SetmealDish {
    private Long id; // 套餐菜品关系ID，唯一标识一条套餐菜品关系记录
    private Integer setmealId; // 套餐ID，关联套餐表的主键
    private Integer dishId; // 菜品ID，关联菜品表的主键
    private String name; // 菜品名称，冗余字段便于展示
    private Double price; // 菜品价格，冗余字段便于展示
    private Integer copies; // 菜品份数，表示该套餐中包含此菜品的数量
}
