package com.qrs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {


    /**
     * 创建并配置RedisTemplate Bean
     * 
     * @param redisConnectionFactory 自动注入的Redis连接工厂
     * @return 配置好的RedisTemplate实例
     * 
     * <p>功能说明：
     * 1. 创建RedisTemplate实例
     * 2. 配置键(key)的序列化方式为字符串序列化
     * 3. 设置连接工厂
     * 4. 默认保留值(value)的JDK序列化方式</p>
     * 
     * <p>注意事项：
     * - 键序列化器改为StringRedisSerializer后，Redis客户端可直读key值
     * - 值序列化仍使用默认JDK方式，适合存储Java对象
     * - 若需要JSON格式存储值，需额外配置valueSerializer</p>
     */
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("初始化Redis模板配置");
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        
        // 设置键的字符串序列化（替代默认的JDK二进制序列化）
        // 优点：Redis客户端可直接查看/管理字符串格式的key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        
        // 注入连接工厂配置（包含host、port等连接信息）
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        
        return redisTemplate;
    }
}