package com.hfocean.uavportal.weixin.verification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfocean.common.httpclient.WebServiceHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jw.lin
 * 2016年08月18日
 * 微信公众号菜单管理
 */

@Service
public class WechatMenuService {
	
	private static Log log = LogFactory.getLog(WechatMenuService.class);
	
	@Resource
	private WechatService wechatService;

	private static final ObjectMapper objectMapper = new ObjectMapper();

    public String creatMenu() throws Exception{
    	
    	BufferedReader br = null;
    	String menu = null;
    	try {
        	InputStream in = this.getClass().getResourceAsStream("/weChatMenu.json");
        	br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
        	   
        	   StringBuffer sb= new StringBuffer();
        	   String tmp = null;
        	   while((tmp = br.readLine())!=null){   //一行一行读
        		   sb.append(tmp);
        	  }
        	  System.out.println(sb.toString());	
        	  menu = sb.toString();
		} catch (Exception e) {
			log.debug(e.toString());
			if(br!=null)
				br.close();
			throw new Exception(e);
		}finally{
			if(br!=null)
				br.close();
		}

		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+wechatService.getToken().getToken();
		this.deleteMenu();
		String json = WebServiceHelper.webServiceGet2(url, menu);
        Map<String,String> map = objectMapper.readValue(json, Map.class);
        String code = map.get("errcode");
		String errmsg = map.get("errmsg");
		if(Integer.parseInt(code)!=0){
			throw new Exception(code+":"+errmsg);
		}
		return "creatSucc:true";	
    }
    
    public String deleteMenu() throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", wechatService.getToken().getToken()));

        String json = WebServiceHelper.webServiceGet("https://api.weixin.qq.com/cgi-bin/menu/delete", params);
        Map<String,String> map = objectMapper.readValue(json, Map.class);
        String code = map.get("errcode");
		String errmsg = map.get("errmsg");
		if(Integer.parseInt(code)!=0){
			throw new Exception(code+":"+errmsg);
		}
		return "deleteSucc:true";    	
    }
    
    
    public String getMenu() throws Exception{
    	
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", wechatService.getToken().getToken()));
		return WebServiceHelper.webServiceGet("https://api.weixin.qq.com/cgi-bin/menu/get",params);
    }
    
    
}
