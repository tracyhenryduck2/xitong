package com.template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.template.utils.TStringUtils;

public class TStrust {

	public static void createXMLFile(String tableName, String packageName,String folderName, String path) {
		String className = TStringUtils.tableName2ClassName(tableName);
		String varClassName = TStringUtils.columnName2Attr(tableName);
		
		String str = "";
		str +="<action name=\""+className+"\" class=\""+packageName+".action."+className+"Action\">\r\n";
		str +="	 		<result name=\"toAdd"+className+"\">/"+folderName+"/"+varClassName+"_edit.jsp</result>\r\n";
		str +="	 		<result name=\"list\">/"+folderName+"/"+varClassName+"_list.jsp</result>\r\n";
		str +="	 		<result name=\"search\">/"+folderName+"/"+varClassName+"_search.jsp</result>\r\n";
		str +="</action>\r\n";
		try {
			String allPath = path+"\\"+className+".xml";
			OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(allPath),"UTF-8");
			output.write(str);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
