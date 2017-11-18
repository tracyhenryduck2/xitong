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
			Dialog.opener().document.getElementById("devTid").value = $("#devTid").val()||"";    
			Dialog.opener().document.getElementById("token").value = $("#token").val()||"";    
			Dialog.opener().document.getElementById("ctrlKey").value = $("#ctrlKey").val()||"";    
			Dialog.opener().document.getElementById("bindKey").value = $("#bindKey").val()||"";    
			Dialog.opener().document.getElementById("mac").value = $("#mac").val()||"";    
			Dialog.opener().document.getElementById("binVer").value = $("#binVer").val()||"";    
			Dialog.opener().document.getElementById("binType").value = $("#binType").val()||"";    
			Dialog.opener().document.getElementById("ip").value = $("#ip").val()||"";    
			Dialog.opener().document.getElementById("online").value = $("#online").val()||"";    
			Dialog.opener().document.getElementById("pid").value = $("#pid").val()||"";    
			Dialog.opener().document.getElementById("name").value = $("#name").val()||"";    
			Dialog.opener().document.getElementById("ssid").value = $("#ssid").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "deviceBean.devTid":{            
			        CNRangeLength:[0,45]
			    },                              
			    "deviceBean.token":{            
			        CNRangeLength:[0,255]
			    },                              
			    "deviceBean.ctrlKey":{            
			        CNRangeLength:[0,255]
			    },                              
			    "deviceBean.bindKey":{            
			        CNRangeLength:[0,255]
			    },                              
			    "deviceBean.mac":{            
			        CNRangeLength:[0,45]
			    },                              
			    "deviceBean.binVer":{            
			        CNRangeLength:[0,45]
			    },                              
			    "deviceBean.binType":{            
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
			    },                              
			    "deviceBean.ssid":{            
			        CNRangeLength:[0,255]
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
            <input type="text" name="deviceBean.devTid" id="devTid"  value="${deviceBean.devTid}" class="GF-field"/>   
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
            <input type="text" name="deviceBean.ctrlKey" id="ctrlKey"  value="${deviceBean.ctrlKey}" class="GF-field"/>   
        </td>                            
    	<td align="right" width="20%" > 
                            绑定码<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.bindKey" id="bindKey"  value="${deviceBean.bindKey}" class="GF-field"/>   
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
            <input type="text" name="deviceBean.binVer" id="binVer"  value="${deviceBean.binVer}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="20%" > 
                            设备固件类型A,B<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.binType" id="binType"  value="${deviceBean.binType}" class="GF-field"/>   
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
    	<td align="right" width="20%" > 
                            wifi名称<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="deviceBean.ssid" id="ssid"  value="${deviceBean.ssid}" class="GF-field"/>   
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
