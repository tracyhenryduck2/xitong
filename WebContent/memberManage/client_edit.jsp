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
        if(isCommit==false){   
         isCommit=true       
			form1.submit();                     
      }                 
		}                                     
	}                                       
	                                        
	function result(messageType, message){  
		if(messageType=="error"){           
			Dialog.error(message);				      
		} else if (messageType == "reload_success") {   
			Dialog.alert(message,function(){    
				Dialog.opener().location.reload(); //;= "<%=path%>/memberManage/Client!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
	}                                       
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "clientBean.username":{            
			        required : true,CNRangeLength:[0,45]
			    },                              
			    "clientBean.password":{            
			        required : true,CNRangeLength:[0,45]
			    },                              
			    "clientBean.img":{            
			        CNRangeLength:[0,255]
			    },                              
			    "clientBean.email":{            
			        CNRangeLength:[0,45]
			    },                              
			    "clientBean.phone":{            
			        CNRangeLength:[0,20]
			    }                              
			},                                  
			messages:{                          
//			    "clientBean.code":{          
//				    required : "",CNRangeLength:""  
//			    },                            
			}                                   
		});                                   
	});                                     
	                                        
	                                        
</script>                                
</head>                                  
<body>                                   
<form name="form1" id="form1" action="<%=path %>/memberManage/Client!addClient.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="clientBean.id" id="id" value="${clientBean.id }"/>  
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 
  <tr>                                    
    	<td align="right" width="30%" > 
                            登陆名<span class="mark">*</span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.username" id="username"  value="${clientBean.username}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            密码<span class="mark">*</span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.password" id="password"  value="${clientBean.password}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            头像<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.img" id="img"  value="${clientBean.img}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            邮箱<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.email" id="email"  value="${clientBean.email}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            手机号<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.phone" id="phone"  value="${clientBean.phone}" class="GF-field"/>   
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
