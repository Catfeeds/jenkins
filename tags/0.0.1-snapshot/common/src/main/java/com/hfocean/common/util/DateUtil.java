package com.hfocean.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * 
 * 
 */
public class DateUtil {

	/**
	 * 时间戳转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String dateToString(Timestamp date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 时间戳转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Timestamp date) {
		return dateToString(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/*
	 * 
	 * */
	public static String dateToString2(Timestamp date) {
		return dateToString(date, "yyyy年M月d日");
	}
	
	/*
	 * 
	 * */
	public static String dateToString3(Timestamp date) {
		return dateToString(date, " HH:mm");
	}	
	
	/*
	 * 获得时间的日期字符
	 * */
	public static String getDateStr(Timestamp date) {
		return dateToString(date, "yyyy-MM-dd");
	}
	
	/*
	 * 获得时间的小时字符
	 * */
	public static String getHourStr(Timestamp date) {
		return dateToString(date, "HH");
	}
	

	/*
	 * 获得时间的月份日期字符
	 * */
	public static String getMonthAndDayStr(Timestamp date) {
		return dateToString(date, "M月d日");
	}
	
	
	/**
	 * 加/减 精确到天
	 * @param date 日期
	 * @param day 加/减 天数
	 * @return
	 */
	public static Date addDay(Date date,int day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}
	
	/**
	 * 加/减 精确到秒
	 * @param date 时间
	 * @param num 天
	 * @return
	 */
	public static Date addDate(Date date,int num){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);
		return cal.getTime();
	}
	
	
	/**
	 * 日期是否相同
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean equalsDay(Date date1,Date date2){
		return sdf.format(date1).equals(sdf.format(date2));
	}
	
	
	/**
	 * 转字符串
	 * @param date
	 * @return
	 */
	public static String date2String(Date date){
		return sdf.format(date);
	}
	
	/**
	 * 字符串转日期 
	 * @param date 字符串
	 * @param format 日期格式
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date(String date,String format) throws ParseException{
		return new SimpleDateFormat(format).parse(date);
	}
	
	/**
	 * 字符串转日期 默认格式:yyyy-MM-dd
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date(String date) throws ParseException{
		return sdf1.parse(date);
	}
	
	/**
	 * 时间加/减 小时
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date,int hour){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);
		return cal.getTime();
	}	
	
	/**
	 * 时间加/减 分钟
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date,int minute){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}
	
	/**
	 * date 是否在 d1,d2之间 (注意:只比较天数yyyyMMdd)
	 * @param date 目标日期
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return 是:true,否:false;
	 */
	public static boolean isBetweenDay(Date date,Date d1,Date d2){
		
		String s = sdf.format(date);
		String s1 = sdf.format(d1);
		String s2 = sdf.format(d2);
		
		Long l = Long.parseLong(s);
		Long l1 = Long.parseLong(s1);
		Long l2 = Long.parseLong(s2);
		
		return l>=l1 && l<=l2;
	}
	

	public static String date2String(Date date,String format){
		return new SimpleDateFormat(format).format(date);
	}
	
	public static String GMTFormat(String date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf2.format(sdf.parse(date));
	}
	
	public static void main(String[] args) throws Exception {
		
		String str = minute2DateFormat(11520);
		
		System.out.println(str);
	}
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	
	/**查看天数(传入的时间与当前系统时间相隔天数)
	 * 如果是当前，则返回1，表示第一天
	 * @param date:开始时间
	 * @return int 天数
	 */
	public static int howManyDays(Date date){
		Long d1 = date.getTime();
		Long d2 = System.currentTimeMillis();
		
		return ((int)(d2-d1)/1000/60/60/24)+1;
	}
	
	
	
	public static String minute2DateFormat(Integer minute){
		if(minute==null || minute<=0) return "";
		
		String str ="";
		
		int hh = minute/60;
		
		if(hh!=0){
			int mm = (minute%60);
			String mValue = "";
			if(mm>0){
				mValue = mm + "分";
			}
			if(hh<24){
				str=hh+"小时"+mValue;
			}else{
				int dd = hh/24;
				int h = (hh%24);
				String hValue = "";
				if(h!=0)hValue = h+"小时";
				str =dd+"天"+hValue+mValue;
			}
		}else{
			str = minute+"分";
		}
		
		return str;
	}
	
	
	public static String minute2DateFormat(String minute){
		if(minute==null || minute.trim().equals("")) 
			return minute2DateFormat(0);
		return minute2DateFormat(Integer.valueOf(minute));
	}
	
	
	
}
