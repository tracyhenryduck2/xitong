<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ include file="/common/taglibs.jsp" %>  
<%                                       
String path = request.getContextPath();  
%>                                       
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>                                   
<title></title> 
  <link rel="stylesheet" href="<%=path %>/kindeditor/themes/default/default.css" />
  <link rel="stylesheet" href="<%=path %>/kindeditor/plugins/code/prettify.css" />
  <script charset="utf-8" src="<%=path %>/kindeditor/kindeditor.js"></script>
  <script charset="utf-8" src="<%=path %>/kindeditor/lang/zh_CN.js"></script>
  <script charset="utf-8" src="<%=path %>/kindeditor/plugins/code/prettify.js"></script>
    <style>
        .uploadify-button {
          width: 12%;
          height: 31px;
          line-height: 31px;
      }
  </style> 

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
				Dialog.opener().location.reload(); //;= "<%=path%>/memberManage/QuestionLog!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
	}                                       
	                                        
	$(function(){        



    KindEditor.ready(function(K) {
      var editor1 = K.create('textarea[name="questionLogBean.dsc"]', {
        allowUpload : true,
        cssPath : '<%=path%>/kindeditor/plugins/code/prettify.css',
        uploadJson : "<%=path%>/kindeditor/jsp/upload_json.jsp?dirName=article",
        fileManagerJson : '<%=path%>/kindeditor/jsp/file_manager_json.jsp',
        allowFileManager : true,
        allowImageUpload : true,
        urlType:'relative',
        afterBlur: function(){this.sync();},
        afterCreate : function() {
          var self = this;
          K.ctrl(document, 13, function() {
            self.sync();
            if($("#form1").valid()) {
              document.forms['form1'].submit();
            }
          });
        }
      });
      prettyPrint();
    });



		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "questionLogBean.type":{            
			        required : true,number:true,range:[0,9999999999]
			    },                              
			    "questionLogBean.title":{            
			        CNRangeLength:[0,255]
			    },                              
			    "questionLogBean.dsc":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "questionLogBean.createtime":{            
			        number:true,range:[0,9999999999]
			    },                              
			    "questionLogBean.answer":{            
			        CNRangeLength:[0,65535]
			    },                              
			    "questionLogBean.answertime":{            
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
<form name="form1" id="form1" action="<%=path %>/memberManage/QuestionLog!addQuestionLog.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="questionLogBean.id" id="id" value="${questionLogBean.id }"/>  
<input type="hidden" name="questionLogBean.createtime" id="createtime" value="${questionLogBean.createtime }"/>
<input type="hidden" name="questionLogBean.createid" id="createid" value="${questionLogBean.createid}"/> 
<input type="hidden" name="questionLogBean.answertime" id="answertime" value="${questionLogBean.answertime }"/>  
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 

  <tr>                                    
      <td align="right" > 
                            是安卓还是苹果问题<span class="mark">*</span>   
        </td>                            
        <td>                             
          <s:select list="#{'0':'安卓','1':'苹果','2':'其他'}" listKey="key" listValue="value" name="questionLogBean.type" id="sex" theme="simple"  value="#request.questionLogBean.type" cssClass="GF-field"></s:select> 
        </td>                            
  </tr> 

  <tr>                                    
    	<td align="right" > 
                            主题<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="questionLogBean.title" id="title"  value="${questionLogBean.title}" class="GF-field"/>   
        </td>                            
  </tr>                                


  <tr>                                    
      <td align="right" > 
                            问题描述<span class="mark">*</span>   
        </td>                            
        <td>                             
          <textarea cols="120" rows="16" style="width:800px;height:400px;visibility:hidden;" name="questionLogBean.dsc" id="dsc" >${questionLogBean.dsc}</textarea>
        </td>                            
  </tr>  
   <tr>                                    
    	<td align="right" > 
                            发现的版本信息<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text"    name="questionLogBean.ver" id="ver"  value="${questionLogBean.ver}" class="GF-field"  />   
        </td>                            
  </tr>
                            
   <tr <s:if test="#session.user.type>1 && read == 0"> style="display:none;"</s:if>>                                    
    	<td align="right" > 
                            回答<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text"    name="questionLogBean.answer" id="answer"  value="${questionLogBean.answer}" class="GF-field"  />   
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