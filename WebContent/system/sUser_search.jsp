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
			Dialog.opener().document.getElementById("type").value = $("#type").val()||"";    
			Dialog.opener().document.getElementById("createtime").value = $("#createtime").val()||"";  
      Dialog.opener().document.getElementById("phone").value = $("#phone").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "sUserBean.username":{            
			        CNRangeLength:[0,255]
			    },                              
			    "sUserBean.password":{            
			        CNRangeLength:[0,255]
			    },                              
			    "sUserBean.type":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "sUserBean.createtime":{            
			        number:true,range:[0,9999999999]
			    },
          "sUserBean.phone":{            
              number:true,range:[0,9999999999]
          }                                 
			},                                  
			messages:{                          
//			    "sUserBean.code":{          
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
    	<td align="right" width="30%" > 
                            用户名<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="sUserBean.username" id="username"  value="${sUserBean.username}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            登录密码<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="sUserBean.password" id="password"  value="${sUserBean.password}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            类型1总管理员，2其他<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="sUserBean.type" id="type"  value="${sUserBean.type}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            创建时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="sUserBean.createtime" id="createtime"  value="${sUserBean.createtime}" class="GF-field"/>   
        </td>                            
  </tr> 
    <tr>                                    
      <td align="right" width="30%" > 
                            手机号<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="sUserBean.phone" id="phone"  value="${sUserBean.phone}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                
      <td>&nbsp;</td>		
      <td><input type="button" name="查询" value="查询" onclick="search();" class="GF-btn"/></td>		
  </tr>                                
</table>                                 
<iframe name="fram" id="fram" style="display:none"></iframe>   
</form>                                  
</body>                                  
</html>                                  
