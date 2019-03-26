<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <style type="text/css">
  	body{padding:0px ;margin:0px}
  	.riqi{ width:175px; font-size:12px; color:#666; text-align:center; font-family:宋体; height:29px; line-height:29px;}
	.wrap-menu,.wrap-menu1 {width:778px; overflow:auto; width:175px; background:#F6F6F6; font:12px/1.5 Tahoma,Arial,sans-serif}
	.wrap-menu ul,.wrap-menu1 ul{ list-style:none; margin:0; padding:0;}
	.wrap-menu ul li,.wrap-menu1 ul li{ text-indent:3em; white-space:nowrap; }
	.wrap-menu ul li h2,.wrap-menu1 ul li h2{ cursor:pointer; height:100%; width:100%; margin:0 0 1px 0; font:12px/31px '宋体'; color:#fff; background:rgb(34,150,211);}
	.wrap-menu ul li a{ display:block; outline:none; height:25px; line-height:25px; margin:1px 0; color:#1A385C; text-decoration:none;}
	.wrap-menu1 ul li a{ display:block; outline:none;margin:1px 0; color:#fff; text-decoration:none;}
	.wrap-menu ul li img{ margin-right:10px; margin-left:-17px; margin-top:9px; width:7px; height:7px; background:url(images/arrow.gif) no-repeat; border:none;}
	.wrap-menu ul li img.unfold{ background-position:0 -9px;}
	.wrap-menu ul li a:hover{ background-color:#ccc; background-image:none;}
  </style>
  <script src="js/jquery1.9.js"></script>
  <script src="js/zzsc.js"></script>
  <script type="text/javascript">
  function getDate(){
  return "今天是 "+new Date().getFullYear()+"年"+(new Date().getMonth()+1)+"月"+new Date().getDate()+"日 星期"+"日一二三四五六".charAt(new Date().getDay());
}
function s_getDate(){
  document.getElementById("s_date").innerHTML=getDate();
}
function myopen(url){
	parent.iframe_content.location=url;
}
$(function(){
	s_getDate();
	var testMenu=${sessionScope.menu};
	new AccordionMenu({menuArrs:testMenu});
});
  </script>
 </head>
 <body>
 	<div  id="s_date" class="riqi"></div>
	<div class="wrap-menu">
		
	</div>
</body>
</html>
