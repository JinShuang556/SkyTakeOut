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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtProperties jwtProperties;

    private final UserService userService;

    @PostMapping("/user/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录:{}",userLoginDTO.getCode());

        //检查用户是否正确
        User user = userService.wxLogin(userLoginDTO);

        // 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());

        //生成token:
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getUserTtl(),claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();


        return Result.success(userLoginVO);

    }

}
