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
			Dialog.opener().document.getElementById("clientId").value = $("#clientId").val()||"";    
			Dialog.opener().document.getElementById("accessToken").value = $("#accessToken").val()||"";    
			Dialog.opener().document.getElementById("refreshToken").value = $("#refreshToken").val()||"";    
			Dialog.opener().document.getElementById("expiresIn").value = $("#expiresIn").val()||"";    
			Dialog.opener().document.getElementById("expiresInRefresh").value = $("#expiresInRefresh").val()||"";    
			Dialog.opener().document.getElementById("time").value = $("#time").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "clientTokenBean.clientId":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "clientTokenBean.accessToken":{            
			        CNRangeLength:[0,512]
			    },                              
			    "clientTokenBean.refreshToken":{            
			        CNRangeLength:[0,512]
			    },                              
			    "clientTokenBean.expiresIn":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "clientTokenBean.expiresInRefresh":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "clientTokenBean.time":{            
			        number:true,range:[0,9999999999]
			    }                              
			},                                  
			messages:{                          
//			    "clientTokenBean.code":{          
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
                            clientid<span class="mark"></span>   
        </td>                            
        <td>                             
            <s:select list="#request.clientList" emptyOption="true" cssClass="GF-field" name="clientTokenBean.clientId" id="clientId"   theme="simple" listKey="id" listValue="name"  value="#request.clientTokenBean.clientId" ></s:select>
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            操作令牌<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.accessToken" id="accessToken"  value="${clientTokenBean.accessToken}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            刷新令牌<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.refreshToken" id="refreshToken"  value="${clientTokenBean.refreshToken}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            过期时间（单位:秒）<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.expiresIn" id="expiresIn"  value="${clientTokenBean.expiresIn}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            刷新令牌过期时间(单位:秒)<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.expiresInRefresh" id="expiresInRefresh"  value="${clientTokenBean.expiresInRefresh}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.time" id="time"  value="${clientTokenBean.time}" class="GF-field"/>   
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
