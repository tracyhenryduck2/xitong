package com.common;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import com.avatar.gdk.util.DateUtils;
import com.avatar.gdk.util.StringUtils;
import com.common.BaseActionSupport;
import com.common.DBUtils;

public class CommonAction extends BaseActionSupport {

//	private String path = "D:/entrust/doc";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 字段名
	 */
	private String fieldName;
	/**
	 * 字段值
	 */
	private String fieldValue;
	/**
	 * 主键名
	 */
	private String keyName;
	/**
	 * 主键值
	 */
	private String keyValue;
	
	/**
	 * db
	 */
	private String db;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	
	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public void isDbExist() {
		boolean result = false;
		if (keyValue != null && keyValue.trim().length() > 0) {
			result = DBUtils.isExist(tableName, fieldName, fieldValue,keyName,keyValue);
		} else {
			result = DBUtils.isExist(tableName, fieldName, fieldValue);
		}
		try {
			out = response.getWriter();
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	/**
	 * 导出具体数据
	 */
	
	public void export1() {
		String data = request.getParameter("data");
		System.out.println(data);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject("["+data+"]");
		System.out.println("jsonArray:"+jsonArray);
		if(jsonArray==null || jsonArray.size()<2) {
			return;
		}
		net.sf.json.JSONObject titleObject = jsonArray.getJSONObject(1);
		net.sf.json.JSONObject fieldObject = jsonArray.getJSONObject(2);
		net.sf.json.JSONObject extObject = jsonArray.getJSONObject(0);
		System.out.println(extObject);
		String[] titleArray = new String[titleObject.size()];
		String[] fieldArr = new String[fieldObject.size()];
		String[] extArr=new String[extObject.size()];
		for(int i=0;i<titleObject.size();i++) {
			titleArray[i] = titleObject.getString(i+"");
			fieldArr[i] = fieldObject.getString(i+"");
		}
		String fieldNames = jsonArray.getString(1);
		fieldNames.split(",");
		String[][] arrs = new String[fieldArr.length][4];
		for(int i=0;i<fieldArr.length;i++) {
			arrs[i][0] = titleArray[i];
			arrs[i][1] = fieldArr[i];
			arrs[i][2] = "1";
			arrs[i][3] = "20";
		}
		
		
		for(int i=0;i<extArr.length;i++) {
			extArr[i] = extObject.getString(i+"");
		}
		/*for(int k=0;k<extArr.length;k++){
			for(int z=0;z<4;z++){
				System.out.println(arrt[k][z]);
			}
		}*/
		ExportExcel1 export = new ExportExcel1("导出文件", response);
//		String[][] arrs = {
//				{"ID","ID","1","10"},//excel列名，数据库字段名，类型，宽度
//				{"内容","OPERATION","1","50"},
//				{"时间","OPERATIONTIME","2","20"},
//				{"IP","IP","3","20"}
//		};
		
		export.setParam(arrs);
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		for(int i=3;i<jsonArray.size();i++) {
			net.sf.json.JSONObject cObject = jsonArray.getJSONObject(i);
			System.out.println(cObject);
			Map<String,Object> map = new HashMap<String,Object>();
			for(int j=0;j<fieldArr.length;j++) {
				map.put(fieldArr[j], cObject.getString(fieldArr[j]));
				System.out.println(fieldArr[j]+"---"+cObject.getString(fieldArr[j]));
			}
			maps.add(map);
		}
		export.addData(maps,extArr);
		export.writeExcel();
	}
	public void export() {
		String data = request.getParameter("data");
		System.out.println(data);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject("["+data+"]");
		System.out.println("jsonArray:"+jsonArray);
		if(jsonArray==null || jsonArray.size()<2) {
			return;
		}
		net.sf.json.JSONObject titleObject = jsonArray.getJSONObject(0);
		net.sf.json.JSONObject fieldObject = jsonArray.getJSONObject(1);
		
		String[] titleArray = new String[titleObject.size()];
		String[] fieldArr = new String[fieldObject.size()];
		
		for(int i=0;i<titleObject.size();i++) {
			titleArray[i] = titleObject.getString(i+"");
			fieldArr[i] = fieldObject.getString(i+"");
		}
		String fieldNames = jsonArray.getString(1);
		fieldNames.split(",");
		String[][] arrs = new String[fieldArr.length][4];
		for(int i=0;i<fieldArr.length;i++) {
			arrs[i][0] = titleArray[i];
			arrs[i][1] = fieldArr[i];
			arrs[i][2] = "1";
			arrs[i][3] = "20";
		}
		String filename="";
		for(int i=2;i<jsonArray.size();i++) {
			net.sf.json.JSONObject cObject = jsonArray.getJSONObject(i);
			for(int j=0;j<fieldArr.length;j++) {			
				filename=cObject.getString(fieldArr[0]);
			}			
		}
		ExportExcel export = new ExportExcel(filename, response);
//		String[][] arrs = {
//				{"ID","ID","1","10"},//excel列名，数据库字段名，类型，宽度
//				{"内容","OPERATION","1","50"},
//				{"时间","OPERATIONTIME","2","20"},
//				{"IP","IP","3","20"}
//		};
		export.setParam(arrs);
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		for(int i=2;i<jsonArray.size();i++) {
			net.sf.json.JSONObject cObject = jsonArray.getJSONObject(i);
			Map<String,Object> map = new HashMap<String,Object>();
			for(int j=0;j<fieldArr.length;j++) {
				map.put(fieldArr[j], cObject.getString(fieldArr[j]));
				System.out.println(fieldArr[j]+"---"+cObject.getString(fieldArr[j]));				
			}
			maps.add(map);
		}
		export.addData(maps);
		export.writeExcel();
	}

	/**
	 * 导出Txt
	 */
	public void exportTxt() {
		String fileName = request.getParameter("name");
		String str = request.getParameter("data");
		if(StringUtils.isEmptyString(fileName)) {
			fileName = DateUtils.getCurrentTime("yyyyMMdd");
		}
		response.setContentType("text/plain");// 一下两行关键的设置  
        response.addHeader("Content-Disposition","attachment;filename="+fileName+".txt");// filename指定默认的名字
        ServletOutputStream  outSTr = null;
        BufferedOutputStream buff  = null;
        try {
        	outSTr = response.getOutputStream();// 建立  
        	buff = new BufferedOutputStream(outSTr);  
        	buff.write(str.getBytes("UTF-8"));  
            buff.flush();  
            buff.close();  
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
        	 try {  
                 buff.close();  
                 outSTr.close();  
             } catch (Exception e) {  
                 e.printStackTrace();  
             }  
        }
	}
	
//	/**
//	 * 导出Txt
//	 */
//	public void exp() {
//		String fileName = request.getParameter("name");
//		if(StringUtils.isEmptyString(fileName)) {
//			fileName = DateUtils.getCurrentTime("yyyyMMdd");
//		}
//		String suffixName = Common.getFileSuffix(fileName);
//		response.setContentType("text/plain");// 一下两行关键的设置  
//        response.addHeader("Content-Disposition","attachment;filename="+fileName+"."+suffixName);// filename指定默认的名字
//        ServletOutputStream  outSTr = null;
//        BufferedOutputStream buff  = null;
//        try {
//        	outSTr = response.getOutputStream();// 建立  
//        	buff = new BufferedOutputStream(outSTr);  
//        	FileInputStream fis = new FileInputStream(path+"/"+fileName);  
//	        byte []buffers=new byte[1024];  
//	        int len=0;  
//	        while((len=fis.read(buffers))!=-1){  
//	        	buff.write(buffers,0,len);  
//	        }    
//            buff.flush();  
//            buff.close();  
//        } catch(Exception e) {
//        	e.printStackTrace();
//        } finally {
//        	 try {  
//                 buff.close();  
//                 outSTr.close();  
//             } catch (Exception e) {  
//                 e.printStackTrace();  
//             }  
//        }
//	} 
	
	public static void main(String[] args) {
		
	}
}
