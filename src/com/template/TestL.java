package com.template;

import java.util.List;
import java.util.Map;

import com.avatar.db.DBFactory;
import com.avatar.db.jdbc.JdbcHandler;

public class TestL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String schema="oa";
		String tableName = "telecom_project";//fault_bill
		JdbcHandler j = DBFactory.create("write");
		String sql = "SELECT COLUMN_NAME," + //列名
				"ORDINAL_POSITION," + //列位�?1,2,3
				"IS_NULLABLE,"+ //YES,NO
				"DATA_TYPE," + //数据类型 int,varchar,date
				"COLUMN_DEFAULT," + //默认�?
				"CHARACTER_MAXIMUM_LENGTH," + //�?大字符长度，针对VARCHAR
				"COLUMN_COMMENT," + //备注
				"COLUMN_KEY FROM information_schema.COLUMNS WHERE table_schema='"+schema+"' and table_name = '"+tableName+"' ORDER BY ORDINAL_POSITION ASC "; //PRI=表示主键 MUL=外键
		List<Map<String,Object>> list = j.queryForList(sql);
		String str="{";
		for(int i=0;i<list.size();i++) {
			//str+="\""+list.get(i).get("COLUMN_COMMENT")+"\",";
			str+="{\""+list.get(i).get("COLUMN_NAME")+"\",\"\"},";
		}
		str=str.substring(0, str.length()-1)+"}";
		System.out.println(str);
	}

}
