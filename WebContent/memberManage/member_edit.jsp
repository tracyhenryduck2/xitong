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
				Dialog.opener().location.reload(); //;= "<%=path%>/memberManage/Member!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
	}                                       
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "memberBean.hekrid":{            
			        CNRangeLength:[0,45]
			    },                              
			    "memberBean.sex":{            
			        required : true,number:true,range:[0,9999999999]
			    },                              
			    "memberBean.phone":{            
			        CNRangeLength:[0,45]
			    },                              
			    "memberBean.username":{            
			        CNRangeLength:[0,255]
			    },                              
			    "memberBean.email":{            
			        CNRangeLength:[0,11]
			    },                              
			    "memberBean.ctime":{            
			        number:true,range:[0,9999999999]
			    }                              
			},                                  
			messages:{                          
//			    "memberBean.code":{          
//				    required : "",CNRangeLength:""  
//			    },                            
			}                                   
		});                                   
	});                                     
	                                        
	                                        
</script>                                
</head>                                  
<body>                                   
<form name="form1" id="form1" action="<%=path %>/memberManage/Member!addMember.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="memberBean.id" id="id" value="${memberBean.id }"/>  
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 
  <tr>                                    
    	<td align="right" width="30%" > 
                            hekrid<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.hekrid" id="hekrid"  value="${memberBean.hekrid}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            性别<span class="mark">*</span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.sex" id="sex"  value="${memberBean.sex}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            手机<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.phone" id="phone"  value="${memberBean.phone}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            姓名<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.username" id="username"  value="${memberBean.username}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            邮箱<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.email" id="email"  value="${memberBean.email}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            绑定此平台时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="memberBean.ctime" id="ctime"  value="${memberBean.ctime}" class="GF-field"/>   
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
