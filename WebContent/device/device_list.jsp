<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp" %>  
<% 
String path = request.getContextPath();      
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>    
  <head>  
   
    <title>设备表</title> 
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache">  
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">   
	<meta http-equiv="description" content="This is my page">    
	<script type="text/javascript">    
		function add(){      
			Dialog.open({Title:"新增", Width:600, Height:360, URL:"<%=path%>/device/Device!toAddDevice.action"});   
		}      
		function mod(){      
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"编辑", Width:600, Height:360, URL:"<%=path%>/device/Device!toAddDevice.action?oper=1&deviceBean.id="+id});
		}      
		function detail(){   
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"详情", Width:500, Height:360, URL:"<%=path%>/device/Device!toAddDevice.action?read=1&oper=1&deviceBean.id="+id});    
		}      
		function del(){      
			if(!isCheckAny("idArr")){      
				Dialog.alert("请至少勾选一个！");   
				return;   
			}    
			Dialog.confirm("确定删除吗", function(){     
				var action_tmp = form1.action; 
				form1.action = "<%=path%>/device/Device!delDevices.action";
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
			Dialog.open({Title:"查询", Width:650, Height:220, URL:"<%=path%>/device/Device!search.action",OnLoad:function(){
			     this.innerDoc.getElementById("deviceid").value = $("#deviceid").val();
			     this.innerDoc.getElementById("token").value = $("#token").val();
			     this.innerDoc.getElementById("ctrlkey").value = $("#ctrlkey").val();
			     this.innerDoc.getElementById("bindkey").value = $("#bindkey").val();
			     this.innerDoc.getElementById("mac").value = $("#mac").val();
			     this.innerDoc.getElementById("binver").value = $("#binver").val();
			     this.innerDoc.getElementById("bintype").value = $("#bintype").val();
			     this.innerDoc.getElementById("ip").value = $("#ip").val();
			     this.innerDoc.getElementById("online").value = $("#online").val();
			     this.innerDoc.getElementById("pid").value = $("#pid").val();
			     this.innerDoc.getElementById("name").value = $("#name").val();
           }
			});    
		}      
		
	</script>
  </head> 
  <body>  
  <GF:BodyCaption label="设备表" ico="images/ico/user.gif"> 
  	<GF:ToolBar id="123">     
	  	<GF:ToolBarItem id="search" label="查询" ico="images/ico/search.gif" onclick="search();" />    
	  	<GF:ToolBarItem id="add" label="添加" ico="images/ico/add.gif" 		onclick="add();" />   
	  	<GF:ToolBarItem id="edit" label="编辑" ico="images/ico/edit.gif" 	onclick="mod();" />   
	  	<GF:ToolBarItem id="delete" label="删除" ico="images/ico/delete.gif" onclick="del();"/> 
	  	<GF:ToolBarItem id="detail" label="详情" ico="images/ico/detail.gif" onclick="detail();"/>     
	  	<GF:ToolBarItem id="export" label="导出" ico="images/ico/export.gif" onclick="exportExcel();"/>
	  	<GF:ToolBarItem id="refresh" label="刷新" ico="images/ico/refresh.gif" onclick="refresh();"/>  
	</GF:ToolBar>   
    <form action="<%=path%>/device/Device!list.action" name="form1" id="form1" method="get">  
   
			<input type="hidden" name="deviceBean.deviceid" id="deviceid" value="${deviceBean.deviceid}"/> 
			<input type="hidden" name="deviceBean.token" id="token" value="${deviceBean.token}"/> 
			<input type="hidden" name="deviceBean.ctrlkey" id="ctrlkey" value="${deviceBean.ctrlkey}"/> 
			<input type="hidden" name="deviceBean.bindkey" id="bindkey" value="${deviceBean.bindkey}"/> 
			<input type="hidden" name="deviceBean.mac" id="mac" value="${deviceBean.mac}"/> 
			<input type="hidden" name="deviceBean.binver" id="binver" value="${deviceBean.binver}"/> 
			<input type="hidden" name="deviceBean.bintype" id="bintype" value="${deviceBean.bintype}"/> 
			<input type="hidden" name="deviceBean.ip" id="ip" value="${deviceBean.ip}"/> 
			<input type="hidden" name="deviceBean.online" id="online" value="${deviceBean.online}"/> 
			<input type="hidden" name="deviceBean.pid" id="pid" value="${deviceBean.pid}"/> 
			<input type="hidden" name="deviceBean.name" id="name" value="${deviceBean.name}"/> 
		   	<input type="hidden" name="sortname" value="${page.sortname}"/>    
		   	<input type="hidden" name="sortorder"  value="${page.sortorder }"/>
		   	<input type="hidden" name="pageSize" value="${page.pageSize}"/>    
		   	<input type="hidden" name="pageNo"  value="${page.pageNo }"/>      
			<table id="table1" width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0"> 
			   	 <thead>
			   		<tr>  
			   			<th><span><input type="checkbox" onClick="checkAll(this,'idArr')"  width="20"/></span></th>     
			   			<th sortname="deviceid" width="10%">设备id</th>	   
			   			<th sortname="token" width="10%">设备token</th>	   
			   			<th sortname="ctrlkey" width="10%">控制码</th>	   
			   			<th sortname="bindkey" width="10%">绑定码</th>	   
			   			<th sortname="mac" width="10%">设备mac地址</th>	   
			   			<th sortname="binver" width="10%">设备固件版本号</th>	   
			   			<th sortname="bintype" width="10%">设备固件类型A,B</th>	   
			   			<th sortname="ip" width="10%">设备ip</th>	   
			   			<th sortname="online" width="10%">是否在线</th>	   
			   			<th sortname="pid" width="10%">设备种类id</th>	   
			   			<th sortname="name" width="10%">设备名称</th>	   
			   		</tr> 
			   	</thead>
			   	<tbody> 
			   		<s:iterator value="#request.list" id="map"> 
		   			 <tr> 
		   			 	 <td align="left"><input type="checkbox" name="idArr"  value="${map.id}"/></td> 
		   			 	 <td>${map.deviceid}</td>    
		   			 	 <td>${map.token}</td>    
		   			 	 <td>${map.ctrlkey}</td>    
		   			 	 <td>${map.bindkey}</td>    
		   			 	 <td>${map.mac}</td>    
		   			 	 <td>${map.binver}</td>    
		   			 	 <td>${map.bintype}</td>    
		   			 	 <td>${map.ip}</td>    
		   			 	 <td>${map.online}</td>    
		   			 	 <td>${map.pid}</td>    
		   			 	 <td>${map.name}</td>    
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
