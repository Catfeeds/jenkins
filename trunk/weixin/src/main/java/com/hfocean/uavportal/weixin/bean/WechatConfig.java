package com.hfocean.uavportal.weixin.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
* 
* @author Leslie.Lam
* @create 2017-06-23 14:40
**/

@Configuration
@ConfigurationProperties(prefix = "wechat")
@ConditionalOnProperty(prefix="wechat",value="appId")
public class WechatConfig {

    private String appId;

    private String secret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
