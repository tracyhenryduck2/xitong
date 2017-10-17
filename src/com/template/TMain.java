package com.template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.template.utils.Utils;

public class TMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String packageName = "com.memberManage";
		String[] pakageArr = packageName.split("\\.");
		String folderName=pakageArr[pakageArr.length-1];
		String schema="point";
		String tableName = "client";//fault_bill
		String path = "d:\\work1\\template\\"+packageName;
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
		f = new File(path+"\\action");
		if(!f.exists()) {
			f.mkdirs();
		}
		f = new File(path+"\\dao");
		if(!f.exists()) {
			f.mkdirs();
		}
		f = new File(path+"\\bean");
		if(!f.exists()) {
			f.mkdirs();
		}
		List<TableBean> columnList = new ArrayList<TableBean>();
		List<TableBean> priList = new ArrayList<TableBean>();
		List<TableBean> forList = new ArrayList<TableBean>();
		List<TableBean> noPriList = new ArrayList<TableBean>();
		Utils.parseColumns(schema,tableName,columnList,priList,forList,noPriList);
		TJavaBean tBean = new TJavaBean();
		tBean.createTemplate(columnList, priList, forList, noPriList, tableName, packageName, path);
		TJavaAction javaAction = new TJavaAction();
		javaAction.createTemplate(columnList, priList, forList,noPriList, tableName, packageName, path);
	    TJavaDao dao = new TJavaDao();
		dao.createTemplate(columnList, priList, forList, noPriList, tableName, packageName, path);
		TJavaJspList jsp = new TJavaJspList();
		jsp.createTemplate(columnList, priList, forList, noPriList, tableName, folderName, path);
		TJavaJspEdit t = new TJavaJspEdit();
		t.createTemplate(columnList, priList, forList, noPriList, tableName, folderName, path);
		TStrust.createXMLFile(tableName, packageName,folderName, path);
		TJavaJspSearch search = new TJavaJspSearch();
		search.createTemplate(columnList, priList, forList, noPriList, tableName, folderName, path);
	}

}
