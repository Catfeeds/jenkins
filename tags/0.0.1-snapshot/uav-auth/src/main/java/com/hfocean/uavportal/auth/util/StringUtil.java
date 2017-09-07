package com.hfocean.uavportal.auth.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {
	private static Log log = LogFactory.getLog(StringUtil.class);
	
	public static final Double toDouble(String str){
		Double result = new Double(0.0d);
		if (str != null && !"".equals(str)){
			try{
				result = new Double(str);
			}catch(NumberFormatException nfe){
				log.debug("[" + str + "]格式不正确或者超出double类型的值的范围", nfe);
			}
		}
		return result;
	}
	
	public static String nullToString(String str){
		if (str == null){
			str = "";
		}
		return str;
	}
	
	public static boolean isNull(String str){
		return str == null || "".equals(str);
	}

	public static boolean isNull(StringBuffer str){
		return str == null || "".equals(str.toString());
	}
	public static boolean noNull(String str){
		return !isNull(str);
	}

	public static boolean noNull(StringBuffer str){
		return !isNull(str);
	}
	/**  
   * 生成随机数  
   *   
   * @param pwd_len  
   *            生成的密码的总长度  
   * @return 密码的字符串  
   */  
  public static String genRandomNum(int len) {
  	String str = "";
  	while(str.length() < len){
  		str += String.valueOf(Math.round(Math.random()*10));
  	}
    return str.substring(0, len); 
  }   
  
  public static boolean equals(String str1, String str2){
  	if (str1 == null && str2 != null){
  		return false;
  	}else if (str1 != null && str2 == null){
  		return false;
  	}else if (str1 == null && str2 == null){
  		return true;
  	}else{
  		return str1.equals(str2);
  	}
  }
  
  public static boolean equalsIgnoreCase(String str1, String str2){
		if (isNull(str1) || isNull(str2)){
			return false;
		}else{
			return str1.equalsIgnoreCase(str2);
		}
  }
  public static String toHexString(byte b){
		String s = Integer.toHexString(b & 0xFF);
		if (s.length() == 1){
    	return "0" + s;
    }else{
    	return s;
    }
  }
  public static String toHexString(byte[] b){
  	StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < b.length; ++i){
    	buffer.append(toHexString(b[i]));
    }
	return buffer.toString();
  }
  /**
   * 数组转换成十六进制字符串
   * @param byte[]
   * @return HexString
   */
  public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
  /**
   * 指定字符串在源字符里出现的次数
   * @param src 源字符串
   * @param target 指定字符串
   * @return 次数
   */
  public static int getTokenCount(String src, String target){
  	int count = 0;
  	if (isNull(src)){
  		return count;
  	}
  	for (int i = src.indexOf(target); i > -1; src = src.substring(i + target.length()), i = src.indexOf(target)){
  		count++;
  	}
  	return count;
  }
  
  public static List<Integer> idsToInteger(String ids){
		List<Integer> ret = new ArrayList<Integer>();
		String[] arrIds = (ids.split(","));
		for(String id : arrIds){
			if(StringUtil.noNull(id)){
				ret.add(Integer.valueOf(id.trim()));
			}
		}
		return ret;
	}
  	public static List<String> idsToString(String ids){
		List<String> ret = new ArrayList<String>();
		String[] arrIds = (ids.split(","));
		for(String id : arrIds){
			if(StringUtil.noNull(id)){
				ret.add(id);
			}
		}
		return ret;
	}
  
  public static String getValue(String all, String name, String sp){
	  if(StringUtil.isNull(all))
		  return "";
	  String[] rets = all.split(sp);
	  if(rets.length<2 || StringUtil.isNull(rets[0]))
		  return "";
	  return rets[1];
  }
  
  public static String toUTF8(String isoString) {
	  String utf8String = null;
	  if (null != isoString && !isoString.equals("")) {
		  try {
			  byte[] stringBytesISO = isoString.getBytes("ISO-8859-1");
			  utf8String = new String(stringBytesISO, "UTF-8");
		  } catch (UnsupportedEncodingException e) {
			  System.out.println("字符串转换类型异常: " + e.getMessage());
			  utf8String = isoString;
		  }
	  } else {
		  utf8String = isoString;
	  }
	  return utf8String;
  }
  
  public static String GBKtoUTF8(String isoString) {
	  String utf8String = null;
	  if (null != isoString && !isoString.equals("")) {
		  try {
			  byte[] stringBytesISO = isoString.getBytes("GBK");
			  utf8String = new String(stringBytesISO, "UTF-8");
		  } catch (UnsupportedEncodingException e) {
			  System.out.println("字符串转换类型异常: " + e.getMessage());
			  utf8String = isoString;
		  }
	  } else {
		  utf8String = isoString;
	  }
	  return utf8String;
  }
  
  public static Integer[] idsToIntegerArr(String ids){
		Integer[] ret = null;
		if (StringUtil.noNull(ids)) {
			String arrIds[] = ids.split(",");
			ret = new Integer[arrIds.length];
			int i = 0;
			for (String id : arrIds) {
				ret[i++] = Integer.parseInt(id);
			}
		}
		return ret;
  }
  public static String[] stringToStringArr(String arrayString){
	  	String[] ret = null;
		if (StringUtil.noNull(arrayString)) {
			String arrString[] = arrayString.split(",");
			ret = arrString;
		}
		return ret;
  }  
  public static String replaceEnter(String oldString) {
	  Pattern pattern=Pattern.compile("(\r\n|\r|\n|\n\r)");
	  Matcher matcher=pattern.matcher(oldString);
	  String newString=matcher.replaceAll(" ");
	  return newString;
  }
  public static String cutMatch(String s) {
      List<String> results = new ArrayList<String>();
      Pattern p = Pattern.compile("\\[(.*?)\\]");
      Matcher m = p.matcher(s);
      while (!m.hitEnd() && m.find()) {
          results.add(m.group(1));
      }
      String ret = Arrays.toString(results.toArray(new String[0]));

      return ret;
  }
  
  public static final String PREFIX = "No.";

  public static synchronized String nextNumber() {
      return PREFIX + UtilDate.getOrderNum();
  }
  
  public static String getUUID(){
	  return UUID.randomUUID().toString().replaceAll("-", "");
  }
  
  public static void main(String[] args) {
	System.out.println(nextNumber());
  }
  
  public static String stringToInt(String value) {
	if(StringUtil.noNull(value)){
		String[] list =  value.split("\\.");
		return list[0];
	}else {
		return "";
	}
  }
  
  public static BigDecimal BigDecimalToInt(BigDecimal value) {
	  
	if(value!=null){
		return new BigDecimal(value.intValue());
	}else {
		return new BigDecimal(0);
	}
  }  
  

}
