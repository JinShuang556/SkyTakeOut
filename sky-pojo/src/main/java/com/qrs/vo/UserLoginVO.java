package com.qrs.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User类，用于存储用户信息
 * 该类包含用户的基本属性，如ID、openid、姓名、电话等
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginVO {
    private Long id; // 用户ID，唯一标识符
    private String openid; // 微信用户唯一标识
    private String token; // 用户登录令牌
}
