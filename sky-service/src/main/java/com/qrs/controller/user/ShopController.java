package com.qrs.controller.user;


import com.qrs.constant.JwtClaimsConstant;
import com.qrs.dto.UserLoginDTO;
import com.qrs.entity.User;
import com.qrs.properties.JwtProperties;
import com.qrs.result.Result;
import com.qrs.service.UserService;
import com.qrs.utils.JwtUtil;
import com.qrs.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController("userShopController")
@RequestMapping("/user/shop")
@RequiredArgsConstructor
public class ShopController {

    private static final String SHOP_STATUS_KEY = "SHOP_STATUS";

    private final RedisTemplate<Object,Object> redisTemplate;

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
