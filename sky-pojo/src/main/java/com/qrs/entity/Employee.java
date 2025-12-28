package com.qrs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * 员工实体类
 * 用于存储员工相关信息，包括基本信息、账号信息、创建和更新时间等
 * 使用@Data注解自动生成getter、setter、toString等方法
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // 更新时间
    private Long createUser; // 创建人ID
    private Long updateUser; // 更新人ID
}
