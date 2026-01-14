package com.qrs.service.impl;

import com.qrs.constant.ShoppingCartConstant;
import com.qrs.context.BaseContext;
import com.qrs.dto.ShoppingCartDTO;
import com.qrs.entity.Dish;
import com.qrs.entity.Setmeal;
import com.qrs.entity.ShoppingCart;
import com.qrs.mapper.DishMapper;
import com.qrs.mapper.SetmealMapper;
import com.qrs.mapper.ShoppingCartMapper;
import com.qrs.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartMapper shoppingCartMapper;
    private final DishMapper dishMapper;
    private final SetmealMapper setmealMapper;

    @Override
    public List<ShoppingCart> getList() {
        return shoppingCartMapper.showShoppingCart();
    }

    @Transactional
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        //将shoppingCartAddDTO中的属性复制到shoppingCart中
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        //设置shoppingCart的userId
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        //判断当前商品是否在购物车中
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.getList(shoppingCart);
        if (shoppingCartList != null && shoppingCartList.size() == 1) {
            //如果已经存在，就更新数量，数量加1
            shoppingCart = shoppingCartList.getFirst();//获取第一个元素
            shoppingCart.setNumber(shoppingCart.getNumber() + 1);
            shoppingCartMapper.updateNumberById(shoppingCart);
        } else {
            //如果不存在，插入数据，数量就是1
            Long dishId = shoppingCartDTO.getDishId();
            if (dishId != null) {
                //添加到购物车的是菜品
                Dish dish = dishMapper.getDishById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {
                //添加到购物车的是套餐
                Setmeal setmeal = setmealMapper.getSetmealById(shoppingCartDTO.getSetmealId());
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(ShoppingCartConstant.SHOPPING_CART_DEFAULT_NUMBER);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);
        }
    }

    @Transactional
    @Override
    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        //获得购物车的信息：
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.getList(shoppingCart);
        ShoppingCart shoppingCart1 = shoppingCarts.getFirst();
        int number = shoppingCart1.getNumber() - 1;
        shoppingCart1.setNumber(number);
        shoppingCartMapper.updateNumberById(shoppingCart1);
        //如果数量为0则删除该购物车
        if (number == 0) {
            shoppingCartMapper.deleteById(shoppingCart1.getId());
        }
    }

    @Override
    public void cleanShoppingCart() {
        //获得当前用户的id
        Long UserId = BaseContext.getCurrentId();
        //根据用户id清空购物车
        shoppingCartMapper.cleanShoppingCart(UserId);
    }
}
