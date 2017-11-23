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
				Dialog.opener().location.reload(); //;= "<%=path%>/device/Product!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
	}                                       
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "productBean.name":{            
			        CNRangeLength:[0,45]
			    },                              
			    "productBean.prodKey":{            
			        CNRangeLength:[0,255]
			    },                              
			    "productBean.model":{            
			        CNRangeLength:[0,45]
			    },                              
			    "productBean.desp":{            
			        CNRangeLength:[0,4294967295]
			    },                              
			    "productBean.img":{            
			        CNRangeLength:[0,255]
			    },                              
			    "productBean.forceBind":{            
			        required : true,number:true,range:[0,9999999999]
			    }                              
			},                                  
			messages:{                          
//			    "productBean.code":{          
//				    required : "",CNRangeLength:""  
//			    },                            
			}                                   
		});                                   
	});                                     
	                                        
	                                        
</script>                                
</head>                                  
<body>                                   
<form name="form1" id="form1" action="<%=path %>/device/Product!addProduct.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="productBean.id" id="id" value="${productBean.id }"/>  
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 
  <tr>                                    
    	<td align="right" width="30%" > 
                            设备型号名称<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.name" id="name"  value="${productBean.name}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr class="hidew">                                    
    	<td align="right" > 
                            设备种类标识<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.prodKey" id="prodKey"  value="${productBean.prodKey}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            设备型号<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.model" id="model"  value="${productBean.model}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            介绍<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.desp" id="desp"  value="${productBean.desp}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            产品图片<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.img" id="img"  value="${productBean.img}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" > 
                            是否强制绑定<span class="mark">*</span>   
        </td>                            
        <td>                             
            <input type="text" name="productBean.forceBind" id="forceBind"  value="${productBean.forceBind}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr class="hidew">                                    
    	<td align="right" > 
                            生成时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <GF:ConvertTime format="yyyy-MM-dd HH:mm:ss" value="${productBean.ctime}"/>   
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
