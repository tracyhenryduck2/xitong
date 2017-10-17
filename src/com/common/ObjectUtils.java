package com.common;

public class ObjectUtils {

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String obj2String(Object obj) {
		if(obj==null) {
			return null;
		} else {
			return obj.toString();
		}
	}
	
	/**
	 * 转化为Long
	 * @param obj
	 * @return
	 */
	public static Long obj2Long(Object obj) {
		if(obj==null) {
			return null;
		} else {
			return Long.parseLong(obj.toString());
		}
	}
	
	public static void main(String[] args) {
		System.out.println(obj2Long("10.1"));
	}
	
}
