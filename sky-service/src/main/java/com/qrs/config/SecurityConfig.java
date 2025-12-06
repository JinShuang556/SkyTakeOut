package com.qrs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

/**
 * 创建并配置密码编码器Bean
 * 该方法用于Spring容器中注册一个密码编码器实例
 * 使用BCrypt强哈希函数进行密码加密，提供较高的安全性
 * PasswordEncoder 是 Spring Security 框架中用于密码加密和验证的接口。
 * @return PasswordEncoder 返回一个BCryptPasswordEncoder实例，用于密码的加密与验证
 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用BCrypt强哈希函数加密，自动处理盐值和哈希过程
        return new BCryptPasswordEncoder();
    }
}
