package com.hfocean.uavportal.web;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 24 * 60 * 60)
public class SessionConfig {
	@Bean
    public CookieSerializer cookieSerializer() {
		 DefaultCookieSerializer serializer = new DefaultCookieSerializer();
         serializer.setCookieName("uav-session"); 
         serializer.setCookiePath("/"); 
         return serializer;
    }
}
