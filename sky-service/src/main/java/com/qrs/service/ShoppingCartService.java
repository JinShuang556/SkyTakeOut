package com.qrs.service;

import com.qrs.dto.ShoppingCartAddDTO;
import com.qrs.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShoppingCartService {

    /**
     * 获取购物车列表
     * @return 购物车列表
     */
    List<ShoppingCart> getList();

    /**
     * 添加商品到购物车
     * @param shoppingCartAddDTO 商品信息
     */
    void addShoppingCart(ShoppingCartAddDTO shoppingCartAddDTO);
}
