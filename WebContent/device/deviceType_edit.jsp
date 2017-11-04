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
				Dialog.opener().location.reload(); //;= "<%=path%>/device/DeviceType!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
	}                                       
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "deviceTypeBean.name":{            
			        CNRangeLength:[0,45]
			    },                              
			    "deviceTypeBean.pid":{            
			        CNRangeLength:[0,255]
			    },                              
			    "deviceTypeBean.model":{            
			        CNRangeLength:[0,45]
			    },                              
			    "deviceTypeBean.desp":{            
			        CNRangeLength:[0,4294967295]
			    },                              
			    "deviceTypeBean.img":{            
			        CNRangeLength:[0,255]
			    }                              
			},                                  
			messages:{                          
//			    "deviceTypeBean.code":{          
//				    required : "",CNRangeLength:""  
//			    },                            
			}                                   
		});                                   
	});                                     
	                                        
	                                        
</script>                                
</head>                                  
<body>                                   
<form name="form1" id="form1" action="<%=path %>/device/DeviceType!addDeviceType.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="deviceTypeBean.id" id="id" value="${deviceTypeBean.id }"/>  
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 
  <tr>                                    
    	<td align="right" width="30%" > 
                            设备型号名称<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceTypeBean.name" id="name"  value="${deviceTypeBean.name}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            设备种类标识<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceTypeBean.pid" id="pid"  value="${deviceTypeBean.pid}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            设备型号<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceTypeBean.model" id="model"  value="${deviceTypeBean.model}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            介绍<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceTypeBean.desp" id="desp"  value="${deviceTypeBean.desp}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            产品图片<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceTypeBean.img" id="img"  value="${deviceTypeBean.img}" class="GF-field"/>   
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
