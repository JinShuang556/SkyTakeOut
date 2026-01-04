package com.qrs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data  // 使用Lombok的@Data注解，自动生成getter、setter、equals、hashCode和toString方法
public class SetmealPageVO {
    private Long id; // 套餐ID，
    private String name; // 套餐名称
    private Integer categoryId; // 分类ID
    private Double price; // 套餐价格
    private String image; // 套餐图片路径或URL
    private String description; // 套餐描述信息
    private Integer status; // 套餐状态，例如：0-下架，1-上架等
    private LocalDateTime updateTime; // 套餐更新时间
    private String categoryName; // 分类名称
}
