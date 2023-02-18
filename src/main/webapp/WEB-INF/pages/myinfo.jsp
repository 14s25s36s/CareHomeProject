<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/16
  Time: 19:23
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
    <title>我的信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
</head>
<body>
<form class="layui-form layui-form-pane" action="myinfo/toupdatemyinfo">
    <div class="layui-form-item">
        <div class="layui-form-item">
            <label class="layui-form-label">UID：</label>
            <div class="layui-input-inline">
                <input type="text" name="uid" lay-verify="required"
                       value="${userinfo.getUid()}" autocomplete="off"
                       class="layui-input" readonly>
            </div>
        </div>
        <label class="layui-form-label">姓名：</label>
        <div class="layui-input-inline">
            <input type="text" name="uname" lay-verify="required"
                   value="${userinfo.getUname()}" autocomplete="off"
                   class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别：</label>
        <div class="layui-input-inline">
            <input type="text" name="usex" lay-verify="required"
                   value="${userinfo.getUsex()}" autocomplete="off"
                   class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄：</label>
        <div class="layui-input-inline">
            <input type="text" name="uage" lay-verify="required"
                   value="${userinfo.getUage()}" autocomplete="off"
                   class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">家庭住址：</label>
        <div class="layui-input-inline">
            <input type="text" name="uaddress" lay-verify="required"
                   value="${userinfo.getUaddress()}"
                   autocomplete="off"
                   class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">账户id：</label>
        <div class="layui-input-inline">
            <input type="text" name="aid" lay-verify="required"
                   value="${userinfo.getAid()}" autocomplete="off"
                   class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn" lay-submit="" lay-filter="demo2">修改用户信息</button>
        <button class="layui-btn" type="button" id="updatepassword">修改密码</button>
        <button class="layui-btn" type="button" id="exit">退出登陆</button>
    </div>
</form>
<!-- START 修改密码 -->
<div id="editpassword" style="display:none;" class="layui-form" lay-filter="editpassword">
    <input type="hidden" name="id">
    <div class="layui-form-item">
        <label class="layui-form-label">原密码</label>
        <div class="layui-input-block">
            <input id="oldpassword" type="password" name="oldpassword" autocomplete="off" placeholder="请输入密码"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input id="newpassword" type="password" name="newpassword" autocomplete="off" placeholder="请输入密码"
                   class="layui-input">
        </div>
        <label class="layui-input-block" style="color:grey;">大写开头 数字字母符号混合 7-13位</label>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认新密码</label>
        <div class="layui-input-block">
            <input id="ensurenewpassword" type="password" name="ensurenewpassword" autocomplete="off"
                   placeholder="请重新输入密码"
                   class="layui-input">
        </div>
    </div>
</div>
<!-- END 修改密码 -->
<script type="text/javascript" src="static/js/jquery.js"></script>
<script type="text/javascript" src="static/lib/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/js/myinfo.js"></script>
</body>
</html>
