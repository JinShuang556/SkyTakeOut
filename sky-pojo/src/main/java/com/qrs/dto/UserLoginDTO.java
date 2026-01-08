package com.qrs.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User类，用于存储用户信息
 * 该类包含用户的基本属性，如ID、openid、姓名、电话等
 */
@Data
public class UserLoginDTO implements Serializable {
    private String code; //微信用户授权码
}
