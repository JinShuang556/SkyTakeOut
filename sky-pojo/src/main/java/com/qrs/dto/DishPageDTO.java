package com.qrs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜品分页查询数据传输对象
 * 用于封装菜品分页查询的参数信息
 */
@Data                 // Lombok注解，自动生成getter、setter、toString等方法
@NoArgsConstructor  // Lombok注解，自动生成无参构造方法
@AllArgsConstructor // Lombok注解，自动生成全参构造方法
@Builder            // Lombok注解，提供Builder模式构建对象
public class DishPageDTO {
    private Integer page;        // 当前页码
    private Integer pageSize;    // 每页显示条数
    private String name;         // 菜品名称，用于模糊查询
    private Long categoryId;     // 菜品分类ID，用于按分类筛选
    private Integer status;      // 菜品状态，例如：0-停售 1-起售
}
