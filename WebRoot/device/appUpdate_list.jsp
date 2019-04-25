<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp" %>  
<% 
String path = request.getContextPath();      
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>    
  <head>  
   
    <title></title> 
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache">  
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">   
	<meta http-equiv="description" content="This is my page">    
	<script type="text/javascript">    
		function add(){      
			Dialog.open({Title:"新增", Width:600, Height:360, URL:"<%=path%>/device/AppUpdate!toAddAppUpdate.action"});   
		}      
		function mod(){      
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"编辑", Width:600, Height:360, URL:"<%=path%>/device/AppUpdate!toAddAppUpdate.action?oper=1&appUpdateBean.id="+id});
		}      
		function detail(){   
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"详情", Width:500, Height:360, URL:"<%=path%>/device/AppUpdate!toAddAppUpdate.action?read=1&oper=1&appUpdateBean.id="+id});    
		}      
		function del(){      
			if(!isCheckAny("idArr")){      
				Dialog.alert("请至少勾选一个！");   
				return;   
			}    
			Dialog.confirm("确定删除吗", function(){     
				var action_tmp = form1.action; 
				form1.action = "<%=path%>/device/AppUpdate!delAppUpdates.action";
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
			Dialog.open({Title:"查询", Width:650, Height:220, URL:"<%=path%>/device/AppUpdate!search.action",OnLoad:function(){
			     this.innerDoc.getElementById("version").value = $("#version").val();
			     this.innerDoc.getElementById("code").value = $("#code").val();
			     this.innerDoc.getElementById("url").value = $("#url").val();
			     this.innerDoc.getElementById("en").value = $("#en").val();
			     this.innerDoc.getElementById("zh").value = $("#zh").val();
			     this.innerDoc.getElementById("fr").value = $("#fr").val();
			     this.innerDoc.getElementById("de").value = $("#de").val();
			     this.innerDoc.getElementById("es").value = $("#es").val();
			     this.innerDoc.getElementById("nl").value = $("#nl").val();
			     this.innerDoc.getElementById("fi").value = $("#fi").val();
			     this.innerDoc.getElementById("sl").value = $("#sl").val();
			     this.innerDoc.getElementById("it").value = $("#it").val();
			     this.innerDoc.getElementById("cs").value = $("#cs").val();
			     this.innerDoc.getElementById("url_ex").value = $("#url_ex").val();
			     this.innerDoc.getElementById("ios").value = $("#ios").val();
           }
			});    
		}      
		
	</script>
  </head> 
  <body>  
  <GF:BodyCaption label="" ico="images/ico/user.gif"> 
  	<GF:ToolBar id="123">     
	  	<GF:ToolBarItem id="search" label="查询" ico="images/ico/search.gif" onclick="search();" />    
	  	<GF:ToolBarItem id="add" label="添加" ico="images/ico/add.gif" 		onclick="add();" />   
	  	<GF:ToolBarItem id="edit" label="编辑" ico="images/ico/edit.gif" 	onclick="mod();" />   
	  	<GF:ToolBarItem id="delete" label="删除" ico="images/ico/delete.gif" onclick="del();"/> 
	  	<GF:ToolBarItem id="detail" label="详情" ico="images/ico/detail.gif" onclick="detail();"/>     
	  	<GF:ToolBarItem id="export" label="导出" ico="images/ico/export.gif" onclick="exportExcel();"/>
	  	<GF:ToolBarItem id="refresh" label="刷新" ico="images/ico/refresh.gif" onclick="refresh();"/>  
	</GF:ToolBar>   
    <form action="<%=path%>/device/AppUpdate!list.action" name="form1" id="form1" method="get">  
   
			<input type="hidden" name="appUpdateBean.version" id="version" value="${appUpdateBean.version}"/> 
			<input type="hidden" name="appUpdateBean.code" id="code" value="${appUpdateBean.code}"/> 
			<input type="hidden" name="appUpdateBean.url" id="url" value="${appUpdateBean.url}"/> 
			<input type="hidden" name="appUpdateBean.en" id="en" value="${appUpdateBean.en}"/> 
			<input type="hidden" name="appUpdateBean.zh" id="zh" value="${appUpdateBean.zh}"/> 
			<input type="hidden" name="appUpdateBean.fr" id="fr" value="${appUpdateBean.fr}"/> 
			<input type="hidden" name="appUpdateBean.de" id="de" value="${appUpdateBean.de}"/> 
			<input type="hidden" name="appUpdateBean.es" id="es" value="${appUpdateBean.es}"/> 
			<input type="hidden" name="appUpdateBean.nl" id="nl" value="${appUpdateBean.nl}"/> 
			<input type="hidden" name="appUpdateBean.fi" id="fi" value="${appUpdateBean.fi}"/> 
			<input type="hidden" name="appUpdateBean.sl" id="sl" value="${appUpdateBean.sl}"/> 
			<input type="hidden" name="appUpdateBean.it" id="it" value="${appUpdateBean.it}"/> 
			<input type="hidden" name="appUpdateBean.cs" id="cs" value="${appUpdateBean.cs}"/> 
			<input type="hidden" name="appUpdateBean.urlEx" id="urlEx" value="${appUpdateBean.urlEx}"/> 
			<input type="hidden" name="appUpdateBean.ios" id="ios" value="${appUpdateBean.ios}"/> 
						<input type="hidden" name="appUpdateBean.project" id="ios" value="${appUpdateBean.project}"/> 
		   	<input type="hidden" name="sortname" value="${page.sortname}"/>    
		   	<input type="hidden" name="sortorder"  value="${page.sortorder }"/>
		   	<input type="hidden" name="pageSize" value="${page.pageSize}"/>    
		   	<input type="hidden" name="pageNo"  value="${page.pageNo }"/>      
			<table id="table1" width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0"> 
			   	 <thead>
			   		<tr>  
			   			<th width="2%"><span><input type="checkbox" onClick="checkAll(this,'idArr')"  width="20"/></span></th>     
			   			<th sortname="version" width="10%">app版本号</th>	   
			   			<th sortname="code" width="10%">app版本号代码</th>	   
			   			<th sortname="url" width="10%">app下载的路径</th>	   
			   			<th sortname="en" width="10%">英文说明</th>	   
			   			<th sortname="zh" width="10%">中文说明</th>	   
			   			<th sortname="fr" width="10%">法文说明</th>	   
			   			<th sortname="de" width="10%">德文说明</th>	   
			   			<th sortname="es" width="10%">西班牙文说明</th>	   
			   			<th sortname="nl" width="10%">荷兰文说明</th>	   
			   			<th sortname="fi" width="10%">芬兰文说明</th>	   
			   			<th sortname="sl" width="10%">斯洛文尼亚语说明</th>	   
			   			<th sortname="it" width="10%">意大利语说明</th>	   
			   			<th sortname="cs" width="10%">捷克语说明</th>	   
			   			<th sortname="url_ex" width="10%">app下载的路径国外</th>	   
			   			<th sortname="ios" width="10%">是否是ios</th>	  
			   			<th sortname="ios" width="10%">项目标示</th>	  
			   		</tr> 
			   	</thead>
			   	<tbody> 
			   		<s:iterator value="#request.list" id="map"> 
		   			 <tr> 
		   			 	 <td align="left"><input type="checkbox" name="idArr"  value="${map.id}"/></td> 
		   			 	 <td>${map.version}</td>    
		   			 	 <td>${map.code}</td>    
		   			 	 <td>${map.url}</td>    
		   			 	 <td>${map.en}</td>    
		   			 	 <td>${map.zh}</td>    
		   			 	 <td>${map.fr}</td>    
		   			 	 <td>${map.de}</td>    
		   			 	 <td>${map.es}</td>    
		   			 	 <td>${map.nl}</td>    
		   			 	 <td>${map.fi}</td>    
		   			 	 <td>${map.sl}</td>    
		   			 	 <td>${map.it}</td>    
		   			 	 <td>${map.cs}</td>    
		   			 	 <td>${map.url_ex}</td>    
		   			 	 <td>${map.ios}</td> 
		   			 	 <td>${map.project}</td>    
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
