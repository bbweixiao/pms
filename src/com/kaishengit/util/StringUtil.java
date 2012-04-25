package com.kaishengit.util;

public class StringUtil {

	/**
	 * 判断一个字符是否为null或为空
	 * @param str
	 * @return 
	 */
	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str));
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
}
