<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ include file="/common/taglibs.jsp" %>  
<%                                       
String path = request.getContextPath();  
%>                                       
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>                                   
<title></title>                          
<script type="text/javascript">        
 var isCommit=false 
	function save(){                        
		if($("#form1").valid()) {
			
		var usern = /^[a-zA-Z_]{1,}$/g; 


	        if (!usern.test($("#name").val())) { 

	              var  msg = "用户名只能由字母下划线组成"; 

	                Dialog.error(msg); 

	                $("#name").val(''); 

	                $("#name").focus(); 


	        }else {
	        	if($("#type").val()==0){
	        		Dialog.error("必须选择类型");
	        	}else if($("#type").val()==1){
	        		if($("#min").val() >= $("#max").val()){
	        			Dialog.error("最小值必须小于最大值");
	        		}else{
		                if(isCommit==false){   
		                    isCommit=true       
		           			form1.submit();                     
		                 } 
	        		}
	        	}else if($("#type").val()==2){
	        		$("#min").val("");
	        		$("#max").val("");
	                if(isCommit==false){   
	                    isCommit=true       
	           			form1.submit();                     
	                 }  
	        	}
	        	
	        }
			
               
		}                                     
	}                                       
	                                        
	function result(messageType, message){  
		if(messageType=="error"){           
			Dialog.error(message);
			isCommit = false;
		} else if (messageType == "reload_success") {   
			Dialog.alert(message,function(){    
				Dialog.opener().location.reload(); //;= "<%=path%>/device/ClientParam!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
	}                                       
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "clientParamBean.name":{            
			        required:true,CNRangeLength:[0,255]
			    },                              
			    "clientParamBean.type":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "clientParamBean.min":{            
			    	digits:true,range:[0,9999999999]
			    },                              
			    "clientParamBean.max":{            
			    	digits:true,range:[0,9999999999]
			    }                              
			},
			messages:{                          
//			    "clientParamBean.code":{          
//				    required : "",CNRangeLength:""  
//			    },                            
			}                                   
		});  
		
		
	$('#type').change(function(){   
		var p1=$(this).children('option:selected').val();//这就是selected的值  
          if(p1==1){
        	  $(".ds").css("display","");
          }else{
        	  $(".ds").css("display","none");
          }

		})  
	});                                     
	                                        
	                                        
</script>                                
</head>                                  
<body>                                   
<form name="form1" id="form1" action="<%=path %>/device/ClientParam!addClientParam.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="clientParamBean.id" id="id" value="${clientParamBean.id }"/>
<input type="hidden" name="clientParamBean.pid" id="pid" value="${clientParamBean.pid}"/>  
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 
  <tr>                                    
    	<td align="right" width="30%" > 
                            名称<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientParamBean.name" id="name"  value="${clientParamBean.name}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            类型<span class="mark"></span>   
        </td>                            
        <td>                             
            <s:select list="#{1:'Int',2:'String'}" theme="simple" listKey="key" listValue="value"
        	 value="#clientParamBean.type" name="clientParamBean.type" headerKey="0" headerValue="--请选择参数类型--" cssClass="GF-field" id="type"></s:select>      
        </td>                            
  </tr>                                
  <tr class="ds" style="display:none;">                                    
    	<td align="right" > 
                            最小值<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientParamBean.min" id="min"  value="${clientParamBean.min}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr class="ds" style="display:none;">                                    
    	<td align="right" > 
                            最大值<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientParamBean.max" id="max"  value="${clientParamBean.max}" class="GF-field"/>   
        </td>                            
  </tr>                                                                                                
  <tr>                                
      <td>&nbsp;</td>		
      <td><input type="button" name="提交" value="提交" onclick="save();" class="GF-btn"/></td>		
  </tr>                                
</table>                                 
<iframe name="fram" id="fram" style="display:none"></iframe>   
</form>                                  
</body>                                  
</html>                                  
