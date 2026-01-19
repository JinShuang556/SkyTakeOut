package com.qrs.mapper;

import com.qrs.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 查询所有购物车
     * @return 购物车列表
     */
    @Select("select * from shopping_cart")
    List<ShoppingCart> showShoppingCart();

    /**
     * 添加购物车
     * @param shoppingCart 购物车信息
     */
    @Insert("insert into shopping_cart(name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, create_time) " +
            "VALUE (#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{amount}, #{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 根据用户id、菜品id、套餐id和口味查询购物车
     * @param shoppingCart 购物车信息
     * @return 购物车列表
     */
    List<ShoppingCart> getList(ShoppingCart shoppingCart);

    /**
     * 根据id修改购物车数量
     * @param shoppingCart 购物车信息
     */
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 根据id删除购物车
     * @param id 购物车id
     */
    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据用户id清空购物车
     * @param userId 用户id
     */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void cleanShoppingCart(Long userId);

    /**
     * 根据用户id批量删除购物车
     * @param userId 用户id
     */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteBatch(Long userId);
}
