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
	function search(){                          
			Dialog.opener().document.getElementById("username").value = $("#username").val()||"";    
			Dialog.opener().document.getElementById("password").value = $("#password").val()||"";    
			Dialog.opener().document.getElementById("email").value = $("#email").val()||"";    
			Dialog.opener().document.getElementById("phoneNumber").value = $("#phoneNumber").val()||"";    
			Dialog.opener().document.getElementById("birthday").value = $("#birthday").val()||"";    
			Dialog.opener().document.getElementById("firstName").value = $("#firstName").val()||"";    
			Dialog.opener().document.getElementById("lastName").value = $("#lastName").val()||"";    
			Dialog.opener().document.getElementById("render").value = $("#render").val()||"";    
			Dialog.opener().document.getElementById("age").value = $("#age").val()||"";    
			Dialog.opener().document.getElementById("description").value = $("#description").val()||"";    
			Dialog.opener().document.getElementById("small").value = $("#small").val()||"";    
			Dialog.opener().document.getElementById("big").value = $("#big").val()||"";    
			Dialog.opener().document.getElementById("updateDate").value = $("#updateDate").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "clientBean.username":{            
			        CNRangeLength:[0,45]
			    },                              
			    "clientBean.password":{            
			        CNRangeLength:[0,45]
			    },                              
			    "clientBean.email":{            
			        CNRangeLength:[0,45]
			    },                              
			    "clientBean.phoneNumber":{            
			        CNRangeLength:[0,20]
			    },                              
			    "clientBean.birthday":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "clientBean.firstName":{            
			        CNRangeLength:[0,45]
			    },                              
			    "clientBean.lastName":{            
			        CNRangeLength:[0,45]
			    },                              
			    "clientBean.render":{            
			        CNRangeLength:[0,5]
			    },                              
			    "clientBean.age":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "clientBean.description":{            
			        CNRangeLength:[0,255]
			    },                              
			    "clientBean.small":{            
			        CNRangeLength:[0,255]
			    },                              
			    "clientBean.big":{            
			        CNRangeLength:[0,255]
			    },                              
			    "clientBean.updateDate":{            
			        number:true,range:[0,9999999999]
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
<form name="form1" id="form1" action="#" method="post" target="fram" >   
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 
  <tr>                                    
    	<td align="right" width="20%" > 
                            登陆名<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.username" id="username"  value="${clientBean.username}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            密码<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.password" id="password"  value="${clientBean.password}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            邮箱<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.email" id="email"  value="${clientBean.email}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            手机号<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.phoneNumber" id="phoneNumber"  value="${clientBean.phoneNumber}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            生日<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.birthday" id="birthday"  value="${clientBean.birthday}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            名<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.firstName" id="firstName"  value="${clientBean.firstName}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            姓<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.lastName" id="lastName"  value="${clientBean.lastName}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            性别<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.render" id="render"  value="${clientBean.render}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            年龄<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.age" id="age"  value="${clientBean.age}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            描述<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.description" id="description"  value="${clientBean.description}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            小头像<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.small" id="small"  value="${clientBean.small}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            大头像<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.big" id="big"  value="${clientBean.big}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            更新时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientBean.updateDate" id="updateDate"  value="${clientBean.updateDate}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%">&nbsp;</td> 
    	<td></td>                        
  </tr>                                
  <tr>                                
      <td>&nbsp;</td>		
      <td colspan="3"><input type="button" name="保存" value="保存" onclick="save();" class="GF-btn"/></td>		
  </tr>                                
</table>                                 
<iframe name="fram" id="fram" style="display:none"></iframe>   
</form>                                  
</body>                                  
</html>                                  
