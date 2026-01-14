package com.qrs.controller.user;

import com.qrs.dto.ShoppingCartDTO;
import com.qrs.entity.ShoppingCart;
import com.qrs.result.Result;
import com.qrs.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    /**
     * 获取购物车列表
     * @return 购物车列表
     */
    @GetMapping("/list")
    public Result<List<ShoppingCart>> list(){
        log.info("获取购物车列表");
        List<ShoppingCart> shoppingCartList = shoppingCartService.getList();
        return Result.success(shoppingCartList);
    }

    /**
     * 将菜品或套餐添加到购物车
     * @param shoppingCartDTO 数据
     * @return 成功
     */
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加购物车:{}", shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 删除购物车中的菜品或套餐
     * @param shoppingCartDTO 数据
     * @return 成功
     */
    @PostMapping("/sub")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("删除购物车:{}",shoppingCartDTO);
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 清空购物车
     * @return 成功
     */
    @DeleteMapping("/clean")
    public Result clean(){
        log.info("清空购物车");
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }


}
