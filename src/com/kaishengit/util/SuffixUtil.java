package com.kaishengit.util;

public class SuffixUtil {
	public static String getSuffix(String str){
		return str.substring(str.lastIndexOf("."));
	}
}
