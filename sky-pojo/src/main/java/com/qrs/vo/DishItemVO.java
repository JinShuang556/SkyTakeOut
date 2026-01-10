package com.qrs.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DishItemVO implements Serializable {
    private String name; //菜品名称
    private String image; //菜品图片Uri
    private String description; //菜品描述
    private Integer copies; //份数

}
