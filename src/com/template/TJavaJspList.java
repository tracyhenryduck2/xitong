package com.template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import com.template.utils.TStringUtils;
import com.template.utils.Utils;

public class TJavaJspList implements TBase {

	public void createTemplate(List<TableBean> columnList,
			List<TableBean> priList, List<TableBean> forList,
			List<TableBean> noPriList, String tableName, String folderName,
			String path) {
		String className = TStringUtils.tableName2ClassName(tableName);
		String bean = TStringUtils.columnName2Attr(tableName)+"Bean";
		String tableDesc = Utils.getTableComments(tableName);
		String str = "";
		str +="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%> \r\n";
		str +="<%@ include file=\"/common/taglibs.jsp\" %>  \r\n";
		str +="<% \r\n";
		str +="String path = request.getContextPath();      \r\n";
		str +="%> \r\n";
		str +="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"> \r\n";
		str +="<html>    \r\n";
		str +="  <head>  \r\n";
		str +="   \r\n";
		str +="    <title>"+tableDesc+"</title> \r\n";
		str +="	<meta http-equiv=\"pragma\" content=\"no-cache\">  \r\n";
		str +="	<meta http-equiv=\"cache-control\" content=\"no-cache\">  \r\n";
		str +="	<meta http-equiv=\"expires\" content=\"0\"> \r\n";
		str +="	<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">   \r\n";
		str +="	<meta http-equiv=\"description\" content=\"This is my page\">    \r\n";
		str +="	<script type=\"text/javascript\">    \r\n";
		str +="		function add(){      \r\n";
		str +="			Dialog.open({Title:\"新增\", Width:600, Height:360, URL:\"<%=path%>/"+folderName+"/"+className+"!toAdd"+className+".action\"});   \r\n";
		str +="		}      \r\n";
		str +="		function mod(){      \r\n";
		str +="			var id = getCheckRowValue();     \r\n";
		str +="			if(id==null) {     \r\n";
		str +="				return;   \r\n";
		str +="			}    \r\n";
		str +="			Dialog.open({Title:\"编辑\", Width:600, Height:360, URL:\"<%=path%>/"+folderName+"/"+className+"!toAdd"+className+".action?oper=1&"+bean+".id=\"+id});\r\n";
		str +="		}      \r\n";
		str +="		function detail(){   \r\n";
		str +="			var id = getCheckRowValue();     \r\n";
		str +="			if(id==null) {     \r\n";
		str +="				return;   \r\n";
		str +="			}    \r\n";
		str +="			Dialog.open({Title:\"详情\", Width:500, Height:360, URL:\"<%=path%>/"+folderName+"/"+className+"!toAdd"+className+".action?read=1&oper=1&"+bean+".id=\"+id});    \r\n";
		str +="		}      \r\n";
		str +="		function del(){      \r\n";
		str +="			if(!isCheckAny(\"idArr\")){      \r\n";
		str +="				Dialog.alert(\"请至少勾选一个！\");   \r\n";
		str +="				return;   \r\n";
		str +="			}    \r\n";
		str +="			Dialog.confirm(\"确定删除吗\", function(){     \r\n";
		str +="				var action_tmp = form1.action; \r\n";
		str +="				form1.action = \"<%=path%>/"+folderName+"/"+className+"!del"+className+"s.action\";\r\n";
		str +="				form1.target = \"fram\";\r\n";
		str +="				form1.submit();  \r\n";
		str +="				form1.action = action_tmp;     \r\n";
		str +="				form1.target = \"\";    \r\n";
		str +="			}, function(){     \r\n";
		str +="				//�?      \r\n";
		str +="			});  \r\n";
		str +="		}      \r\n";
		str +="		\r\n";
		str +="		function result(type, message) {   \r\n";
		str +="			if(\"reload_success\" == type) { \r\n";
		str +="				Dialog.alert(message,function(){      \r\n";
		str +="					form1.submit();\r\n";
		str +="				});\r\n";
		str +="			} else if(\"error\" == type) {   \r\n";
		str +="				Dialog.error(message);  \r\n";
		str +="			}    \r\n";
		str +="		}      \r\n";
		
		String colStr = "";
		String colValueStr = "";
		String searchStr = "";
		String hiddenStr = "";
		for(int i=0;i<noPriList.size();i++) {
			String fName = TStringUtils.columnName2Attr(noPriList.get(i).getColumnName());
			hiddenStr +="			<input type=\"hidden\" name=\""+bean+"."+fName+"\" id=\""+fName+"\" value=\"${"+bean+"."+fName+"}\"/> \r\n";
			String comments = noPriList.get(i).getColumnComment();
			if(comments!=null && comments.startsWith("list#")) {
				String[] comArr = comments.split("#");
				if(comArr!=null && comArr.length==3) {
					comments = comArr[1];
				}
			}
			String columnName=noPriList.get(i).getColumnName();
			colStr +="			   			<th sortname=\""+columnName+"\" width=\"10%\">"+comments+"</th>	   \r\n";
			colValueStr +="		   			 	 <td>${map."+columnName+"}</td>    \r\n";
			searchStr +="			     this.innerDoc.getElementById(\""+columnName+"\").value = $(\"#"+columnName+"\").val();\r\n";
		}
		
		str +="		function search() {  \r\n";
		str +="			Dialog.open({Title:\"查询\", Width:650, Height:220, URL:\"<%=path%>/"+folderName+"/"+className+"!search.action\",OnLoad:function(){\r\n";
		str += searchStr;
		str +="           }\r\n";
		str +="			});    \r\n";
		str +="		}      \r\n";
		str +="		\r\n";
		str +="	</script>\r\n";
		str +="  </head> \r\n";
		str +="  <body>  \r\n";
		str +="  <GF:BodyCaption label=\""+tableDesc+"\" ico=\"images/ico/user.gif\"> \r\n";
		str +="  	<GF:ToolBar id=\"123\">     \r\n";
		str +="	  	<GF:ToolBarItem id=\"search\" label=\"查询\" ico=\"images/ico/search.gif\" onclick=\"search();\" />    \r\n";
		str +="	  	<GF:ToolBarItem id=\"add\" label=\"添加\" ico=\"images/ico/add.gif\" 		onclick=\"add();\" />   \r\n";
		str +="	  	<GF:ToolBarItem id=\"edit\" label=\"编辑\" ico=\"images/ico/edit.gif\" 	onclick=\"mod();\" />   \r\n";
		str +="	  	<GF:ToolBarItem id=\"delete\" label=\"删除\" ico=\"images/ico/delete.gif\" onclick=\"del();\"/> \r\n";
		str +="	  	<GF:ToolBarItem id=\"detail\" label=\"详情\" ico=\"images/ico/detail.gif\" onclick=\"detail();\"/>     \r\n";
		str +="	  	<GF:ToolBarItem id=\"export\" label=\"导出\" ico=\"images/ico/export.gif\" onclick=\"exportExcel();\"/>\r\n";
		str +="	  	<GF:ToolBarItem id=\"refresh\" label=\"刷新\" ico=\"images/ico/refresh.gif\" onclick=\"refresh();\"/>  \r\n";
		str +="	</GF:ToolBar>   \r\n";
		str +="    <form action=\"<%=path%>/"+folderName+"/"+className+"!list.action\" name=\"form1\" id=\"form1\" method=\"get\">  \r\n";
		str +="   \r\n";
		str +=hiddenStr;
		str +="		   	<input type=\"hidden\" name=\"sortname\" value=\"${page.sortname}\"/>    \r\n";
		str +="		   	<input type=\"hidden\" name=\"sortorder\"  value=\"${page.sortorder }\"/>\r\n";
		str +="		   	<input type=\"hidden\" name=\"pageSize\" value=\"${page.pageSize}\"/>    \r\n";
		str +="		   	<input type=\"hidden\" name=\"pageNo\"  value=\"${page.pageNo }\"/>      \r\n";
		
		str +="			<table id=\"table1\" width=\"100%\" height=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \r\n";
		str +="			   	 <thead>\r\n";
		str +="			   		<tr>  \r\n";
		str +="			   			<th><span><input type=\"checkbox\" onClick=\"checkAll(this,'idArr')\"  width=\"20\"/></span></th>     \r\n";
		
		str += colStr;

		str +="			   		</tr> \r\n";
		str +="			   	</thead>\r\n";
		str +="			   	<tbody> \r\n";
		str +="			   		<s:iterator value=\"#request.list\" id=\"map\"> \r\n";
		str +="		   			 <tr> \r\n";
		str +="		   			 	 <td align=\"left\"><input type=\"checkbox\" name=\"idArr\"  value=\"${map.id}\"/></td> \r\n";
		str += colValueStr;
		str +="		   			 </tr>\r\n";
		str +="		   			 </s:iterator> 	    \r\n";
		str +="		   		</tbody>		    		  \r\n";
		str +="   			</table>\r\n";
		str +="   			<GF:Pagination formName=\"form1\" pageNo=\"${page.pageNo }\" pageSize=\"${page.pageSize }\" totalRows=\"${page.totalRows }\"/> \r\n";
		str +="   </form>\r\n";
		str +="   </GF:BodyCaption>    \r\n";
		str +="   <iframe name=\"fram\" id=\"fram\" style=\"display:none\"></iframe>    \r\n";
		str +="  </body> \r\n";
		str +="  <script type=\"text/javascript\">   \r\n";
		str +="   	$(function(){      \r\n";
		str +="   		/* 渲染表格 DataGrid */      \r\n";
		str +="   		$(\"#table1\").render().sort(\"form1\");     \r\n";
		str +="   	});  \r\n";
		str +="</script> \r\n";
		str +="</html>   \r\n";
		try {
			String allPath = path+"\\"+TStringUtils.columnName2Attr(tableName)+"_list.jsp";
			OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(allPath),"UTF-8");
			output.write(str);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
