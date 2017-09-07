package com.hfocean.uavportal.console.notify.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author guan.sj
 */
@Configuration
@ConfigurationProperties(prefix = "wechat.imgtext")
@ConditionalOnProperty(prefix="wechat.imgtext",value="imgFile")
public class WechatImgTextConfig {
	private String imgFile;
	private String imgUrl;
	private String newsUrl;
	private String annoLink;
	private String sendAllUrl;
	public String getImgFile() {
		return imgFile;
	}
	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getNewsUrl() {
		return newsUrl;
	}
	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}
	public String getAnnoLink() {
		return annoLink;
	}
	public void setAnnoLink(String annoLink) {
		this.annoLink = annoLink;
	}
	public String getSendAllUrl() {
		return sendAllUrl;
	}
	public void setSendAllUrl(String sendAllUrl) {
		this.sendAllUrl = sendAllUrl;
	}
}
