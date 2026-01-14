package com.qrs.service;

import com.qrs.dto.ShoppingCartDTO;
import com.qrs.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    /**
     * 获取购物车列表
     * @return 购物车列表
     */
    List<ShoppingCart> getList();

    /**
     * 添加菜品或套餐到购物车
     * @param shoppingCartDTO 菜品或套餐信息
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 从购物车中减少菜品或套餐
     * @param shoppingCartDTO 菜品或套餐信息
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 清空购物车
     */
    void cleanShoppingCart();
}
