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
				Dialog.opener().location.reload(); //;= "<%=path%>/memberManage/ChargeLog!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
	}                                       
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "chargeLogBean.chargeMoney":{            
			        CNRangeLength:[0,]
			    },                              
			    "chargeLogBean.chargeTime":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "chargeLogBean.initStorage":{            
			        CNRangeLength:[0,]
			    },                              
			    "chargeLogBean.userId":{            
			        number:true,range:[0,9999999999]
			    }                              
			},                                  
			messages:{                          
//			    "chargeLogBean.code":{          
//				    required : "",CNRangeLength:""  
//			    },                            
			}                                   
		});                                   
	});                                     
	                                        
	                                        
</script>                                
</head>                                  
<body>                                   
<form name="form1" id="form1" action="<%=path %>/memberManage/ChargeLog!addChargeLog.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="chargeLogBean.id" id="id" value="${chargeLogBean.id }"/>  
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 
  <tr>                                    
    	<td align="right" width="30%" > 
                            充值金额<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="chargeLogBean.chargeMoney" id="chargeMoney"  value="${chargeLogBean.chargeMoney}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            充值时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="chargeLogBean.chargeTime" id="chargeTime"  value="${chargeLogBean.chargeTime}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            初始余额<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="chargeLogBean.initStorage" id="initStorage"  value="${chargeLogBean.initStorage}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            用户id<span class="mark"></span>   
        </td>                            
        <td>                             
            <s:select list="#request.userList"  cssClass="GF-field" name="chargeLogBean.userId" id="userId"   theme="simple" listKey="id" listValue="name"  value="#request.chargeLogBean.userId" ></s:select>
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
