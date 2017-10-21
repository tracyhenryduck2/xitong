<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp" %>  
<% 
String path = request.getContextPath();      
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>    
  <head>  
   
    <title>令牌表</title> 
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache">  
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">   
	<meta http-equiv="description" content="This is my page">    
	<script type="text/javascript">    
		function add(){      
			Dialog.open({Title:"新增", Width:600, Height:360, URL:"<%=path%>/memberManage/ClientToken!toAddClientToken.action"});   
		}      
		function mod(){      
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"编辑", Width:600, Height:360, URL:"<%=path%>/memberManage/ClientToken!toAddClientToken.action?oper=1&clientTokenBean.id="+id});
		}      
		function detail(){   
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"详情", Width:500, Height:360, URL:"<%=path%>/memberManage/ClientToken!toAddClientToken.action?read=1&oper=1&clientTokenBean.id="+id});    
		}      
		function del(){      
			if(!isCheckAny("idArr")){      
				Dialog.alert("请至少勾选一个！");   
				return;   
			}    
			Dialog.confirm("确定删除吗", function(){     
				var action_tmp = form1.action; 
				form1.action = "<%=path%>/memberManage/ClientToken!delClientTokens.action";
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
		function search() {  
			Dialog.open({Title:"查询", Width:650, Height:220, URL:"<%=path%>/memberManage/ClientToken!search.action",OnLoad:function(){
			     this.innerDoc.getElementById("client_id").value = $("#client_id").val();
			     this.innerDoc.getElementById("access_token").value = $("#access_token").val();
			     this.innerDoc.getElementById("refresh_token").value = $("#refresh_token").val();
			     this.innerDoc.getElementById("expires_in").value = $("#expires_in").val();
			     this.innerDoc.getElementById("expires_in_refresh").value = $("#expires_in_refresh").val();
			     this.innerDoc.getElementById("time").value = $("#time").val();
           }
			});    
		}      
		
	</script>
  </head> 
  <body>  
  <GF:BodyCaption label="令牌表" ico="images/ico/user.gif"> 
  	<GF:ToolBar id="123">     
	  	<GF:ToolBarItem id="search" label="查询" ico="images/ico/search.gif" onclick="search();" />    
	  	<GF:ToolBarItem id="add" label="添加" ico="images/ico/add.gif" 		onclick="add();" />   
	  	<GF:ToolBarItem id="edit" label="编辑" ico="images/ico/edit.gif" 	onclick="mod();" />   
	  	<GF:ToolBarItem id="delete" label="删除" ico="images/ico/delete.gif" onclick="del();"/> 
	  	<GF:ToolBarItem id="detail" label="详情" ico="images/ico/detail.gif" onclick="detail();"/>     
	  	<GF:ToolBarItem id="export" label="导出" ico="images/ico/export.gif" onclick="exportExcel();"/>
	  	<GF:ToolBarItem id="refresh" label="刷新" ico="images/ico/refresh.gif" onclick="refresh();"/>  
	</GF:ToolBar>   
    <form action="<%=path%>/memberManage/ClientToken!list.action" name="form1" id="form1" method="get">  
   
			<input type="hidden" name="clientTokenBean.clientId" id="clientId" value="${clientTokenBean.clientId}"/> 
			<input type="hidden" name="clientTokenBean.accessToken" id="accessToken" value="${clientTokenBean.accessToken}"/> 
			<input type="hidden" name="clientTokenBean.refreshToken" id="refreshToken" value="${clientTokenBean.refreshToken}"/> 
			<input type="hidden" name="clientTokenBean.expiresIn" id="expiresIn" value="${clientTokenBean.expiresIn}"/> 
			<input type="hidden" name="clientTokenBean.expiresInRefresh" id="expiresInRefresh" value="${clientTokenBean.expiresInRefresh}"/> 
			<input type="hidden" name="clientTokenBean.time" id="time" value="${clientTokenBean.time}"/> 
		   	<input type="hidden" name="sortname" value="${page.sortname}"/>    
		   	<input type="hidden" name="sortorder"  value="${page.sortorder }"/>
		   	<input type="hidden" name="pageSize" value="${page.pageSize}"/>    
		   	<input type="hidden" name="pageNo"  value="${page.pageNo }"/>      
			<table id="table1" width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0"> 
			   	 <thead>
			   		<tr>  
			   			<th><span><input type="checkbox" onClick="checkAll(this,'idArr')"  width="20"/></span></th>     
			   			<th sortname="client_id" width="10%">clientid</th>	   
			   			<th sortname="access_token" width="10%">操作令牌</th>	   
			   			<th sortname="refresh_token" width="10%">刷新令牌</th>	   
			   			<th sortname="expires_in" width="10%">过期时间（单位:秒）</th>	   
			   			<th sortname="expires_in_refresh" width="10%">刷新令牌过期时间(单位:秒)</th>	   
			   			<th sortname="time" width="10%">时间</th>	   
			   		</tr> 
			   	</thead>
			   	<tbody> 
			   		<s:iterator value="#request.list" id="map"> 
		   			 <tr> 
		   			 	 <td align="left"><input type="checkbox" name="idArr"  value="${map.id}"/></td> 
		   			 	 <td>${map.client_id}</td>    
		   			 	 <td>${map.access_token}</td>    
		   			 	 <td>${map.refresh_token}</td>    
		   			 	 <td>${map.expires_in}</td>    
		   			 	 <td>${map.expires_in_refresh}</td>    
		   			 	 <td>${map.time}</td>    
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
