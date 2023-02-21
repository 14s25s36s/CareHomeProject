<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/17
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>登陆</title>
    <link rel="stylesheet" href="static/css/normalize.min.css">
    <link rel="stylesheet" href="static/css/style.css">
    <link rel="stylesheet" href="static/css/code.css">
    <link rel="shortcut icon" href="#">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
</head>
<body>
<div class="container">
    <div class="left">
        <div class="header">
            <h2 class="animation a1" style="font-weight: bold;font-size: 25px">欢迎光临养老院</h2>
            <h4 class="animation a2">使用用户名和密码登录</h4>
        </div>
        <div class="form">
            <input type="text" id="useraccount" class="form-field animation a3" name="useraccount"
                   placeholder="用户名">
            <input type="password" id="password" class="form-field animation a4" name="password" placeholder="密码">
            <label class="animation a4" style="color:grey;">大写开头 数字字母符号混合 7-13位</label>
            <p class="animation a5"><a href="#">忘记密码</a></p>
            <button class="animation a6" id="login">登录</button>
            <button class="animation a6" id="register" lay-event="add">注册</button>
        </div>
    </div>
    <div class="right"></div>
</div>
<div id="logincodeform" style="display:none;" class="layui-form" lay-filter="logincodeform">
    <br>
    <div style="text-align: center;">
        <span style="font-weight: bold;font-size: 35px;">请输入验证码</span>
    </div>
    <div style="text-align: center;">
        <%-- 验证码输入框 --%>
        <div id="inputcode">
            <input class="verifyInput form-field" id="verifycode" name="verifyInput" placeholder="请输入验证码">
        </div>
        <br>
        <%-- 验证码图片 --%>
        <img class="verifyCode" onclick="changeCode();" src="login/getVerifyCode" style="border: 2px solid #ddd;
    border-radius: 4px;">
        <div>
            <label style="margin: 5px auto;color: grey">如看不清，请点击验证码刷新</label>
        </div>
    </div>
</div>
<!-- START 注册用户 -->
<div id="registerform" style="display:none;" class="layui-form" lay-filter="registerform">
    <input type="hidden" name="id">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input id="registeruseraccount" type="text" name="useraccount" autocomplete="off" placeholder="请输入用户名"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input id="registerpass" type="password" name="password" autocomplete="off" placeholder="请输入密码"
                   class="layui-input">
        </div>
        <label class="layui-input-block" style="color:grey;">大写开头 数字字母符号混合 7-13位</label>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input id="ensureregisterpass" type="password" name="ensurepassword" autocomplete="off"
                   placeholder="请重新输入密码" class="layui-input">
        </div>
    </div>
</div>
<!-- END 注册用户 -->
<script type="text/javascript" src="static/js/jquery.js"></script>
<script type="text/javascript" src="static/lib/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/js/login.js"></script>
</body>
</html>

