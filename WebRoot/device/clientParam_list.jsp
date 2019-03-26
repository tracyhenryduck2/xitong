<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp" %>  
<% 
String path = request.getContextPath();      
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>    
  <head>  
   
    <title>参数表</title> 
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache">  
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">   
	<meta http-equiv="description" content="This is my page">    
	<script type="text/javascript">    
		function add(){  
			Dialog.open({Title:"新增", Width:600, Height:360, URL:"<%=path%>/device/ClientParam!toAddClientParam.action?clientParamBean.pid=${clientParamBean.pid}"});   
		}                 
		function del(){      
			if(!isCheckAny("idArr")){      
				Dialog.alert("请至少勾选一个！");   
				return;   
			}    
			Dialog.confirm("确定删除吗", function(){     
				var action_tmp = form1.action; 
				form1.action = "<%=path%>/device/ClientParam!abandonClientParams.action";
				form1.target = "fram";
				form1.submit();  
				form1.action = action_tmp;     
				form1.target = "";    
			}, function(){     
				//�?      
			});  
		}      
		
		function result(type, message) {   
			if("reload_success" == type) { 
				Dialog.alert(message,function(){      
					form1.submit();
				});
			} else if("error" == type) {   
				Dialog.error(message);  
			}    
		}            
		
	</script>
  </head> 
  <body>  
  <GF:BodyCaption label="参数表" ico="images/ico/user.gif"> 
  	<GF:ToolBar id="123">        
	  	<GF:ToolBarItem id="add" label="添加" ico="images/ico/add.gif" 		onclick="add();" />     
	  	<GF:ToolBarItem id="delete" label="删除" ico="images/ico/delete.gif" onclick="del();"/>    
	  	<GF:ToolBarItem id="export" label="导出" ico="images/ico/export.gif" onclick="exportExcel();"/>
	  	<GF:ToolBarItem id="refresh" label="刷新" ico="images/ico/refresh.gif" onclick="refresh();"/>  
	</GF:ToolBar>   
    <form action="<%=path%>/device/ClientParam!list.action" name="form1" id="form1" method="get">  
   
			<input type="hidden" name="clientParamBean.name" id="name" value="${clientParamBean.name}"/> 
			<input type="hidden" name="clientParamBean.type" id="type" value="${clientParamBean.type}"/> 
			<input type="hidden" name="clientParamBean.min" id="min" value="${clientParamBean.min}"/> 
			<input type="hidden" name="clientParamBean.max" id="max" value="${clientParamBean.max}"/> 
			<input type="hidden" name="clientParamBean.abandoned" id="abandoned" value="${clientParamBean.abandoned}"/> 
			<input type="hidden" name="clientParamBean.ctime" id="ctime" value="${clientParamBean.ctime}"/> 
			<input type="hidden" name="clientParamBean.atime" id="atime" value="${clientParamBean.atime}"/> 
			<input type="hidden" name="clientParamBean.pid" id="pid" value="${clientParamBean.pid}"/> 
		   	<input type="hidden" name="sortname" value="${page.sortname}"/>    
		   	<input type="hidden" name="sortorder"  value="${page.sortorder }"/>
		   	<input type="hidden" name="pageSize" value="${page.pageSize}"/>    
		   	<input type="hidden" name="pageNo"  value="${page.pageNo }"/>      
			<table id="table1" width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0"> 
			   	 <thead>
			   		<tr>  
			   			<th width="2%"><span><input type="checkbox" onClick="checkAll(this,'idArr')"  width="20"/></span></th>     
			   			<th sortname="name" width="10%">名称</th>	   
			   			<th sortname="type" width="10%">类型</th>	   
			   			<th sortname="min" width="10%">最小值</th>	   
			   			<th sortname="max" width="10%">最大值</th>	   
			   			<th sortname="abandoned" width="10%">是否弃用</th>	   
			   			<th sortname="ctime" width="10%">创建时间</th>	   
			   			<th sortname="atime" width="10%">弃用时间</th>	   	   
			   		</tr> 
			   	</thead>
			   	<tbody> 
			   		<s:iterator value="#request.list" id="map"> 
		   			 <tr> 
		   			 	 <td align="left"><input type="checkbox" name="idArr"  value="${map.id}"/></td> 
		   			 	 <td>${map.name}</td>    
		   			 	 <td><s:if test="#map.type==1">Int</s:if><s:elseif test="#map.type==2">String</s:elseif></td>    
		   			 	 <td>${map.min}</td>    
		   			 	 <td>${map.max}</td>    
		   			 	 <td> <s:if test="#map.abandoned==1">已废弃</s:if><s:else>可使用</s:else></td>    
		   			 	 <td><GF:ConvertTime value="${map.ctime}" format="yyyy-MM-dd HH:mm:ss"/></td>    
		   			 	 <td><GF:ConvertTime value="${map.atime}" format="yyyy-MM-dd HH:mm:ss"/></td>     
		   			 </tr>
		   			 </s:iterator> 	    
		   		</tbody>		    		  
   			</table>
   			<GF:Pagination formName="form1" pageNo="${page.pageNo }" pageSize="${page.pageSize }" totalRows="${page.totalRows }"/> 
   </form>
   </GF:BodyCaption>    
   <iframe name="fram" id="fram" style="display:none"></iframe>    
  </body> 
  <script type="text/javascript">   
   	$(function(){      
   		/* 渲染表格 DataGrid */      
   		$("#table1").render().sort("form1");     
   	});  
</script> 
</html>   
