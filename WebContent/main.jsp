<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
$(document).ready(function(){
	$(window).resize(function() {
		var sHeight = $(window).height();
		$("#tfm_table").css("height",sHeight);
		$("#iframe_left").css("height",sHeight-106);
		$("#iframe_content").css("height",sHeight-106);
	});
});

function switchSysBar()
{
	if($("#tfm_left").is(":hidden")){
		$("#tfm_left").css("width",175);
		$("#tfm_left").show();
		$("#img1").attr("src","<%=path%>/images/guanli_25.png");
	} else {
		$("#tfm_left").css("width",0);
		$("#tfm_left").hide();
		$("#img1").attr("src","<%=path%>/images/guanli_22.png");
	}
}


</script>
<title></title>
<style>
table{border-collapse:collapse;border-spacing:0;}
#tfm_table{ width:100%; height:100%; border:#006  solid 1px; padding:0px; margin:0px;}
#tfm_table td { border:0; padding:0px; margin:0px;}
#tfm_table .top { height:68px;}
#tfm_table .left { width:166px; height:100%; overflow:hidden;}
#tfm_table .middle_bar { width:10px;}
#tfm_header {height:56px; width:100%;}
#tfm_header .logo {float:left; height:56px;width:289px;background:url(<%=path%>/images/top_lf.jpg) no-repeat; }
#tfm_header .header_info {height:56px;background:url(<%=path%>/images/top_bg.jpg) repeat-x;}


#tfm_header .half_top { height:29px; width:100%; color:#FFF; line-height:29px;}
#tfm_header .half_bottom { height:28px; width:100%; color:#FFF;}
#tfm_header .role_name { float:left; height:28px; width:300px; line-height:28px; background:url(<%=path%>/images/top_lable.jpg) no-repeat;}

#tfm_header_menu { height:25px; width:100%; background:url(<%=path%>/images/menu_bg.jpg) repeat-x;}
#tfm_header_menu .menu_left {height:25px;float:left;background:url(<%=path%>/images/menu_left.jpg) left bottom no-repeat; width:155px; margin:0 0 0 4px; text-align:center;}
#tfm_header_menu .menu_left p {margin-top:8px; color:#FFF;}
#tfm_header_menu .menu_list { height:25px;margin-left:200px;}
#tfm_header_menu .menu_list ul {margin:0; padding:4px 0 0 70px;}
#tfm_header_menu .menu_list li { display:inline;}
#tfm_header_menu .menu_list a { float:left; text-decoration:none; }
#tfm_header_menu .menu_list a span { display:block; padding:4px 10px 0 10px; color:#004c7e; }
#tfm_header_menu .menu_list a:hover span { color:#FFF; border:1px solid #004c7e;}
#tfm_header_menu .menu_list a:hover { background:url(<%=path%>/images/menu_list.jpg) repeat-x; }
.navPoint {cursor:hand;}
.STYLE1 {color: #000000}
</style>

</head>
<body>
<table id="tfm_table" cellpadding="0" cellspacing="0"  width="100%"  height="100%" >
    <tr>
        <td class="top" valign="top"><iframe src="top.jsp" width="100%" height="81" frameborder="0" id="iframe_top" name="iframe_top" scrolling="no"></iframe>
        </td>
    </tr>
    <tr>
        <td  valign="top"  >
          <table  id="content" cellpadding="0" cellspacing="0" width="100%" height="100%" style="overflow:hidden;table-layout:fixed;">
          	<tr>
            	<td valign="top" width="175" nowrap="nowrap" id="tfm_left" style="overflow:hidden;">
            	    <iframe src="left2.jsp" width="200" height="100%" frameborder="0" id="iframe_left" name="iframe_left" scrolling="no"></iframe>
                </td>
                <td class="middle_bar"  style="width:8px;background-color:#2296d3;" valign="middle" align="center" onclick="switchSysBar()">
                	 <span class="navPoint" id=switchPoint title="打开/关闭左栏">
						<img src="images/guanli_25.png" name="img1" width="8" height="48" id="img1"/>
					</span>
              </td>
                <td class="right-content" valign="top" align="left">
                    <iframe  width="100%" height="100%" frameborder="0" id="iframe_content" name="iframe_content" src="install/action/InstallAction!list.action"></iframe>
                </td>
            </tr>
          </table>
        </td>
    </tr>
    <tr>
        <td  class="bottom"  style="height:20px;background-color:#2296d3;" >
        	<center style="color:white">copyright siterwell@ 2017</center>
        </td>
    </tr>
</table>
</body>
</html>