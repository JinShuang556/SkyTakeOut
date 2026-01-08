package com.qrs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sky.wechat")
public class WeChatProperties {
    private String appId;
    private String appSecret;
}
