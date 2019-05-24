<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ include file="/common/taglibs.jsp" %>  
<%                                       
String path = request.getContextPath();  
%>                                       
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>                                   
<title></title>
		<style>
				.uploadify-button {
			    width: 12%;
			    height: 31px;
			    line-height: 31px;
			}
	</style>                            
<script type="text/javascript">        

	                                        
	function result(messageType, message){  
		if(messageType=="error"){           
			Dialog.error(message);				      
		} else if (messageType == "reload_success") {   
			Dialog.alert(message,function(){    
				Dialog.opener().location.reload(); //;= "<%=path%>/device/Instructions!list.action";   
				parentDialog.close();             
			});                                 
		} else if(messageType == "exception") { 
			                                    
		}                                     
	}                                       
	                                        
	$(function(){                           
		/* form1表单进行验证 */               
		$("#form1").validate({              
			rules:{                             
			    "instructionsBean.name":{            
			        CNRangeLength:[0,100]
			    },                              
			    "instructionsBean.fileUrl":{            
			        CNRangeLength:[0,100]
			    } ,                              
			    "instructionsBean.fileUrlEn":{            
			        CNRangeLength:[0,100]
			    }                                
			},                                  
			messages:{                          
//			    "instructionsBean.code":{          
//				    required : "",CNRangeLength:""  
//			    },                            
			}                                   
		});  


		setTimeout(function(){


			
							var up = $('#upload').Huploadify({
						auto:true,
						fileTypeExts:'*.*',
						multi:true,
						fileSizeLimit:99999999999,
						showUploadedPercent:true,
						showUploadedSize:true,
						removeTimeout:9999999,
						uploader : '<%=request.getContextPath()%>/device/Instructions!importFile.action',
						onUploadSuccess : function(file, data, response) {
							if(data!=null){
								$("#msg").html(data);
								}
									$("#fileUrl").val(data);
									Dialog.alert("上传成功！");
									$("#_del").hide();
									$("#_del1").show();
									
						    },
						    'onSWFReady': function() {
						    	
							}
						});

						var up2 = $('#uploadEn').Huploadify({
						auto:true,
						fileTypeExts:'*.*',
						multi:true,
						fileSizeLimit:99999999999,
						showUploadedPercent:true,
						showUploadedSize:true,
						removeTimeout:9999999,
						uploader : '<%=request.getContextPath()%>/device/Instructions!importFile.action',
						onUploadSuccess : function(file, data, response) {
							if(data!=null){
								$("#msgEn").html(data);
								}
									$("#fileUrlEn").val(data);
									Dialog.alert("上传成功！");
									$("#_delEn").hide();
									$("#_del1En").show();
									
						    },
						    'onSWFReady': function() {
						    	
							}
						});
		},10);


	});                                     
	                                        
function removeFile1() {
	 	Dialog.confirm("确定删除附件吗？", function(){     
		$.ajax({
		 type :"post",
		 url : '<%=request.getContextPath()%>/device/Instructions!removeFile.action',
				data : {
					"fileName" : $("#fileUrl").val()
				},
				success : function(data) {
                         $("#fileUrl").val("");

						if(data)
						{
							
							Dialog.alert("删除成功！");
							$("#_del1").hide();
							$("#_del").show();
						}
						else
						{
							Dialog.alert("删除失败！");
						}
				}
			});
}, function() {
	//否      
});                                        
}	

function removeFile2() {
	 	Dialog.confirm("确定删除附件吗？", function(){     
		$.ajax({
		 type :"post",
		 url : '<%=request.getContextPath()%>/device/Instructions!removeFile.action',
				data : {
					"fileName" : $("#fileUrlEn").val()
				},
				success : function(data) {
                         $("#fileUrlEn").val("");

						if(data)
						{
							
							Dialog.alert("删除成功！");
							$("#_del1En").hide();
							$("#_delEn").show();
						}
						else
						{
							Dialog.alert("删除失败！");
						}
				}
			});
}, function() {
	//否      
});                                        
}	                                        
</script>                                
</head>                                  
<body>                                   
<form name="form1" id="form1" action="<%=path %>/device/Instructions!addInstructions.action" method="post" target="fram" >   
<input type="hidden" name="oper" value="${oper}" />
<input type="hidden" name="instructionsBean.id" id="id" value="${instructionsBean.id }"/>
<input type="hidden" name="instructionsBean.fileUrl" id="fileUrl" value="${instructionsBean.fileUrl }"/>
<input type="hidden" name="instructionsBean.fileUrlEn" id="fileUrlEn" value="${instructionsBean.fileUrlEn }"/>      
<table cellpadding="0" cellspacing="0" width="100%" class="GF-grid"> 
  <tr>                                    
    	<td align="right" width="30%" > 
                            产品名称<span class="mark"></span>   
        </td>                            
        <td>                             
            <input type="text" name="instructionsBean.name" id="name"  value="${instructionsBean.name}" class="GF-field"/>   
        </td>                            
  </tr>                                

  <tr>
    <td align="right">
						附件
						<span class="mark"></span>
					</td>
					<s:if test="#request.oper==1">
						<s:if test="#request.instructionsBean.fileUrl!=''">
							<td id="_del" style="display: none">
								<div id="upload"></div>
							</td>
							<td id="_del1">
								<span id="msg">${instructionsBean.fileUrl}</span>
								<input type="button" value="删除" onclick="removeFile1()"
									class="GF-btn" />
							</td>
						</s:if>
						<s:else>
							<td id="_del">
								<div id="upload"></div>
							</td>
							<td id="_del1" style="display: none">
								<span id="msg"></span>
								<input type="button" value="删除" onclick="removeFile1()"
									class="GF-btn" />
							</td>
						</s:else>
					</s:if>
					<s:else>
						<td id="_del">
							<div id="upload"></div>
						</td>
						<td id="_del1" style="display: none">
							<span id="msg"></span>
							<input type="button" value="删除" onclick="removeFile1()"
								class="GF-btn" />
						</td>
					</s:else>                         
  </tr>


  <tr>
    <td align="right">
						附件(英文)
						<span class="mark"></span>
					</td>
					<s:if test="#request.oper==1">
						<s:if test="#request.instructionsBean.fileUrlEn!=''">
							<td id="_delEn" style="display: none">
								<div id="uploadEn"></div>
							</td>
							<td id="_del1En">
								<span id="msgEn">${instructionsBean.fileUrlEn}</span>
								<input type="button" value="删除" onclick="removeFile2()"
									class="GF-btn" />
							</td>
						</s:if>
						<s:else>
							<td id="_delEn">
								<div id="uploadEn"></div>
							</td>
							<td id="_del1En" style="display: none">
								<span id="msgEn"></span>
								<input type="button" value="删除" onclick="removeFile2()"
									class="GF-btn" />
							</td>
						</s:else>
					</s:if>
					<s:else>
						<td id="_delEn">
							<div id="uploadEn"></div>
						</td>
						<td id="_del1En" style="display: none">
							<span id="msg"></span>
							<input type="button" value="删除" onclick="removeFile2()"
								class="GF-btn" />
						</td>
					</s:else>                         
  </tr>

  <tr>                                
      <td>&nbsp;</td>		
      <td><input type="submit" name="提交" value="提交" class="GF-btn"/></td>			
  </tr>                                
</table>                                 
<iframe name="fram" id="fram" style="display:none"></iframe>   
</form>                                  
</body>                                  
</html>                                  
