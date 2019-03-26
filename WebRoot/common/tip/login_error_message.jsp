<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
%>
<html>
  <head>
    <title></title>
  </head>
  <body>
        <div align=center>
       <table border="0" cellpadding="0" cellspacing="0"  >
           
      <tr><td height="80" align="center"><img src="<%=path%>/images/error.gif"></td>
            </tr>
            
            <tr>
              <td height="50" align="center"><font size="2" color="red">登陆超时，请重新登录</font>！</td>
            </tr>
            
      <tr>
              <td height="30" align="center" valign="middle" >
              <input type='button' value='重新登录' class='Button' onclick='relogin();'/>
                <script>
				function relogin(){
					  var pp=getparent();
					  pp.testClose=0;
					  pp.location.href="<%=path%>/Index!index.action";
				}
				function getparent(){
					var a=window.parent;while(a.parent&&a.parent!=a){try{if(a.parent.document.domain!=document.domain){break}}catch(b){break}a=a.parent}return a}
                </script>
      </td>
            </tr>
          
        </table>
     </div>
 </body>
</html>