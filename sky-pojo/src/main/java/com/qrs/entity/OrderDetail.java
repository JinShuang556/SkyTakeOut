package com.qrs.entity;

import lombok.Data;

/**
 * 订单明细
 * 用于存储订单中包含的菜品或套餐的详细信息
 * 使用@Data注解自动生成getter、setter等方法
 */
@Data
public class OrderDetail {
    private Long id;          // 订单详情ID，唯一标识一条订单明细记录
    private String name;        // 菜品或套餐名称，用于展示菜品或套餐的名称信息
    private String image;       // 菜品或套餐图片，存储菜品或套餐对应的图片路径或URL
    private Integer orderId;        // 所属订单ID，关联到订单表的主键，表示该明细属于哪个订单
    private Integer dishId;         // 菜品ID，当明细为菜品时关联到菜品表的主键
    private Integer setmealId;    // 套餐ID，当明细为套餐时关联到套餐表的主键
    private String dishFlavor;   // 菜品口味，用于记录用户选择的菜品口味或特殊要求
    private Integer number;          // 菜品或套餐的数量，表示该明细中包含的菜品或份数
    private Double amount;         // 金额，表示该明细项的总价（单价×数量）
}
