<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp" %>  
<% 
String path = request.getContextPath();      
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>    
  <head>  
   
    <title>问题记录</title> 
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache">  
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">   
	<meta http-equiv="description" content="This is my page">    
	<script type="text/javascript">    
		function add(){      
			Dialog.open({Title:"新增", Width:1000, Height:600, URL:"<%=path%>/memberManage/QuestionLog!toAddQuestionLog.action"});   
		}      
		function mod(){      
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"编辑", Width:1000, Height:600, URL:"<%=path%>/memberManage/QuestionLog!toAddQuestionLog.action?oper=1&questionLogBean.id="+id});
		}      
		function detail(){   
			var id = getCheckRowValue();     
			if(id==null) {     
				return;   
			}    
			Dialog.open({Title:"详情", Width:1000, Height:600, URL:"<%=path%>/memberManage/QuestionLog!toAddQuestionLog.action?read=1&oper=1&questionLogBean.id="+id});    
		}      
		function del(){      
			if(!isCheckAny("idArr")){      
				Dialog.alert("请至少勾选一个！");   
				return;   
			}    
			Dialog.confirm("确定删除吗", function(){     
				var action_tmp = form1.action; 
				form1.action = "<%=path%>/memberManage/QuestionLog!delQuestionLogs.action";
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
			Dialog.open({Title:"查询", Width:650, Height:220, URL:"<%=path%>/memberManage/QuestionLog!search.action",OnLoad:function(){
			     this.innerDoc.getElementById("type").value = $("#type").val();
			     this.innerDoc.getElementById("title").value = $("#title").val();
			     this.innerDoc.getElementById("dsc").value = $("#dsc").val();
			     this.innerDoc.getElementById("ver").value = $("#ver").val();
			     this.innerDoc.getElementById("createtime").value = $("#createtime").val();
			     this.innerDoc.getElementById("answer").value = $("#answer").val();
			     this.innerDoc.getElementById("answertime").value = $("#answertime").val();
			     this.innerDoc.getElementById("createid").value = $("#createid").val();
           }
			});    
		}      
		
		function exportExcelAll() {  
			window.open("<%=path%>/memberManage/QuestionLog!exportAll.action"); 
		}     
		
	</script>
  </head> 
  <body>  
  <GF:BodyCaption label="问题记录" ico="images/ico/user.gif"> 
  	<GF:ToolBar id="123">     
	  	<GF:ToolBarItem id="search" label="查询" ico="images/ico/search.gif" onclick="search();" />    
	  	<GF:ToolBarItem id="add" label="添加" ico="images/ico/add.gif" 		onclick="add();" />   
	  	<GF:ToolBarItem id="edit" label="编辑" ico="images/ico/edit.gif" 	onclick="mod();" />   
	  	<GF:ToolBarItem id="delete" label="删除" ico="images/ico/delete.gif" onclick="del();"/> 
	  	<GF:ToolBarItem id="detail" label="详情" ico="images/ico/detail.gif" onclick="detail();"/>     
	  	<GF:ToolBarItem id="export" label="导出全部" ico="images/ico/export.gif" onclick="exportExcelAll();"/>
	  	<GF:ToolBarItem id="refresh" label="刷新" ico="images/ico/refresh.gif" onclick="refresh();"/>  
	</GF:ToolBar>   
    <form action="<%=path%>/memberManage/QuestionLog!list.action" name="form1" id="form1" method="get">  
   
			<input type="hidden" name="questionLogBean.type" id="type" value="${questionLogBean.type}"/> 
			<input type="hidden" name="questionLogBean.title" id="title" value="${questionLogBean.title}"/> 
			<input type="hidden" name="questionLogBean.dsc" id="dsc" value="${questionLogBean.dsc}"/> 
			<input type="hidden" name="questionLogBean.ver" id="ver" value="${questionLogBean.ver}"/> 
			<input type="hidden" name="questionLogBean.createtime" id="createtime" value="${questionLogBean.createtime}"/> 
			<input type="hidden" name="questionLogBean.answer" id="answer" value="${questionLogBean.answer}"/> 
			<input type="hidden" name="questionLogBean.answertime" id="answertime" value="${questionLogBean.answertime}"/> 
			<input type="hidden" name="questionLogBean.createid" id="createid" value="${questionLogBean.createid}"/> 
		   	<input type="hidden" name="sortname" value="${page.sortname}"/>    
		   	<input type="hidden" name="sortorder"  value="${page.sortorder }"/>
		   	<input type="hidden" name="pageSize" value="${page.pageSize}"/>    
		   	<input type="hidden" name="pageNo"  value="${page.pageNo }"/>      
			<table id="table1" width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0"> 
			   	 <thead>
			   		<tr>  
			   			<th><span><input type="checkbox" onClick="checkAll(this,'idArr')"  width="20"/></span></th>     
			   			<th sortname="type" width="10%">是安卓还是苹果问题,0代表安卓，1代表IOS，2代表其他</th>	   
			   			<th sortname="title" width="10%">主题</th>	   
			   			<th sortname="dsc" width="10%">问题描述</th>	   
			   			<th sortname="ver" width="10%">APP版本号信息和网关固件版本信息</th>	   
			   			<th sortname="createtime" width="10%">创建时间</th>	   
			   			<th sortname="answer" width="10%">回答</th>	   
			   			<th sortname="answertime" width="10%">回答时间</th>	   
			   			<th sortname="createid" width="10%">创建者</th>	   
			   		</tr> 
			   	</thead>
			   	<tbody> 
			   		<s:iterator value="#request.list" id="map"> 
		   			 <tr> 
		   			 	 <td align="left"><input type="checkbox" name="idArr"  value="${map.id}"/></td> 
		   			 	 <td><s:if test="#map.type == 0">安卓</s:if><s:elseif test="#map.type== 1">IOS</s:elseif><s:else>其他</s:else></td>    
		   			 	 <td>${map.title}</td>    
		   			 	 <td>${map.dsc}</td>    
		   			 	 <td>${map.ver}</td>    
		   			 	 <td><GF:ConvertTime value="${map.createtime}" format="yyyy/MM/dd HH:mm"/></td>    
		   			 	 <td>${map.answer}</td>    
		   			 	 <td><GF:ConvertTime value="${map.answertime}" format="yyyy/MM/dd HH:mm"/></td>    
		   			 	 <td>${map.username}</td>    
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
