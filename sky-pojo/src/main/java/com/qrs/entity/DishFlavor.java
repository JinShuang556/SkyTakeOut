package com.qrs.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜品口味类
 * 用于存储和管理菜品的相关口味信息
 */
@Data
public class DishFlavor implements Serializable {
    private Long id; // 菜品口味ID，唯一标识一个菜品口味
    private Long dishId; // 关联的菜品ID，用于标识该口味属于哪个菜品
    private String name; // 口味名称，如"辣度"、"甜度"等
    private String value; // 口味的具体值，如"微辣"、"不辣"等
}
