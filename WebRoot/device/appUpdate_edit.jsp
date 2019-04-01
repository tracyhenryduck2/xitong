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
				Dialog.opener().location.reload(); //;= "<%=path%>/device/AppUpdate!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
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
			    },                              
			    "appUpdateBean.ios":{            
			        number:true,range:[0,9999999999]
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
<form name="form1" id="form1" action="<%=path %>/device/AppUpdate!addAppUpdate.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="appUpdateBean.id" id="id" value="${appUpdateBean.id }"/>  
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
    	<td align="right"  > 
                            app下载的路径<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.url" id="url"  value="${appUpdateBean.url}" class="GF-field"/>   
        </td>                            
    	<td align="right"  > 
                            英文说明<span class="mark"></span>   
        </td>                            
        <td>      
        <textarea style="height:100px" name="appUpdateBean.en" id="en"   class="GF-field">
        ${appUpdateBean.en}
</textarea>                        
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right"  > 
                            中文说明<span class="mark"></span>   
        </td>                            
        <td>                             
        <textarea style="height:100px" name="appUpdateBean.zh" id="zh"  class="GF-field">
${appUpdateBean.zh}
</textarea> 
        </td>                            
    	<td align="right"  > 
                            法文说明<span class="mark"></span>   
        </td>                            
        <td>                             
                    <textarea style="height:100px" name="appUpdateBean.fr" id="fr"  class="GF-field">
  ${appUpdateBean.fr}
</textarea>      
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right"  > 
                            德文说明<span class="mark"></span>   
        </td>                            
        <td>                             
                   <textarea style="height:100px" name="appUpdateBean.de" id="de"  class="GF-field">
  ${appUpdateBean.de}
</textarea>     
        </td>                            
    	<td align="right"  > 
                            西班牙文说明<span class="mark"></span>   
        </td>                            
        <td>                             
                   <textarea style="height:100px" name="appUpdateBean.es" id="es"  class="GF-field">
${appUpdateBean.es}
</textarea>     
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right"  > 
                            荷兰文说明<span class="mark"></span>   
        </td>                            
        <td>                             
                   <textarea style="height:100px" name="appUpdateBean.nl" id="nl" class="GF-field">
 ${appUpdateBean.nl}
</textarea>    
        </td>                            
    	<td align="right"  > 
                            芬兰文说明<span class="mark"></span>   
        </td>                            
        <td>                             
                   <textarea style="height:100px" name="appUpdateBean.fi" id="fi"  class="GF-field">
                   ${appUpdateBean.fi}
</textarea>      
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right"  > 
                            斯洛文尼亚语说明<span class="mark"></span>   
        </td>                            
        <td>                             
                   <textarea style="height:100px" name="appUpdateBean.sl" id="sl" class="GF-field">
${appUpdateBean.sl}
</textarea>    
        </td>                            
    	<td align="right"  > 
                            意大利语说明<span class="mark"></span>   
        </td>                            
        <td>                             
                   <textarea style="height:100px" name="appUpdateBean.it" id="it"  class="GF-field">
${appUpdateBean.it}
</textarea>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right"  > 
                            捷克语说明<span class="mark"></span>   
        </td>                            
        <td>                             
                   <textarea style="height:100px" name="appUpdateBean.cs" id="cs" class="GF-field">
${appUpdateBean.cs}
</textarea>     
        </td>                            
    	<td align="right"  > 
                            app下载的路径国外<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.urlEx" id="urlEx"  value="${appUpdateBean.urlEx}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right"  > 
                            是否是ios<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="appUpdateBean.ios" id="ios"  value="${appUpdateBean.ios}" class="GF-field"/>   
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
