<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/17
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>权限不符，不可访问</title>
</head>
<body>
<script type="text/javascript">
    alert("权限不符，不可访问");
</script>
<script language=javascript>
    function go() {
        window.history.go(-1);
    }

    setTimeout("go()", 1000);
</script>
</body>
</html>
