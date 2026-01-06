package com.qrs.controller.admin;

import com.qrs.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
@RequiredArgsConstructor
@Slf4j
public class ShopController {

    private final RedisTemplate<Object, Object> redisTemplate;
    private static final String SHOP_STATUS_KEY = "SHOP_STATUS";

    /**
     * 修改店铺营业状态
     * @param status 营业状态
     * @return 结果
     */
    @PutMapping("/{status}")
    public Result ShopStatus(@PathVariable Integer status) {
        log.info("修改商铺状态：{}", status);
        redisTemplate.opsForValue().set(SHOP_STATUS_KEY, status);
        return Result.success();
    }

    /**
     * 获得店铺营业状态
     * @return 结果
     */
    @GetMapping("/status")
    public Result getShopStatus() {
        Object status = redisTemplate.opsForValue().get(SHOP_STATUS_KEY);
        log.info("获取商铺状态：{}", status);
        return Result.success(status);
    }

}
