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
			Dialog.opener().document.getElementById("sort").value = $("#sort").val()||"";    
			Dialog.opener().document.getElementById("parentId").value = $("#parentId").val()||"";  
			Dialog.opener().document.getElementById("authority").value = $("#authority").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "sFunctionGroupBean.name":{            
			        CNRangeLength:[0,45]
			    },                              
			    "sFunctionGroupBean.sort":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "sFunctionGroupBean.parentId":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "sFunctionGroupBean.authority":{            
			        number:true,range:[0,9999999999]
			    }                              
			},                                  
			messages:{                          
//			    "sFunctionGroupBean.code":{          
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
                            名称<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="sFunctionGroupBean.name" id="name"  value="${sFunctionGroupBean.name}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            排序<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="sFunctionGroupBean.sort" id="sort"  value="${sFunctionGroupBean.sort}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            上一级<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="sFunctionGroupBean.parentId" id="parentId"  value="${sFunctionGroupBean.parentId}" class="GF-field"/>   
        </td>                            
  </tr>
    <tr>                                    
    	<td align="right" width="30%" > 
                            权限<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="sFunctionGroupBean.authority" id="authority"  value="${sFunctionGroupBean.authority}" class="GF-field"/>   
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
