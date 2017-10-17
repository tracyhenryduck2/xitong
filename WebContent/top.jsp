<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<style type="text/css">
a:link {
	color: #CCC;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #CCC;
}
a:hover {
	text-decoration: none;
	color: #999;
}
a:active {
	text-decoration: none;
	color: #CCC;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.top{ width:100%; height:81px; background:#2296d3}
.top_font{color:white;} 
#info{float:right;margin:20px 10px;}
</style>
<script type="text/javascript">
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadimages2() { //v3.0
  var d=document; if(d.images2){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadimages2.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function logout(){
	window.parent.document.location="Index!logout.action";
}

function changePass(){
	Dialog.open({Title:"修改密码", Width:300, Height:200, URL:"javascript:void(0)"});
}


</script>
</head>
<body>
<div class="top"><div id="info"><span class="top_font">欢迎${sessionScope.user.username}登录系统</span>|<a href="javascript:changePass()"><span class="top_font">修改密码</span></a>|<a href="javascript:logout()"><span class="top_font">退出</span></a></div></div>
<!-- End Save for Web Slices -->
</body>
</html>