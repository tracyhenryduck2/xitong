<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"  %>
<%@ taglib uri="/gdk-tags" prefix="GF"%>
<%@ taglib uri="/gui-tags" prefix="g"%>
<%@ taglib uri="http://org.mytaglibs/page-tag" prefix="p"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/gdk.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataGrid.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/cySearchBox.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/uploadify/uploadify.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery1.9.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/gdk.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ExtPrototype.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cySearchBox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<style media="print" type="text/css">   
	.noprint{visibility:hidden;display: none;}  
</style>  
<script>
 var g_Path = "<%=request.getContextPath()%>";
 $(function(){
	 if("${read}"=="1") {
		 //$("input[type='text']").removeClass("GF-field");
		 //$("input[type='text']").addClass("GF-field-ReadOnly");
		 $("input[type='file']").attr("disabled","disabled");
		 $("input[type='text']").attr("disabled","disabled");
		 $("input[type='checkbox']").attr("disabled","disabled");
		 //$("select").removeClass("GF-field");
		// $("select").addClass("GF-field-ReadOnly");
		 $("select").attr("disabled","disabled");
		 $("input[type='button']").hide();
		 $("input[type='radio']").attr("disabled","disabled");
		 $("textarea").attr("disabled","disabled");
		 $("input[type='button']").addClass("noprint");
		 $("input[type='button']").last().after("<input type=\"button\" name=\"打印\" value=\"打印\" onclick=\"window.print();\" class=\"GF-btn noprint\" style=\"margin-left:10px;\"/>");
		 $(":disabled").css({'color':'gray','border-color':'#CCC'});
	 }
 });
</script>