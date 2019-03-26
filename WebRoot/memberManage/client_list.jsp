<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp" %>  
<% 
String path = request.getContextPath();      
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>    
  <head>  
   
    <title>客户</title> 
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache">  
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">   
	<meta http-equiv="description" content="This is my page">    
	<script type="text/javascript">    
		function add(){      
			Dialog.open({Title:"新增", Width:600, Height:360, URL:"<%=path%>/memberManage/Client!toAddClient.action"});   
		}      
		function mod(){      
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"编辑", Width:600, Height:360, URL:"<%=path%>/memberManage/Client!toAddClient.action?oper=1&clientBean.id="+id});
		}      
		function detail(){   
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"详情", Width:500, Height:360, URL:"<%=path%>/memberManage/Client!toAddClient.action?read=1&oper=1&clientBean.id="+id});    
		}      
		function del(){      
			if(!isCheckAny("idArr")){      
				Dialog.alert("请至少勾选一个！");   
				return;   
			}    
			Dialog.confirm("确定删除吗", function(){     
				var action_tmp = form1.action; 
				form1.action = "<%=path%>/memberManage/Client!delClients.action";
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
			Dialog.open({Title:"查询", Width:650, Height:220, URL:"<%=path%>/memberManage/Client!search.action",OnLoad:function(){
			     this.innerDoc.getElementById("username").value = $("#username").val();
			     this.innerDoc.getElementById("password").value = $("#password").val();
			     this.innerDoc.getElementById("email").value = $("#email").val();
			     this.innerDoc.getElementById("phone_number").value = $("#phone_number").val();
			     this.innerDoc.getElementById("birthday").value = $("#birthday").val();
			     this.innerDoc.getElementById("first_name").value = $("#first_name").val();
			     this.innerDoc.getElementById("last_name").value = $("#last_name").val();
			     this.innerDoc.getElementById("render").value = $("#render").val();
			     this.innerDoc.getElementById("age").value = $("#age").val();
			     this.innerDoc.getElementById("description").value = $("#description").val();
			     this.innerDoc.getElementById("small").value = $("#small").val();
			     this.innerDoc.getElementById("big").value = $("#big").val();
			     this.innerDoc.getElementById("update_date").value = $("#update_date").val();
			     this.innerDoc.getElementById("ctime").value = $("#ctime").val();
           }
			});    
		}      
		
	</script>
  </head> 
  <body>  
  <GF:BodyCaption label="客户" ico="images/ico/user.gif"> 
  	<GF:ToolBar id="123">     
	  	<GF:ToolBarItem id="search" label="查询" ico="images/ico/search.gif" onclick="search();" />    
	  	<GF:ToolBarItem id="add" label="添加" ico="images/ico/add.gif" 		onclick="add();" />   
	  	<GF:ToolBarItem id="edit" label="编辑" ico="images/ico/edit.gif" 	onclick="mod();" />   
	  	<GF:ToolBarItem id="delete" label="删除" ico="images/ico/delete.gif" onclick="del();"/> 
	  	<GF:ToolBarItem id="detail" label="详情" ico="images/ico/detail.gif" onclick="detail();"/>     
	  	<GF:ToolBarItem id="export" label="导出" ico="images/ico/export.gif" onclick="exportExcel();"/>
	  	<GF:ToolBarItem id="refresh" label="刷新" ico="images/ico/refresh.gif" onclick="refresh();"/>  
	</GF:ToolBar>   
    <form action="<%=path%>/memberManage/Client!list.action" name="form1" id="form1" method="get">  
   
			<input type="hidden" name="clientBean.username" id="username" value="${clientBean.username}"/> 
			<input type="hidden" name="clientBean.password" id="password" value="${clientBean.password}"/> 
			<input type="hidden" name="clientBean.email" id="email" value="${clientBean.email}"/> 
			<input type="hidden" name="clientBean.phoneNumber" id="phoneNumber" value="${clientBean.phoneNumber}"/> 
			<input type="hidden" name="clientBean.birthday" id="birthday" value="${clientBean.birthday}"/> 
			<input type="hidden" name="clientBean.firstName" id="firstName" value="${clientBean.firstName}"/> 
			<input type="hidden" name="clientBean.lastName" id="lastName" value="${clientBean.lastName}"/> 
			<input type="hidden" name="clientBean.render" id="render" value="${clientBean.render}"/> 
			<input type="hidden" name="clientBean.age" id="age" value="${clientBean.age}"/> 
			<input type="hidden" name="clientBean.description" id="description" value="${clientBean.description}"/> 
			<input type="hidden" name="clientBean.small" id="small" value="${clientBean.small}"/> 
			<input type="hidden" name="clientBean.big" id="big" value="${clientBean.big}"/> 
			<input type="hidden" name="clientBean.updateDate" id="updateDate" value="${clientBean.updateDate}"/> 
		   	<input type="hidden" name="clientBean.ctime" id="ctime" value="${clientBean.ctime}"/> 
		   	<input type="hidden" name="sortname" value="${page.sortname}"/>    
		   	<input type="hidden" name="sortorder"  value="${page.sortorder }"/>
		   	<input type="hidden" name="pageSize" value="${page.pageSize}"/>    
		   	<input type="hidden" name="pageNo"  value="${page.pageNo }"/>      
			<table id="table1" width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0"> 
			   	 <thead>
			   		<tr>  
			   			<th width="2%"><span><input type="checkbox" onClick="checkAll(this,'idArr')"  width="20"/></span></th>     
			   			<th sortname="username" width="5%">登陆名</th>	   
			   			<th sortname="password" width="10%">密码</th>	   
			   			<th sortname="email" width="10%">邮箱</th>	   
			   			<th sortname="phone_number" width="5%">手机号</th>	   
			   			<th sortname="birthday" width="5%">生日</th>	   
			   			<th sortname="first_name" width="5%">名</th>	   
			   			<th sortname="last_name" width="5%">姓</th>	   
			   			<th sortname="render" width="5%">性别</th>	   
			   			<th sortname="age" width="5%">年龄</th>	   
			   			<th sortname="description" width="20%">描述</th>	   
			   			<th sortname="small" width="10%">小头像</th>	   
			   			<th sortname="big" width="10%">大头像</th>	   
			   			<th sortname="update_date" width="25%">更新时间</th>
			   			<th sortname="ctime" width="25%">注册时间</th>	   
			   		</tr> 
			   	</thead>
			   	<tbody> 
			   		<s:iterator value="#request.list" id="map"> 
		   			 <tr> 
		   			 	 <td align="left"><input type="checkbox" name="idArr"  value="${map.id}"/></td> 
		   			 	 <td>${map.username}</td>    
		   			 	 <td>${map.password}</td>    
		   			 	 <td>${map.email}</td>    
		   			 	 <td>${map.phone_number}</td>    
		   			 	 <td>${map.birthday}</td>    
		   			 	 <td>${map.first_name}</td>    
		   			 	 <td>${map.last_name}</td>    
		   			 	 <td>${map.render}</td>    
		   			 	 <td>${map.age}</td>    
		   			 	 <td>${map.description}</td>    
		   			 	 <td>${map.small}</td>    
		   			 	 <td>${map.big}</td>    
		   			 	 <td>${map.update_date}</td>
		   			 	 <td>${map.ctime}</td>        
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
