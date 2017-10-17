package com.template.utils;

public class TStringUtils {
	public static String tableName2ClassName(String tableName) {
		String s = "";
		String[] tableArr = tableName.split("_");
		for(int i=0;i<tableArr.length;i++) {
			s += toFirstUpper(tableArr[i].toLowerCase());
		}
		return s;
	}
	
	/**
	 * 把列名转化为属�?�名
	 * @param colName
	 * @return
	 */
	public static String columnName2Attr(String colName) {
		if(colName==null || "".equals(colName)) {
			return "";
		}
		String str = tableName2ClassName(colName);
		return str.substring(0,1).toLowerCase()+str.substring(1);
	}
	
	/**
	 * 首字母大写，其他小写
	 * @param str
	 * @return
	 */
	public static String toFirstUpper(String str) {
		if(str==null || "".equals(str)) {
			return "";
		}
		return str.substring(0,1).toUpperCase()+str.substring(1).toLowerCase();
	}
	
	public static void main(String[] args) {
		System.out.println(columnName2Attr("big_class_type"));
	}
	
}
