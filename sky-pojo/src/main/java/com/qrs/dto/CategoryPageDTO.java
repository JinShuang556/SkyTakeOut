package com.qrs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryPageDTO {
    //分类名称
    private String name;
    //页码
    private Integer page;
    //每页数量
    private Integer pageSize;
    //分类类型 1为菜品分类 2为套餐分类
    private Integer type;
}
