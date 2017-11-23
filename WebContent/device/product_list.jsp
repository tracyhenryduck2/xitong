<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp" %>  
<% 
String path = request.getContextPath();      
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>    
  <head>  
   
    <title>产品表</title> 
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache">  
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">   
	<meta http-equiv="description" content="This is my page">    
	<script type="text/javascript">    
		function add(){      
			Dialog.open({Title:"新增", Width:600, Height:360, URL:"<%=path%>/device/Product!toAddProduct.action"});   
		}      
		function mod(){      
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"编辑", Width:600, Height:360, URL:"<%=path%>/device/Product!toAddProduct.action?oper=1&productBean.id="+id});
		}      
		function detail(){   
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"详情", Width:500, Height:360, URL:"<%=path%>/device/Product!toAddProduct.action?read=1&oper=1&productBean.id="+id});    
		}      
		function del(){      
			if(!isCheckAny("idArr")){      
				Dialog.alert("请至少勾选一个！");   
				return;   
			}    
			Dialog.confirm("确定删除吗", function(){     
				var action_tmp = form1.action; 
				form1.action = "<%=path%>/device/Product!delProducts.action";
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
			Dialog.open({Title:"查询", Width:650, Height:220, URL:"<%=path%>/device/Product!search.action",OnLoad:function(){
			     this.innerDoc.getElementById("name").value = $("#name").val();
			     this.innerDoc.getElementById("prod_key").value = $("#prod_key").val();
			     this.innerDoc.getElementById("model").value = $("#model").val();
			     this.innerDoc.getElementById("desp").value = $("#desp").val();
			     this.innerDoc.getElementById("img").value = $("#img").val();
			     this.innerDoc.getElementById("force_bind").value = $("#force_bind").val();
			     this.innerDoc.getElementById("ctime").value = $("#ctime").val();
           }
			});    
		}      
		
	</script>
  </head> 
  <body>  
  <GF:BodyCaption label="产品表" ico="images/ico/user.gif"> 
  	<GF:ToolBar id="123">     
	  	<GF:ToolBarItem id="search" label="查询" ico="images/ico/search.gif" onclick="search();" />    
	  	<GF:ToolBarItem id="add" label="添加" ico="images/ico/add.gif" 		onclick="add();" />   
	  	<GF:ToolBarItem id="edit" label="编辑" ico="images/ico/edit.gif" 	onclick="mod();" />   
	  	<GF:ToolBarItem id="delete" label="删除" ico="images/ico/delete.gif" onclick="del();"/> 
	  	<GF:ToolBarItem id="detail" label="详情" ico="images/ico/detail.gif" onclick="detail();"/>     
	  	<GF:ToolBarItem id="export" label="导出" ico="images/ico/export.gif" onclick="exportExcel();"/>
	  	<GF:ToolBarItem id="refresh" label="刷新" ico="images/ico/refresh.gif" onclick="refresh();"/>  
	</GF:ToolBar>   
    <form action="<%=path%>/device/Product!list.action" name="form1" id="form1" method="get">  
   
			<input type="hidden" name="productBean.name" id="name" value="${productBean.name}"/> 
			<input type="hidden" name="productBean.prodKey" id="prodKey" value="${productBean.prodKey}"/> 
			<input type="hidden" name="productBean.model" id="model" value="${productBean.model}"/> 
			<input type="hidden" name="productBean.desp" id="desp" value="${productBean.desp}"/> 
			<input type="hidden" name="productBean.img" id="img" value="${productBean.img}"/> 
			<input type="hidden" name="productBean.forceBind" id="forceBind" value="${productBean.forceBind}"/> 
			<input type="hidden" name="productBean.ctime" id="ctime" value="${productBean.ctime}"/> 
		   	<input type="hidden" name="sortname" value="${page.sortname}"/>    
		   	<input type="hidden" name="sortorder"  value="${page.sortorder }"/>
		   	<input type="hidden" name="pageSize" value="${page.pageSize}"/>    
		   	<input type="hidden" name="pageNo"  value="${page.pageNo }"/>      
			<table id="table1" width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0"> 
			   	 <thead>
			   		<tr>  
			   			<th width="2%"><span><input type="checkbox" onClick="checkAll(this,'idArr')"  width="20"/></span></th>     
			   			<th sortname="name" width="10%">设备型号名称</th>	   
			   			<th sortname="prod_key" width="10%">设备种类标识</th>	   
			   			<th sortname="model" width="10%">设备型号</th>	   
			   			<th sortname="desp" width="10%">介绍</th>	   
			   			<th sortname="img" width="10%">产品图片</th>	   
			   			<th sortname="force_bind" width="10%">是否强制绑定</th>	   
			   			<th sortname="ctime" width="10%">生成时间</th>	   
			   		</tr> 
			   	</thead>
			   	<tbody> 
			   		<s:iterator value="#request.list" id="map"> 
		   			 <tr> 
		   			 	 <td align="left"><input type="checkbox" name="idArr"  value="${map.id}"/></td> 
		   			 	 <td>${map.name}</td>    
		   			 	 <td>${map.prod_key}</td>    
		   			 	 <td>${map.model}</td>    
		   			 	 <td>${map.desp}</td>    
		   			 	 <td>${map.img}</td>    
		   			 	 <td>${map.force_bind}</td>    
		   			 	 <td><GF:ConvertTime format="yyyy-MM-dd HH:mm:ss" value="${map.ctime}"/></td>    
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
