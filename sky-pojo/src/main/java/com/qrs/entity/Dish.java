package com.qrs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 菜品类(Dish)
 * 用于存储菜品相关的信息，包括菜品的基本信息、价格、状态等
 */
@Data  // 使用Lombok的@Data注解，自动生成getter、setter、equals、hashCode和toString方法
public class Dish {
    private Long id; // 菜品ID，唯一标识一道菜品
    private String name; // 菜品名称
    private Long categoryId; // 菜品所属分类ID
    private Double price; // 菜品价格
    private String image; // 菜品图片路径或URL
    private String description; // 菜品描述信息
    private Integer status; // 菜品状态，例如：0-下架，1-上架等
    private LocalDateTime createTime; // 菜品创建时间
    private LocalDateTime updateTime; // 菜品更新时间
    private Long createUser; // 创建该菜品的管理员ID
    private Long updateUser; // 最后更新该菜品的管理员ID
}
