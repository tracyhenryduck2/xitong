package com.template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import com.template.utils.TStringUtils;
import com.template.utils.Utils;

public class TJavaDao implements TBase{

	public void createTemplate(List<TableBean> columnList,
			List<TableBean> priList, List<TableBean> forList,List<TableBean> noPriList, String tableName,
			String packageName, String path) {
		
		String className = TStringUtils.tableName2ClassName(tableName);
		String bean = TStringUtils.columnName2Attr(className)+"Bean";
		String upBean = className+"Bean";
		
		String str ="";
		str +="package "+packageName+".dao;\r\n";
		str +="import java.util.ArrayList;\r\n";
		str +="import java.util.List;\r\n";
		str +="import java.util.Map;\r\n";
		str +="import com.common.BaseDAO;\r\n";
		str +="import com.common.Page;\r\n";
		str +="import "+packageName+".bean."+upBean+";\r\n";
		str +="                        \r\n";
		str +="/**                     \r\n";
		str +=" *                      \r\n";
		str +=" * "+Utils.getTableComments(tableName)+" <br>          \r\n";
		str +=" *                      \r\n";
		str +=" * @author   <br>   \r\n";
		str +=" * @taskId   <br>   \r\n";
		str +=" */                     \r\n";
		str +="public class "+className+"DAO extends BaseDAO { \r\n";
		
		str +="    /**                 \r\n";
		str +="     * 分页查询         \r\n";
		str +="     * @param page      \r\n";
		str +="     * @param           \r\n";
		str +="     * @return          \r\n";
		str +="     */                 \r\n";
		str +="    public List<Map<String,Object>> getPageList(Page page, "+upBean+" "+bean+") {   \r\n";
		str +="    	String sql =\"select a.* from "+tableName+" a \"; \r\n";
		str +="    	List<Object> objectList = new ArrayList<Object>();\r\n";
		str +="    	String sqlWhere = \" where 1=1 \";  \r\n";
		str +="    	if("+bean+"!=null) { \r\n";
		for(int i=0;i<columnList.size();i++) {
			if("int".equals(columnList.get(i).getDataType())) {
				str +="    		if("+bean+".get"+TStringUtils.tableName2ClassName(columnList.get(i).getColumnName())+"() != null) { \r\n";
				str +="    			objectList.add("+bean+".get"+TStringUtils.tableName2ClassName(columnList.get(i).getColumnName())+"());\r\n";
				str +="    			sqlWhere += \" AND a."+columnList.get(i).getColumnName()+" = ? \";\r\n";
				str +="    		} \r\n";
			} else {
				str +="    		if("+bean+".get"+TStringUtils.tableName2ClassName(columnList.get(i).getColumnName())+"() != null && "+bean+".get"+TStringUtils.tableName2ClassName(columnList.get(i).getColumnName())+"().trim().length()>0) { \r\n";
				str +="    			objectList.add("+bean+".get"+TStringUtils.tableName2ClassName(columnList.get(i).getColumnName())+"());\r\n";
				str +="    			sqlWhere += \" AND a."+columnList.get(i).getColumnName()+" = ? \";\r\n";
				str +="    		} \r\n";
			}
		}
		str +="    	}                 \r\n";
		str +="    	sql = sql + sqlWhere; \r\n";
		str +="    	Object[] pram = objectList.toArray(); \r\n";
		str +="    	if(page.getSortname()!=null && page.getSortorder()!=null) {   \r\n";
		str +="    		sql += \" order by \"+page.getSortname()+\" \" +page.getSortorder(); \r\n";
		str +="    	}                 \r\n";
		str +="    	page.setTotalRows(j.queryForInteger(\"select count(*) from "+tableName+" a \"+sqlWhere, pram));\r\n";
		str +="    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  \r\n";
		str +="    	return list;      \r\n";
		str +="    }                   \r\n";
		
		str +="}                       \r\n";
		try {
			
			String allPath = path+"\\dao\\"+className+"DAO.java";
			OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(allPath),"UTF-8");
			output.write(str);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
