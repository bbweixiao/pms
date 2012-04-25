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
			s = "�ոշ���";
		}else if(time/1000/60 < 60){
			s = "������" + (time/1000/60) + "����֮ǰ";
		}else if(time/1000/60/60 < 24){
			s = "������" + (time/1000/60/60) + "Сʱ֮ǰ";
		}else if(time/1000/60/60/24 < 30){
			s = "������" + (time/1000/60/60/24) + "��֮ǰ";
		}else if(time/1000/60/60/24/30 < 12){
			s = "������" + (time/1000/60/60/24/30) + "����֮ǰ";
		}else{
			s = "������" + str.substring(0, 4) + "��" + Integer.parseInt(str.substring(5, 7)) + "��" + Integer.parseInt(str.substring(8, 10)) + "��";
		}
		return s;
	}
}
