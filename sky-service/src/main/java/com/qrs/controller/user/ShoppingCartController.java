package com.qrs.controller.user;

import com.qrs.dto.ShoppingCartAddDTO;
import com.qrs.entity.ShoppingCart;
import com.qrs.result.Result;
import com.qrs.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * @param shoppingCartAddDTO 数据
     * @return 成功
     */
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartAddDTO shoppingCartAddDTO){
        log.info("添加购物车:{}",shoppingCartAddDTO);
        shoppingCartService.addShoppingCart(shoppingCartAddDTO);
        return Result.success();
    }


}
