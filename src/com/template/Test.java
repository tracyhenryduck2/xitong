package com.template;

import java.util.List;
import java.util.Map;

import com.avatar.db.DBFactory;
import com.avatar.db.jdbc.JdbcHandler;

public class Test {

	public static void main(String[] args) {
		JdbcHandler j = DBFactory.create("write");
		List<Map<String,Object>> list=j.queryForList("SELECT role_id from func_role where func_id=17");
		for(Map<String,Object> map:list){
			j.execute("insert into func_role(role_id,func_id) values("+map.get("role_id")+",37)");
		}
	}
	
}
