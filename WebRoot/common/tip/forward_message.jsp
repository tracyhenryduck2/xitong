<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
   <script>
	   parent.result("<%=request.getParameter("messageType")%>", "${showMessage}");
   </script>
 </body>
</html>