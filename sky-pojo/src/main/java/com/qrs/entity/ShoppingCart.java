package com.qrs.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 购物车实体类
 * 用于存储用户购物车中的商品信息
 * 包含商品的基本信息、用户关联信息以及创建时间等
 */
@Data
public class ShoppingCart {
    private Long id; // 主键ID，唯一标识一条购物车记录
    private Integer name; // 商品名称，用于展示给用户看的商品名称
    private String image; // 商品图片路径，用于展示商品图片
    private Long userId; // 用户ID，关联到具体用户
    private Long dishId; // 菜品ID，关联到具体菜品
    private Long setmealId; // 套餐ID，关联到具体套餐
    private String dishFlavor; // 菜品口味，用户选择的菜品口味描述
    private Integer number; // 商品数量，用户购买该商品的数量
    private Double amount; // 商品金额，该商品的总金额
    private LocalDateTime createTime; // 创建时间，记录该购物车项的添加时间

}
