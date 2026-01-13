package com.qrs.mapper;

import com.qrs.entity.ShoppingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 查询所有购物车
     * @return 购物车列表
     */
    @Select("select * from shopping_cart")
    List<ShoppingCart> selectList();

    /**
     * 添加购物车
     * @param shoppingCart 购物车信息
     */
    @Insert("insert into shopping_cart(name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, create_time) " +
            "VALUE (#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{amount}, #{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 根据用户id和菜品id查询菜品在购物车的数量
     * @param userId 用户id
     * @param dishId 菜品id
     * @return 菜品数量
     */
    @Select("select count(*) from shopping_cart where user_id = #{userId} and dish_id = #{dishId}")
    Integer getCountByDishId(Long userId, Long dishId);

    /**
     * 根据用户id和菜品id添加菜品在购物车的数量
     * @param userId 用户id
     * @param dishId 菜品id
     */
    @Update("update shopping_cart set number = number + 1 where user_id = #{userId} and dish_id = #{dishId}")
    void addNumberByDishId(Long userId, Long dishId);

    /**
     * 根据用户id和套餐id查询套餐在购物车的数量
     * @param userId 用户id
     * @param setmealId 套餐id
     * @return 套餐数量
     */
    @Select("select count(*) from shopping_cart where user_id = #{userId} and setmeal_id = #{setmealId}")
    Integer getCountBySetmealId(Long userId, Long setmealId);

    /**
     * 根据用户id和套餐id添加套餐在购物车的数量
     * @param userId 用户id
     * @param setmealId 套餐id
     */
    @Update("update shopping_cart set number = number + 1 where user_id = #{userId} and setmeal_id = #{setmealId}")
    void addNumberBySetmealId(Long userId, Long setmealId);
}
