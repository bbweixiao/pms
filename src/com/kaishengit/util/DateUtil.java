package com.kaishengit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
	public static String getTime(String str){
		
		Calendar c = Calendar.getInstance();
		
		try {
			c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String s = "";
		long time = (System.currentTimeMillis()-c.getTimeInMillis());
		if(time/1000 < 60){
			s = "刚刚发布";
		}else if(time/1000/60 < 60){
			s = "发布于" + (time/1000/60) + "分钟之前";
		}else if(time/1000/60/60 < 24){
			s = "发布于" + (time/1000/60/60) + "小时之前";
		}else if(time/1000/60/60/24 < 30){
			s = "发布于" + (time/1000/60/60/24) + "天之前";
		}else if(time/1000/60/60/24/30 < 12){
			s = "发布于" + (time/1000/60/60/24/30) + "个月之前";
		}else{
			s = "发布于" + str.substring(0, 4) + "年" + Integer.parseInt(str.substring(5, 7)) + "月" + Integer.parseInt(str.substring(8, 10)) + "日";
		}
		return s;
	}
}
