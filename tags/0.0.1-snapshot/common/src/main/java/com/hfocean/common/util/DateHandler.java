package com.hfocean.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author lin.bc 2016年8月22日
 */
public class DateHandler {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat();

	//获得日期小时
	public static Integer getDateHour(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	// 将日期字符串转化成Date对象
	public static Date parse(String dateStr,String reg) throws Exception {
		sdf.applyPattern(reg);
		return dateStr==null?null:sdf.parse(dateStr);
	}

	//将日期转换成制定格式的字符串
	public static String dateToString(Date date,String reg){
		sdf.applyPattern(reg);
		return date==null?null:sdf.format(date);
	}
	
	// 由出生日期获得年龄
	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			} else {
				age--;
			}
		}
		return age;
	}

	public static Date addMonth(Date date,int month){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	public static Date addDay(Date date,int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	public static Date addSecond(Date date,int second){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}

	public static Date addMinute(Date date,int minute){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}

	public static Date addHour(Date date,int hour){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, hour);
		return c.getTime();
	}
	
	public static Date addTime(Date date,long time){
		return new Date(date.getTime()+time);
	}

    /**
     * 判断指定时间是否小于当前时间
     * @param expirationDay
     * @return
     */
	public static boolean isExpirated(Date expirationDay){
		Calendar d = Calendar.getInstance();
		d.setTime(expirationDay);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        return d.compareTo(now)==-1;
	}

	/**
	 * 获取某个时间的当天日期时间
	 * @param date
	 * @return
	 * @throws Exception
     */
	public static Date getDayTime(Date date) throws Exception {
		return parse(dateToString(date,"yyyyMMdd"),"yyyyMMdd");
	}

	/**
	 * 比较两个时间大小
	 * @return
	 */
	public static int compare(Date from,Date to){
		Calendar d = Calendar.getInstance();
		d.setTime(from);
		Calendar now = Calendar.getInstance();
		now.setTime(to);
		return d.compareTo(now);
	}

	public static Date getDateTimePoint(Date date,int hour,int minute){
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.HOUR_OF_DAY,hour);
		instance.set(Calendar.MINUTE,minute);
		instance.set(Calendar.SECOND,0);
		return instance.getTime();
	}

	public static Date getTodayTimePoint(int hour,int minute){
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.set(Calendar.HOUR_OF_DAY,hour);
		instance.set(Calendar.MINUTE,minute);
		instance.set(Calendar.SECOND,0);
		return instance.getTime();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getTodayTimePoint(21,0));
	}
}
