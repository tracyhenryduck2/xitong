package com.template.utils;

import java.util.List;
import java.util.Map;
import com.avatar.db.DBFactory;
import com.avatar.db.jdbc.JdbcHandler;
import com.template.TableBean;

public class Utils {

	public static String getTableComments(String tableName) {
		JdbcHandler j = DBFactory.create("write");
		return j.queryForObject("SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE TABLE_NAME='"+tableName+"' ")+"";
	}
	
	public static void parseColumns(String schemaName,String tableName,List<TableBean> columnList,List<TableBean> priList, List<TableBean> forList,List<TableBean> noPriList) {
		JdbcHandler j = DBFactory.create("write");
		String sql = "SELECT COLUMN_NAME," + //列名
				"ORDINAL_POSITION," + //列位�?1,2,3
				"IS_NULLABLE,"+ //YES,NO
				"DATA_TYPE," + //数据类型 int,varchar,date
				"COLUMN_DEFAULT," + //默认�?
				"CHARACTER_MAXIMUM_LENGTH," + //�?大字符长度，针对VARCHAR
				"COLUMN_COMMENT," + //备注
				"COLUMN_KEY FROM information_schema.COLUMNS WHERE table_schema='"+schemaName+"' and table_name = '"+tableName+"' ORDER BY ORDINAL_POSITION ASC "; //PRI=表示主键 MUL=外键
		System.out.println(sql);
		List<Map<String,Object>> list = j.queryForList(sql);
		for(int i=0;i<list.size();i++) {
			TableBean bean = new TableBean();
			bean.setColumnName(list.get(i).get("COLUMN_NAME")+"");
			bean.setIsNullable(list.get(i).get("IS_NULLABLE")+"");
			bean.setOrdinalPosition(list.get(i).get("ORDINAL_POSITION")+"");
			bean.setDataType(list.get(i).get("DATA_TYPE")+"");
			bean.setColumnDefault(list.get(i).get("COLUMN_DEFAULT")+"");
			bean.setCharacterMaximumLength(list.get(i).get("CHARACTER_MAXIMUM_LENGTH")+"");
			bean.setColumnComment(list.get(i).get("COLUMN_COMMENT")+"");
			bean.setColumnKey(list.get(i).get("COLUMN_KEY")+"");
			columnList.add(bean);
			if("PRI".equals(bean.getColumnKey())) {
				priList.add(bean);
			}
			else {
				noPriList.add(bean);
			}
			if("MUL".equals(bean.getColumnKey())) {
				forList.add(bean);
			}
		}
	}
	
}
