package com.qrs.service.impl;

import com.qrs.constant.ShoppingCartConstant;
import com.qrs.context.BaseContext;
import com.qrs.dto.ShoppingCartAddDTO;
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
        return shoppingCartMapper.selectList();
    }

    @Transactional
    @Override
    public void addShoppingCart(ShoppingCartAddDTO shoppingCartAddDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        //将shoppingCartAddDTO中的属性复制到shoppingCart中
        BeanUtils.copyProperties(shoppingCartAddDTO, shoppingCart);
        //设置shoppingCart的userId
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        //判断这次操作是菜品还是套餐
        if (shoppingCartAddDTO.getDishId() != null) {
            //如果是菜品
            //判断购物车中是否已经存在该菜品
            log.info("购物车add菜品：{}", shoppingCartAddDTO.getDishId());
            Dish dish = dishMapper.getDishById(shoppingCartAddDTO.getDishId());
            Integer count = shoppingCartMapper.getCountByDishId(userId, dish.getId());
            if(count > 0){
                //存在,让该菜品的number++
                //两个都要同时满足，才能定位
                shoppingCartMapper.addNumberByDishId(userId, dish.getId());
            }else{
                //不存在,将shoppingCart插入数据库
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                //插入默认数量：1
                shoppingCart.setNumber(ShoppingCartConstant.SHOPPING_CART_DEFAULT_NUMBER);
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setCreateTime(LocalDateTime.now());
                shoppingCartMapper.insert(shoppingCart);
            }
            log.info("购物车add菜品成功");
            return;
        }
        if (shoppingCartAddDTO.getSetmealId() != null) {
            //如果是套餐
            //判断购物车中是否已经存在该套餐
            log.info("购物车add套餐：{}", shoppingCartAddDTO.getSetmealId());
            Setmeal setmeal = setmealMapper.getSetmealById(shoppingCartAddDTO.getSetmealId());
            Integer count = shoppingCartMapper.getCountBySetmealId(userId, setmeal.getId());
            if(count > 0){
                //存在,让该套餐的number++
                //两个都要同时满足，才能定位
                shoppingCartMapper.addNumberBySetmealId(userId, setmeal.getId());
            }else{
                //不存在,将shoppingCart插入数据库
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setNumber(ShoppingCartConstant.SHOPPING_CART_DEFAULT_NUMBER);
                shoppingCart.setAmount(setmeal.getPrice());
                shoppingCart.setCreateTime(LocalDateTime.now());
                shoppingCartMapper.insert(shoppingCart);
            }
            log.info("购物车add套餐成功");
        }
    }
}
