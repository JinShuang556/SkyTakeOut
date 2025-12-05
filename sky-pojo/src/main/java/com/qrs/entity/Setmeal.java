package com.qrs.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * 套餐实体类
 * 用于存储套餐的基本信息，包括套餐ID、分类ID、名称、价格等
 */
@Data
public class Setmeal {
    private Long id; // 套餐ID，唯一标识一条套餐记录
    private Integer categoryId; // 所属分类ID，用于标识套餐所属的分类
    private String name; // 套餐名称，用于展示给用户看的套餐名称
    private Double price; // 套餐价格，表示套餐的销售价格
    private Integer status; // 套餐状态， 0:停售 1:起售
    private String description; // 套餐描述，用于介绍套餐的详细信息
    private String image; // 套餐图片路径，用于展示套餐的图片
    private LocalDate createTime; // 创建时间，记录套餐创建的具体日期
    private LocalDate updateTime; // 更新时间，记录套餐最后更新的日期
    private Integer createUser; // 创建人ID，记录创建该套餐的操作员ID
    private Integer updateUser; // 更新人ID，记录最后更新该套餐的操作员ID
}
