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
			Dialog.opener().document.getElementById("hekrid").value = $("#hekrid").val()||"";    
			Dialog.opener().document.getElementById("sex").value = $("#sex").val()||"";    
			Dialog.opener().document.getElementById("phone").value = $("#phone").val()||"";    
			Dialog.opener().document.getElementById("username").value = $("#username").val()||"";    
			Dialog.opener().document.getElementById("email").value = $("#email").val()||"";    
			Dialog.opener().document.getElementById("ctime").value = $("#ctime").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "memberBean.hekrid":{            
			        CNRangeLength:[0,45]
			    },                              
			    "memberBean.sex":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "memberBean.phone":{            
			        CNRangeLength:[0,45]
			    },                              
			    "memberBean.username":{            
			        CNRangeLength:[0,255]
			    },                              
			    "memberBean.email":{            
			        CNRangeLength:[0,11]
			    },                              
			    "memberBean.ctime":{            
			        number:true,range:[0,9999999999]
			    }                              
			},                                  
			messages:{                          
//			    "memberBean.code":{          
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
                            hekrid<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.hekrid" id="hekrid"  value="${memberBean.hekrid}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            性别<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.sex" id="sex"  value="${memberBean.sex}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            手机<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.phone" id="phone"  value="${memberBean.phone}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            姓名<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.username" id="username"  value="${memberBean.username}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            邮箱<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.email" id="email"  value="${memberBean.email}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            绑定此平台时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.ctime" id="ctime"  value="${memberBean.ctime}" class="GF-field"/>   
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
