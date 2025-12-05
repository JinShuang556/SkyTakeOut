package com.qrs.entity;

import lombok.Data;

import java.time.LocalDate;

import lombok.Data;
import java.time.LocalDate;
/**
 * 员工实体类
 * 用于存储员工相关信息，包括基本信息、账号信息、创建和更新时间等
 * 使用@Data注解自动生成getter、setter、toString等方法
 */
@Data
public class Employee {
    private Long id; // 员工ID，主键
    private String name; // 员工姓名
    private String username; // 登录用户名
    private String password; // 登录密码
    private String phone; // 手机号码
    private String sex; // 性别
    private String idNumber; // 身份证号
    private Integer status; // 员工状态（如：0-禁用，1-正常）
    private LocalDate createTime; // 创建时间
    private LocalDate updateTime; // 更新时间
    private Integer createUser; // 创建人ID
    private Integer updateUser; // 更新人ID
}
