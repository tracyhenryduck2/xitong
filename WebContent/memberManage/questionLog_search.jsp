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
			Dialog.opener().document.getElementById("type").value = $("#type").val()||"";    
			Dialog.opener().document.getElementById("title").value = $("#title").val()||"";    
			Dialog.opener().document.getElementById("dsc").value = $("#dsc").val()||"";    
			Dialog.opener().document.getElementById("ver").value = $("#ver").val()||"";    
			Dialog.opener().document.getElementById("createtime").value = $("#createtime").val()||"";    
			Dialog.opener().document.getElementById("answer").value = $("#answer").val()||"";    
			Dialog.opener().document.getElementById("answertime").value = $("#answertime").val()||"";    
			Dialog.opener().document.getElementById("createid").value = $("#createid").val()||"";    
			Dialog.opener().document.form1.submit();                     
			ownerDialog.close();
	}                                       
	                                        
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "questionLogBean.type":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "questionLogBean.title":{            
			        CNRangeLength:[0,255]
			    },                              
			    "questionLogBean.dsc":{            
			        CNRangeLength:[0,255]
			    },                              
			    "questionLogBean.ver":{            
			        CNRangeLength:[0,255]
			    },                              
			    "questionLogBean.createtime":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "questionLogBean.answer":{            
			        CNRangeLength:[0,255]
			    },                              
			    "questionLogBean.answertime":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "questionLogBean.createid":{            
			        number:true,range:[0,9999999999]
			    }                              
			},                                  
			messages:{                          
//			    "questionLogBean.code":{          
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
                            是安卓还是苹果问题,0代表安卓，1代表IOS，2代表其他<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="questionLogBean.type" id="type"  value="${questionLogBean.type}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            主题<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="questionLogBean.title" id="title"  value="${questionLogBean.title}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            问题描述<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="questionLogBean.dsc" id="dsc"  value="${questionLogBean.dsc}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            APP版本号信息和网关固件版本信息<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="questionLogBean.ver" id="ver"  value="${questionLogBean.ver}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            创建时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="questionLogBean.createtime" id="createtime"  value="${questionLogBean.createtime}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            回答<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="questionLogBean.answer" id="answer"  value="${questionLogBean.answer}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            回答时间<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="questionLogBean.answertime" id="answertime"  value="${questionLogBean.answertime}" class="GF-field"/>   
        </td>                            
  </tr>                                
  <tr>                                    
    	<td align="right" width="30%" > 
                            创建者id<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="questionLogBean.createid" id="createid"  value="${questionLogBean.createid}" class="GF-field"/>   
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
