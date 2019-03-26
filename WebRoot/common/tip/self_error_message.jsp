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
              <td height="50" align="center"><font size="2" color="red">${showMessage}</font>！</td>
            </tr>
            
        </table>
     </div>
 </body>
</html>