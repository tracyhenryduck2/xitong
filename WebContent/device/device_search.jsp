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
			Dialog.opener().document.getElementById("deviceid").value = $("#deviceid").val()||"";    
			Dialog.opener().document.getElementById("token").value = $("#token").val()||"";    
			Dialog.opener().document.getElementById("ctrlkey").value = $("#ctrlkey").val()||"";    
			Dialog.opener().document.getElementById("bindkey").value = $("#bindkey").val()||"";    
			Dialog.opener().document.getElementById("mac").value = $("#mac").val()||"";    
			Dialog.opener().document.getElementById("binver").value = $("#binver").val()||"";    
			Dialog.opener().document.getElementById("bintype").value = $("#bintype").val()||"";    
			Dialog.opener().document.getElementById("ip").value = $("#ip").val()||"";    
			Dialog.opener().document.getElementById("online").value = $("#online").val()||"";    
			Dialog.opener().document.getElementById("pid").value = $("#pid").val()||"";    
			Dialog.opener().document.getElementById("name").value = $("#name").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "deviceBean.deviceid":{            
			        CNRangeLength:[0,45]
			    },                              
			    "deviceBean.token":{            
			        CNRangeLength:[0,255]
			    },                              
			    "deviceBean.ctrlkey":{            
			        CNRangeLength:[0,255]
			    },                              
			    "deviceBean.bindkey":{            
			        CNRangeLength:[0,255]
			    },                              
			    "deviceBean.mac":{            
			        CNRangeLength:[0,45]
			    },                              
			    "deviceBean.binver":{            
			        CNRangeLength:[0,45]
			    },                              
			    "deviceBean.bintype":{            
			        CNRangeLength:[0,2]
			    },                              
			    "deviceBean.ip":{            
			        CNRangeLength:[0,45]
			    },                              
			    "deviceBean.online":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "deviceBean.pid":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "deviceBean.name":{            
			        CNRangeLength:[0,45]
			    }                              
			},                                  
			messages:{                          
//			    "deviceBean.code":{          
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
                            设备id<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.deviceid" id="deviceid"  value="${deviceBean.deviceid}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            设备token<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.token" id="token"  value="${deviceBean.token}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            控制码<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.ctrlkey" id="ctrlkey"  value="${deviceBean.ctrlkey}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            绑定码<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.bindkey" id="bindkey"  value="${deviceBean.bindkey}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            设备mac地址<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.mac" id="mac"  value="${deviceBean.mac}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            设备固件版本号<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.binver" id="binver"  value="${deviceBean.binver}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            设备固件类型A,B<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.bintype" id="bintype"  value="${deviceBean.bintype}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            设备ip<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.ip" id="ip"  value="${deviceBean.ip}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            是否在线<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.online" id="online"  value="${deviceBean.online}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            设备种类id<span class="mark"></span>   
        </td>                            
        <td>                             
            <s:select list="#request.List" emptyOption="true" cssClass="GF-field" name="deviceBean.pid" id="pid"   theme="simple" listKey="id" listValue="name"  value="#request.deviceBean.pid" ></s:select>
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            设备名称<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.name" id="name"  value="${deviceBean.name}" class="GF-field"/>   
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
