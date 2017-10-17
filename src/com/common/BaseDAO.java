package com.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avatar.db.DBFactory;
import com.avatar.db.annotation.Table;
import com.avatar.db.jdbc.JdbcHandler;
import com.avatar.db.persistence.PersistenceUtil;

public class BaseDAO {
	public JdbcHandler j = DBFactory.create("write");
	/**
	 * 持久化save,生成ID
	 * @param obj
	 * @return
	 */
	public Long save(Object obj){
		Object[] bean_sql = PersistenceUtil.createInsertSQL(obj);
	    if ((bean_sql == null) || (bean_sql.length == 0))
	    {
	      return null;
	    }
	    long id=j.saveForGeneratedKey(bean_sql[0].toString(), (Object[])bean_sql[1]);
	    if(id>0){
	    	return id;
	    }else{
	    	return null;
	    }
	}
	
	/**
	 * 持久化insert
	 * @param obj
	 * @return
	 */
	public boolean insert(Object obj){
		return j.save(obj);
	}
	
	/**
	 * 持久化update
	 * @param obj
	 * @return
	 */
	public boolean update(Object obj){
		return j.update(obj);
	}
	
	/**
	 * 持久化update部分字段
	 * @param obj
	 * @return
	 */
	public boolean update(Object obj,String[] attrs){
		return j.update(obj, attrs);
	}
	
	/**
	 * 持久化查询单一
	 * @param c
	 * @param id
	 * @return
	 */
	public <T> T select(Class<T> c,Object id){
		String tname=c.getAnnotation(Table.class).name();
		return j.queryForBean(c,"select * from "+tname+" where id=?",new Object[]{id});
	}
	
	
	/**
	 * 持久化查询单一,部分参数查询
	 * @param <T>
	 * @param c
	 * @param id
	 * @param attrs(class 的属性数组)
	 * @return
	 */
	public <T> T select(Class<T> c,Object id,String[] attrs){
		String tname=c.getAnnotation(Table.class).name();
		return j.queryForBean(c,"select "+Common.array2String(attrs)+" from "+tname+" where id=?",new Object[]{id});
	}
	
	
	
	
	/**
	 * 持久化删除
	 * @param c
	 * @param id
	 * @return
	 */
	public boolean delete(Class<?> c,Object id){
		String tname=c.getAnnotation(Table.class).name();
		return j.execute("delete from "+tname+" where id=?",new Object[]{id});
	}
	
	/**
	 * 持久化批量删除
	 * @param c
	 * @param id
	 * @return
	 */
	public boolean deletes(Class<?> c,String ids){
		String tname=c.getAnnotation(Table.class).name();
		return j.execute("delete from "+tname+" where id in("+ids+")");
	}
	
	/**
	 * 持久化查询全部
	 * @param c
	 * @return
	 */
	public List<?> list(Class<?> c){
		String tname=c.getAnnotation(Table.class).name();
		return j.queryForList(c, "select * from "+tname);
	}
	
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 用于处理选出部分字段并导出
	 * @param list
	 * @param args
	 * @return
	 */
	public static List<Map<String, Object>> getExportList(
			List<Map<String, Object>> list, String[] args) {
		List<Map<String, Object>> subList = new ArrayList<Map<String, Object>>(
				list.size());
		for (Map<String, Object> map : list) {
			Map<String, Object> subMap = new HashMap<String, Object>();
			for (String arg : args) {
				String[] argArr = arg.split(":");
				if (argArr.length == 2) {
					if ("time".equals(argArr[1]))
						subMap.put(argArr[0], sdf.format(DataUtils.getLong(map.get(argArr[0]))*1000));
					else
						subMap.put(argArr[0], map.get(argArr[0]));
				} else {
					subMap.put(arg, map.get(arg));
				}
			}
			subList.add(subMap);
		}
		return subList;
	}
	

}
