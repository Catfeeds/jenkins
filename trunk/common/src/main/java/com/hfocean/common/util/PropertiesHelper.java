package com.hfocean.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author guan.sj
 */
public class PropertiesHelper {
	
	private static transient final Logger log = LoggerFactory.getLogger(PropertiesHelper.class);
	
	private static Properties pp = getProperties();

	public static String getValue(String key){
		if(!StringUtils.hasText(key)){
			log.debug("找不到key为 {} 对应的值",key);
			return null;
		}
		return pp.getProperty(key);
		
	}
	
	public static String getValue(String key,String defaultValue){
		String v = getValue(key);
		if(null == v) return defaultValue;
		return v;
	}
	
	
	
	private static Properties getProperties(){
		Properties prop = new Properties();
		String path = PropertiesHelper.class.getResource("/coreConfig.properties").getPath();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));
			prop.load(in);
		} catch (Exception e) {
			log.error("加载systemConfig.properties文件失败...");
		} finally{
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
		}
		return prop;
	}
	
}
