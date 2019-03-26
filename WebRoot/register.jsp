<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html class=" js no-touch no-android chrome no-firefox no-iemobile no-ie no-ie10 no-ie11 no-ios">
<head>
<meta charset="utf-8">
<title>注册页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link href="css/css.css" rel="stylesheet">
<script language="javascript" type="text/javascript">
var msg='${showMessage}';
if(msg!=''){
alert(msg);
}
</script>
</head>

<body style="overflow:hidden">
<div class="bodyer">
  <div class="bodyer-p">
    <div class="banner_box"> 
      <div class="login_box">
        <form action="Register!register.action" method="post" >
          <p class="bor_bot"> <span class="login-ico ico1"></span>
            <input name="username" type="text" id="username" value="${username }" placeholder="账号" style="width: 300px;">
          </p>
          <p class="bor_bot mt20"> <span class="login-ico ico2"></span>
            <input name="password" type="password" id="password" value="${password }" placeholder="密码" style="width: 300px;">
          </p>
           <p class="bor_bot mt20"> <span class="login-ico ico2"></span>
            <input name="password_confirm" type="password" id="password_confirm" value="${password_confirm }" placeholder="确认密码" style="width: 300px;">
          </p>
           <p class="bor_bot mt20"> <span class="login-ico ico2"></span>
            <input name="phone" type="number" id="phone" value="${phone }" placeholder="手机号" style="width: 300px;">
          </p>
          <p class="bor_bot mt20"> <span class="login-ico ico2"></span>
            <input name="type" type="type" id="phone" value="${type }" placeholder="请输入数字:3为安装人员，2为街区人员" style="width: 300px;" />
          </p>
          <input class="sub mt30" type="submit" value="点击注册" id="login-submit">
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
