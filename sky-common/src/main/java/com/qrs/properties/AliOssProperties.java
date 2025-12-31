package com.qrs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS存储配置属性类
 * 用于封装阿里云对象存储服务的相关配置信息
 */
@Data                 // 使用Lombok的@Data注解，自动生成getter、setter等方法
@Component           // 使用Spring的@Component注解，将此类标记为Spring容器中的Bean
@ConfigurationProperties(prefix = "sky.alioss") // 使用Spring Boot的@ConfigurationProperties注解，指定配置前缀为"aliyun.oss"
public class AliOssProperties {
    private String endpoint;        // 阿里云OSS服务的终端地址
    private String accessKeyId;     // 阿里云OSS的访问密钥ID
    private String accessKeySecret; // 阿里云OSS的访问密钥密码
    private String bucketName;      // 阿里云OSS的存储桶名称
}
