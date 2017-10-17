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
			Dialog.opener().document.getElementById("addr").value = $("#addr").val()||"";    
	/* 		Dialog.opener().document.getElementById("time").value = $("#time").val()||"";  */      
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                                                         
			    "installBean.username":{            
			        CNRangeLength:[0,255]
			    },                              
			    "installBean.addr":{            
			        CNRangeLength:[0,255]
			    },                                                           
			    /* "installBean.time":{            
			        number:true,range:[0,9999999999]
			    }   */                           
			},                                  
			messages:{                          
//			    "questionLogBean.code":{          
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
                            安装人员账号<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="installBean.username" id="username"  value="${installBean.username}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            安装地址<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="installBean.addr" id="addr"  value="${installBean.addr}" class="GF-field"/>   
        </td>                            
  </tr>                                                             
  <tr>                                    
    	<%-- <td align="right" width="30%" > 
                            安装时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="installBean.time" id="time"  value="${installBean.time}" class="GF-field"/>   
        </td>   --%>                          
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