<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/17
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>$Title$</title>
  </head>
  <body>
  $END$
  </body>
</html>
