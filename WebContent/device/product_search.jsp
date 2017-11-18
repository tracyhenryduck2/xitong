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
			Dialog.opener().document.getElementById("name").value = $("#name").val()||"";    
			Dialog.opener().document.getElementById("pid").value = $("#pid").val()||"";    
			Dialog.opener().document.getElementById("model").value = $("#model").val()||"";    
			Dialog.opener().document.getElementById("desp").value = $("#desp").val()||"";    
			Dialog.opener().document.getElementById("img").value = $("#img").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "productBean.name":{            
			        CNRangeLength:[0,45]
			    },                              
			    "productBean.pid":{            
			        CNRangeLength:[0,255]
			    },                              
			    "productBean.model":{            
			        CNRangeLength:[0,45]
			    },                              
			    "productBean.desp":{            
			        CNRangeLength:[0,4294967295]
			    },                              
			    "productBean.img":{            
			        CNRangeLength:[0,255]
			    }                              
			},                                  
			messages:{                          
//			    "productBean.code":{          
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
                            设备型号名称<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.name" id="name"  value="${productBean.name}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            设备种类标识<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.pid" id="pid"  value="${productBean.pid}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            设备型号<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.model" id="model"  value="${productBean.model}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            介绍<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.desp" id="desp"  value="${productBean.desp}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            产品图片<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.img" id="img"  value="${productBean.img}" class="GF-field"/>   
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
