package com.qrs.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * 菜品及套餐分类
 * 用于存储分类相关的信息
 */
@Data  // 使用Lombok的@Data注解，自动生成getter、setter、toString等方法
public class Category {
    private Long id;        // 分类ID
    private Integer type;      // 分类类型 1 菜品分类；2 套餐分类
    private String name;       // 分类名称
    private Integer sort;      // 分类排序
    private Integer status;    // 分类状态 0 禁用 1 启用
    private LocalDate createTime;  // 创建时间
    private LocalDate updateTime;  // 更新时间
    private Integer createUser;    // 创建人ID
    private Integer updateUser;    // 更新人ID
}
