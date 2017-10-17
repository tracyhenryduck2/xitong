package com.common;

import com.avatar.db.DBFactory;
import com.avatar.db.jdbc.JdbcHandler;

public class DBUtils {

	private static JdbcHandler j = DBFactory.create("write");
	/**
	 * 是否存在
	 * @param tableName 表名
	 * @param fieldName 表字段名
	 * @param value 传递值
	 * @return true 存在,false不存在
	 */
	public static boolean isExist(String tableName, String fieldName,
			String value) {
		String sql = "select count(*) from " + tableName + " where "
				+ fieldName + "=? ";
		Object[] params = { value };
		int count = j.queryForInteger(sql, params);
		return count > 0;
	}
	
	/**
	 * 判断数据是否存在
	 * @param tableName 表名
	 * @param fieldName 字段名
	 * @param value 值
	 * @param keyFieldName 主键名
	 * @param keyValue 主键值
	 * @return true 存在,false不存在
	 */
	public static boolean isExist(String tableName, String fieldName,
			String value, String keyFieldName, String keyValue) {
		String sql = "select count(*) from " + tableName + " where "
				+ fieldName + "=? and " + keyFieldName + "!=" + "?";
		Object[] params = { value, keyValue };
		int count = j.queryForInteger(sql, params);
		return count > 0;
	}
	
	/**
	 * 获得序列
	 * @param seqName 序列名称
	 * @return 序列值
	 */
	public static Long getSeqValue(String seqName) {
		String sql = "select "+seqName+".nextval from dual";
		long id = j.queryForLong(sql);
		if(id>100000) {
			new Exception("未知错误，请联系开发人员");
		}
		return id;
	}
	
	/**
	 * 获得最大值+1
	 * @param tableName
	 * @param fieldName
	 * @return
	 */
	public static Long getMaxValue(String tableName, String fieldName) {
		String sql = "select nvl(max("+fieldName+"),0)+1 from "+tableName;
		return j.queryForLong(sql);
	}
	
	/**
	 * 判断数据是否被引用
	 * @param tableName 表名
	 * @param fieldName 字段名
	 * @param value 字段值
	 * @return
	 */
	public static boolean fieldIsReferenced(String tableName, String fieldName,Long value) {
		String sql = "select count(*) from "+tableName+" where "+fieldName+"="+value;
		return j.queryForInteger(sql) > 0;
	}
	
	/**
	 * 判断数据是否被引用
	 * @param tableName 表名
	 * @param fieldName 字段名
	 * @param value 字段值
	 * @return
	 */
	public static boolean fieldIsReferenced(String tableName, String fieldName,String value) {
		String sql = "select count(*) from " + tableName + " where " + fieldName + "=?";
		Object[] paramArr = { value };
		return j.queryForInteger(sql, paramArr) > 0;
	}
}
