<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/15
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>修改住户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
</head>
<body>

<form class="layui-form layui-form-pane" action="docare/updatecarerecord">
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <input type="hidden" name="careid" value="${carerecord.getCareid()}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">老人姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="lname" lay-verify="required" value="${carerecord.getLname()}" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">护工姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="uname" lay-verify="required" value="${carerecord.getUname()}" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">护理记录</label>
        <div class="layui-input-inline">
            <input type="text" name="careinfo" lay-verify="required" value="${carerecord.getCareinfo()}"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn" lay-submit="" lay-filter="demo2">修改</button>
    </div>
</form>
<script type="text/javascript" src="static/lib/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript">
    layui.use('form', function () {
        const form = layui.form;
        form.render();
    });
</script>
</body>
</html>
