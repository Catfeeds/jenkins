package com.hfocean.common.httpclient;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;


public class WebServiceHelper {
	
	private static Log log = LogFactory.getLog(WebServiceHelper.class);
	
	
	//正常传参：key-value
	public static String webServiceGet(String url, List<NameValuePair> params) throws ClientProtocolException, IOException{
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
		log.debug("开始请求:"+url);
		HttpResponse response = httpClient.execute(httpPost);
		
		String strResult = null;
		if(response.getStatusLine().getStatusCode()==200){
			strResult = EntityUtils.toString(response.getEntity());
			log.debug("请求成功，返回结果为："+strResult);
		}
		
		return strResult;
	}
	
	
	//body参数请调用这个
	public static String webServiceGet2(String url, String body) throws ClientProtocolException, IOException{
		
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		PostMethod httpPost = new PostMethod(url);
		httpPost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		httpPost.setRequestBody(body);
		
		log.debug("开始请求:"+url);
		httpClient.executeMethod(httpPost);
		
		String strResult = httpPost.getResponseBodyAsString(); 
		log.debug("请求成功，返回结果为："+strResult);

		return strResult;
	}
	//body参数请调用这个
		public static String webServiceGet(String url, String body) throws ClientProtocolException, IOException{
						
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			PostMethod httpPost = new PostMethod(url);
			httpPost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
			httpPost.setRequestBody(body);
			
			log.debug("开始请求:"+url);
			int response = httpClient.executeMethod(httpPost);
			
			return httpPost.getResponseBodyAsString(); 
		}
	
	
	
	//shar加密
	public static String shar1(String object){
		String value = null;
        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(object.getBytes("UTF-8"));
            value = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e){
        	log.debug(e);
        	e.printStackTrace();
        }
        catch (UnsupportedEncodingException e){
        	log.debug(e);
            e.printStackTrace();
        }
        
        return value;
	}
	
    private static String byteToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
