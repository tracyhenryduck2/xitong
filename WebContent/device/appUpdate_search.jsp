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
			Dialog.opener().document.getElementById("version").value = $("#version").val()||"";    
			Dialog.opener().document.getElementById("code").value = $("#code").val()||"";    
			Dialog.opener().document.getElementById("url").value = $("#url").val()||"";    
			Dialog.opener().document.getElementById("en").value = $("#en").val()||"";    
			Dialog.opener().document.getElementById("zh").value = $("#zh").val()||"";    
			Dialog.opener().document.getElementById("fr").value = $("#fr").val()||"";    
			Dialog.opener().document.getElementById("de").value = $("#de").val()||"";    
			Dialog.opener().document.getElementById("es").value = $("#es").val()||"";    
			Dialog.opener().document.getElementById("nl").value = $("#nl").val()||"";    
			Dialog.opener().document.getElementById("fi").value = $("#fi").val()||"";    
			Dialog.opener().document.getElementById("sl").value = $("#sl").val()||"";    
			Dialog.opener().document.getElementById("it").value = $("#it").val()||"";    
			Dialog.opener().document.getElementById("cs").value = $("#cs").val()||"";    
			Dialog.opener().document.getElementById("urlEx").value = $("#urlEx").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "appUpdateBean.version":{            
			        CNRangeLength:[0,11]
			    },                              
			    "appUpdateBean.code":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "appUpdateBean.url":{            
			        CNRangeLength:[0,300]
			    },                              
			    "appUpdateBean.en":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.zh":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.fr":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.de":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.es":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.nl":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.fi":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.sl":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.it":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.cs":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "appUpdateBean.urlEx":{            
			        CNRangeLength:[0,300]
			    }                              
			},                                  
			messages:{                          
//			    "appUpdateBean.code":{          
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
                            app版本号<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.version" id="version"  value="${appUpdateBean.version}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            app版本号代码<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.code" id="code"  value="${appUpdateBean.code}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            app下载的路径<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.url" id="url"  value="${appUpdateBean.url}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            英文说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.en" id="en"  value="${appUpdateBean.en}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            中文说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.zh" id="zh"  value="${appUpdateBean.zh}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            法文说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.fr" id="fr"  value="${appUpdateBean.fr}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            德文说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.de" id="de"  value="${appUpdateBean.de}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            西班牙文说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.es" id="es"  value="${appUpdateBean.es}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            荷兰文说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.nl" id="nl"  value="${appUpdateBean.nl}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            芬兰文说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.fi" id="fi"  value="${appUpdateBean.fi}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            斯洛文尼亚语说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.sl" id="sl"  value="${appUpdateBean.sl}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            意大利语说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.it" id="it"  value="${appUpdateBean.it}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            捷克语说明<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.cs" id="cs"  value="${appUpdateBean.cs}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            app下载的路径国外<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.urlEx" id="urlEx"  value="${appUpdateBean.urlEx}" class="GF-field"/>   
        </td>                            
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
