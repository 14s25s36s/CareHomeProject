<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/15
  Time: 21:57
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
    <title>温馨提示</title>
    <meta http-equiv="refresh" content="0;url=user/touserinfo">
</head>
<body>
<%--<%--%>
<%--    String mess = (String) session.getAttribute("msg");--%>
<%--    if ("".equals(mess) && mess == null) {--%>
<%--    } else {%>--%>
<%--<script type="text/javascript">--%>
<%--    alert("<%=mess%>");--%>
<%--</script>--%>
<%--<% }%>--%>
<%--<jsp:forward page="live/livehouse"></jsp:forward>--%>
</body>
</html>
