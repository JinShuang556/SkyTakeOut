package com.qrs.dto;

import lombok.Data;

@Data
public class ShoppingCartAddDTO {
    private Long dishId;
    private Long setmealId;
    private String dishFlavor;
}
