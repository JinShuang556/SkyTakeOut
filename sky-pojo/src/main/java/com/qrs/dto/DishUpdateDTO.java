package com.qrs.dto;

import com.qrs.entity.DishFlavor;
import lombok.Data;
import java.util.List;

/**
 * 菜品类(Dish)
 * 用于存储菜品相关的信息，包括菜品的基本信息、价格、状态等
 */
@Data  // 使用Lombok的@Data注解，自动生成getter、setter、equals、hashCode和toString方法
public class DishUpdateDTO {
    private Long id; // 菜品ID，唯一标识一道菜品
    private String name; // 菜品名称
    private Integer categoryId; // 菜品所属分类ID
    private Double price; // 菜品价格
    private String image; // 菜品图片路径或URL
    private String description; // 菜品描述信息
    private Integer status; // 菜品状态，例如：0-下架，1-上架等
    private String categoryName; //套餐名
    private List<DishFlavor> flavors; // 菜品口味
}
