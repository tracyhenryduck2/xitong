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
			Dialog.opener().document.getElementById("fileUrl").value = $("#fileUrl").val()||"";
			Dialog.opener().document.getElementById("fileUrlEn").value = $("#fileUrlEn").val()||"";     
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "instructionsBean.name":{            
			        CNRangeLength:[0,100]
			    },                              
			    "instructionsBean.fileUrl":{            
			        CNRangeLength:[0,100]
			    },                              
			    "instructionsBean.fileUrlEn":{            
			        CNRangeLength:[0,100]
			    }                               
			},                                  
			messages:{                          
//			    "instructionsBean.code":{          
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
                            产品名称<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="instructionsBean.name" id="name"  value="${instructionsBean.name}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            文件名<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="instructionsBean.fileUrl" id="fileUrl"  value="${instructionsBean.fileUrl}" class="GF-field"/>   
        </td>                            
  </tr>
    <tr>                                    
    	<td align="right" width="30%" > 
                            文件名(英文名)<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="instructionsBean.fileUrlEn" id="fileUrlEn"  value="${instructionsBean.fileUrlEn}" class="GF-field"/>   
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
