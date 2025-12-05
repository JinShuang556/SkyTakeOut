package com.qrs.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * User类，用于存储用户信息
 * 该类包含用户的基本属性，如ID、openid、姓名、电话等
 */
@Data
public class User {
    private Long id; // 用户ID，唯一标识符
    private String openid; // 微信用户唯一标识
    private String name; // 用户姓名
    private String phone; // 用户手机号码
    private String sex; // 用户性别
    private String idNumber; // 用户身份证号码
    private String avatar; // 用户头像URL
    private LocalDate createTime; // 用户创建时间
}
