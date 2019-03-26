<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html class=" js no-touch no-android chrome no-firefox no-iemobile no-ie no-ie10 no-ie11 no-ios">
<head>
<meta charset="utf-8">
<title>登录页面</title>
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
        <div class="form-hint">
          <div id="hintInfo" class="hint-box">您输入的帐号或密码不正确</div>
          <div id="emptyInfo" class="hint-box">帐号或密码不能为空</div>
        </div>
        <form action="Index!index.action" method="post" >
          <p class="bor_bot"> <span class="login-ico ico1"></span>
            <input name="username" type="text" id="username" value="${username }" placeholder="账号" style="width: 300px;">
          </p>
          <p class="bor_bot mt20"> <span class="login-ico ico2"></span>
            <input name="password" type="password" id="password" value="${password }" placeholder="密码" style="width: 300px;">
          </p>
          <p class="rem">
            <input type="checkbox" id="rem_pwd" name="rem_pwd" value="0">
            <label for="rem_pwd">记住账号</label>
<!--             <a style="float:right;margin-right:10px;" href="resetPwd.jsp" target="_blank">忘记密码</a> -->
            <a style="float:right;margin-right:20px;" href="register.jsp" target="_self">注册</a>
          </p>
          <input class="sub mt30" type="submit" value="登录" id="login-submit">
          <input class="sub mt30" style="background:#9C3" type="reset" value="重置" id="login-submit">
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
