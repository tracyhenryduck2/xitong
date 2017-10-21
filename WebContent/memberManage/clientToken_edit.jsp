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
				Dialog.opener().location.reload(); //;= "<%=path%>/memberManage/ClientToken!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
	}                                       
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "clientTokenBean.clientId":{            
			        required : true,number:true,range:[0,9999999999]
			    },                              
			    "clientTokenBean.accessToken":{            
			        required : true,CNRangeLength:[0,512]
			    },                              
			    "clientTokenBean.refreshToken":{            
			        required : true,CNRangeLength:[0,512]
			    },                              
			    "clientTokenBean.expiresIn":{            
			        required : true,number:true,range:[0,9999999999]
			    },                              
			    "clientTokenBean.expiresInRefresh":{            
			        required : true,number:true,range:[0,9999999999]
			    },                              
			    "clientTokenBean.time":{            
			        required : true,number:true,range:[0,9999999999]
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
<form name="form1" id="form1" action="<%=path %>/memberManage/ClientToken!addClientToken.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="clientTokenBean.id" id="id" value="${clientTokenBean.id }"/>  
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 
  <tr>                                    
    	<td align="right" width="30%" > 
                            clientid<span class="mark">*</span>   
        </td>                            
        <td>                             
            <s:select list="#request.clientList"  cssClass="GF-field" name="clientTokenBean.clientId" id="clientId"   theme="simple" listKey="id" listValue="name"  value="#request.clientTokenBean.clientId" ></s:select>
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            操作令牌<span class="mark">*</span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.accessToken" id="accessToken"  value="${clientTokenBean.accessToken}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            刷新令牌<span class="mark">*</span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.refreshToken" id="refreshToken"  value="${clientTokenBean.refreshToken}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            过期时间（单位:秒）<span class="mark">*</span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.expiresIn" id="expiresIn"  value="${clientTokenBean.expiresIn}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            刷新令牌过期时间(单位:秒)<span class="mark">*</span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.expiresInRefresh" id="expiresInRefresh"  value="${clientTokenBean.expiresInRefresh}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            时间<span class="mark">*</span>   
        </td>                            
        <td>                             
            <input type="text" name="clientTokenBean.time" id="time"  value="${clientTokenBean.time}" class="GF-field"/>   
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
