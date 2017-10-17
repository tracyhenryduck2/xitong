package com.template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import com.template.utils.TStringUtils;

public class TJavaJspEdit implements TBase {

	public void createTemplate(List<TableBean> columnList,
			List<TableBean> priList, List<TableBean> forList,
			List<TableBean> noPriList, String tableName, String folderName,
			String path) {
		String className = TStringUtils.tableName2ClassName(tableName);
		String bean = TStringUtils.columnName2Attr(tableName)+"Bean";
		
		String str = "";
		str +="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>  \r\n";
		str +="<%@ include file=\"/common/taglibs.jsp\" %>  \r\n";
		str +="<%                                       \r\n";
		str +="String path = request.getContextPath();  \r\n";
		str +="%>                                       \r\n";
		str +="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n";
		str +="<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n";
		str +="<head>                                   \r\n";
		str +="<title></title>                          \r\n";
		str +="<script type=\"text/javascript\">        \r\n";
		str +=" var isCommit=false \r\n";
		str +="	function save(){                        \r\n";
		str +="		if($(\"#form1\").valid()) {           \r\n";
		str +="        if(isCommit==false){   \r\n";
		str +="         isCommit=true       \r\n";
		str +="			form1.submit();                     \r\n";
		str +="      }                 \r\n";
		str +="		}                                     \r\n";
		str +="	}                                       \r\n";
		str +="	                                        \r\n";
		str +="	function result(messageType, message){  \r\n";
		str +="		if(messageType==\"error\"){           \r\n";
		str +="			Dialog.error(message);				      \r\n";
		str +="		} else if (messageType == \"reload_success\") {   \r\n";
		str +="			Dialog.alert(message,function(){    \r\n";
		str +="				Dialog.opener().location.reload(); //;= \"<%=path%>/"+folderName+"/"+className+"!list.action\";   \r\n";
		str +="				parentDialog.close();             \r\n";
		str +="			});                                 \r\n";
		str +="		} else if(messageType == \"exception\") { \r\n";
		str +="			                                    \r\n";
		str +="		}                                     \r\n";
		str +="	}                                       \r\n";
		str +="	                                        \r\n";
		str +="	$(function(){                           \r\n";
		str +="		/* form1表单进行验证 */               \r\n";
		str +="		$(\"#form1\").validate({              \r\n";
		str +="			rules:{                             \r\n";
		
		for(int i=0;i<noPriList.size();i++) {
			String fName = TStringUtils.columnName2Attr(noPriList.get(i).getColumnName());
			str +="			    \""+bean+"."+fName+"\":{            \r\n";
			String tmpStr = "";
			if("NO".equals(noPriList.get(i).getIsNullable())) {
				tmpStr += ",required : true";
			}
			if("int".equals(noPriList.get(i).getDataType())) {
				tmpStr += ",number:true,range:[0,9999999999]";
			} else {
				tmpStr += ",CNRangeLength:[0,"+noPriList.get(i).getCharacterMaximumLength()+"]";
			}
 			str +="			        "+tmpStr.substring(1)+"\r\n";
 			if(noPriList.size()==i+1) {
 				str +="			    }                              \r\n";
 			} else {
 				str +="			    },                              \r\n";
 			}
		}
		str +="			},                                  \r\n";
		str +="			messages:{                          \r\n";
		str +="//			    \""+bean+".code\":{          \r\n";
		str +="//				    required : \"\",CNRangeLength:\"\"  \r\n";
		str +="//			    },                            \r\n";
		
		str +="			}                                   \r\n";
		str +="		});                                   \r\n";
		str +="	});                                     \r\n";
		str +="	                                        \r\n";
		str +="	                                        \r\n";
		str +="</script>                                \r\n";
		str +="</head>                                  \r\n";
		str +="<body>                                   \r\n";
		str +="<form name=\"form1\" id=\"form1\" action=\"<%=path %>/"+folderName+"/"+className+"!add"+className+".action\" method=\"post\" target=\"fram\" >   \r\n";
		str +="<input type=\"hidden\" name=\"oper\" value=\"${oper}\" />\r\n";
		str +="<input type=\"hidden\" name=\""+bean+".id\" id=\"id\" value=\"${"+bean+".id }\"/>  \r\n";
		str +="<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"GF-grid\"> \r\n";
		if(noPriList.size()>=10) {
			for(int i=0;i<noPriList.size();i++) {
				
				str +="  <tr>                                    \r\n";
				String mark = "";
				if("NO".equals(noPriList.get(i).getIsNullable())) {
					mark = "*";
				}
				String fName = TStringUtils.columnName2Attr(noPriList.get(i).getColumnName());
				
				int flag = 0;
				String value = "";
				String comments = noPriList.get(i).getColumnComment();
				if(noPriList.get(i).getColumnComment()!=null && noPriList.get(i).getColumnComment().startsWith("list#")) {
					String[] comArr = comments.split("#");
					if(comArr.length==3) {
						value = comArr[2];
						flag = 1;
						comments = comArr[1];
					}
				}
				if(i==0) {
					str +="    	<td align=\"right\" width=\"20%\" > \r\n";
				} else {
					str +="    	<td align=\"right\"  > \r\n";
				}
				str +="                            "+comments+"<span class=\"mark\">"+mark+"</span>   \r\n";
				str +="        </td>                            \r\n";
				str +="        <td>                             \r\n";
				if("MUL".equals(noPriList.get(i).getColumnKey())) { //外键
					String ftableName = noPriList.get(i).getColumnName().substring(0, noPriList.get(i).getColumnName().length()-3);
					str +="            <s:select list=\"#request."+TStringUtils.columnName2Attr(ftableName)+"List\"  cssClass=\"GF-field\" name=\""+bean+"."+fName+"\" id=\""+fName+"\"   theme=\"simple\" listKey=\"id\" listValue=\"name\"  value=\"#request."+bean+"."+fName+"\" ></s:select>\r\n";
				}
				else if(flag==1) {
					str +="            <s:select list=\"#{"+value+"}\"  cssClass=\"GF-field\" name=\""+bean+"."+fName+"\" id=\""+fName+"\"   theme=\"simple\"  value=\"#request."+bean+"."+fName+"\" ></s:select>\r\n ";
				}
				else {
					str +="            <input type=\"text\" name=\""+bean+"."+fName+"\" id=\""+fName+"\"  value=\"${"+bean+"."+fName+"}\" class=\"GF-field\"/>   \r\n";
				}
				str +="        </td>                            \r\n";
				i++;
				if(i<noPriList.size()) {
					mark = "";
					if("NO".equals(noPriList.get(i).getIsNullable())) {
						mark = "*";
					}
					fName = TStringUtils.columnName2Attr(noPriList.get(i).getColumnName());
					int flag1 = 0;
					String value1 = "";
					String comments1 = noPriList.get(i).getColumnComment();
					if(noPriList.get(i).getColumnComment()!=null && noPriList.get(i).getColumnComment().startsWith("list#")) {
						String[] comArr = comments1.split("#");
						if(comArr.length==3) {
							value1 = comArr[2];
							flag1 = 1;
							comments1 = comArr[1];
						}
					}
					if(i==1) {
						str +="    	<td align=\"right\" width=\"20%\" > \r\n";
					} else {
						str +="    	<td align=\"right\"  > \r\n";
					}
					str +="                            "+comments1+"<span class=\"mark\">"+mark+"</span>   \r\n";
					str +="        </td>                            \r\n";
					str +="        <td>                             \r\n";
					if("MUL".equals(noPriList.get(i).getColumnKey())) { //外键
						String ftableName = noPriList.get(i).getColumnName().substring(0, noPriList.get(i).getColumnName().length()-3);
						str +="            <s:select list=\"#request."+TStringUtils.columnName2Attr(ftableName)+"List\"  cssClass=\"GF-field\" name=\""+bean+"."+fName+"\" id=\""+fName+"\"   theme=\"simple\" listKey=\"id\" listValue=\"name\"  value=\"#request."+bean+"."+fName+"\" ></s:select>\r\n";
					}
					else if(flag1==1) {
						str +="            <s:select list=\"#{"+value1+"}\"  cssClass=\"GF-field\" name=\""+bean+"."+fName+"\" id=\""+fName+"\"   theme=\"simple\"  value=\"#request."+bean+"."+fName+"\" ></s:select>\r\n ";
					} else {
						str +="            <input type=\"text\" name=\""+bean+"."+fName+"\" id=\""+fName+"\"  value=\"${"+bean+"."+fName+"}\" class=\"GF-field\"/>   \r\n";
					}
					str +="        </td>                            \r\n";
				} else {
					str +="    	<td align=\"right\" width=\"20%\">&nbsp;</td> \r\n";
					str +="    	<td></td>                        \r\n";
				}
				str +="  </tr>                                \r\n";
			}
			str +="  <tr>                                \r\n";
			str +="      <td>&nbsp;</td>		\r\n";
			str +="      <td colspan=\"3\"><input type=\"button\" name=\"保存\" value=\"保存\" onclick=\"save();\" class=\"GF-btn\"/></td>		\r\n";
			str +="  </tr>                                \r\n";
		} else {
			for(int i=0;i<noPriList.size();i++) {
				String mark = "";
				if("NO".equals(noPriList.get(i).getIsNullable())) {
					mark = "*";
				}
				String fName = TStringUtils.columnName2Attr(noPriList.get(i).getColumnName());
				int flag = 0;
				String value = "";
				String comments = noPriList.get(i).getColumnComment();
				if(noPriList.get(i).getColumnComment()!=null && noPriList.get(i).getColumnComment().startsWith("list#")) {
					String[] comArr = comments.split("#");
					if(comArr.length==3) {
						value = comArr[2];
						flag = 1;
						comments = comArr[1];
					}
				}
				
				str +="  <tr>                                    \r\n";
				if(i==0) {
					str +="    	<td align=\"right\" width=\"30%\" > \r\n";
				} else {
					str +="    	<td align=\"right\" > \r\n";
				}
				
				str +="                            "+comments+"<span class=\"mark\">"+mark+"</span>   \r\n";
				str +="        </td>                            \r\n";
				str +="        <td>                             \r\n";
				if("MUL".equals(noPriList.get(i).getColumnKey())) { //外键
					String ftableName = noPriList.get(i).getColumnName().substring(0, noPriList.get(i).getColumnName().length()-3);
					str +="            <s:select list=\"#request."+TStringUtils.columnName2Attr(ftableName)+"List\"  cssClass=\"GF-field\" name=\""+bean+"."+fName+"\" id=\""+fName+"\"   theme=\"simple\" listKey=\"id\" listValue=\"name\"  value=\"#request."+bean+"."+fName+"\" ></s:select>\r\n";
				}
				else if(flag==1) {
					str +="            <s:select list=\"#{"+value+"}\"  cssClass=\"GF-field\" name=\""+bean+"."+fName+"\" id=\""+fName+"\"   theme=\"simple\"  value=\"#request."+bean+"."+fName+"\" ></s:select>\r\n ";
				}
				else {
					str +="            <input type=\"text\" name=\""+bean+"."+fName+"\" id=\""+fName+"\"  value=\"${"+bean+"."+fName+"}\" class=\"GF-field\"/>   \r\n";
				}
				str +="        </td>                            \r\n";
				str +="  </tr>                                \r\n";
			}
			str +="  <tr>                                \r\n";
			str +="      <td>&nbsp;</td>		\r\n";
			str +="      <td><input type=\"button\" name=\"提交\" value=\"提交\" onclick=\"save();\" class=\"GF-btn\"/></td>		\r\n";
			str +="  </tr>                                \r\n";
		}
		str +="</table>                                 \r\n";
		str +="<iframe name=\"fram\" id=\"fram\" style=\"display:none\"></iframe>   \r\n";
		str +="</form>                                  \r\n";
		str +="</body>                                  \r\n";
		str +="</html>                                  \r\n";
		try {
			String allPath = path+"\\"+TStringUtils.columnName2Attr(tableName)+"_edit.jsp";
			OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(allPath),"UTF-8");
			output.write(str);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
