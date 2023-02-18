<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/16
  Time: 13:55
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
    <title>修改用户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
</head>
<body>
<form class="layui-form layui-form-pane" action="user/updateuserinfo">
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <input type="hidden" name="uid" value="${userInfo.getUid()}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="uname" lay-verify="required" value="${userInfo.getUname()}" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="usex" value="男" title="男" ${("男" eq userInfo.getUsex())?'checked':""}>
            <input type="radio" name="usex" value="女" title="女" ${("女" eq userInfo.getUsex())?'checked':""}>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-inline">
            <input type="text" name="uage" lay-verify="required" value="${userInfo.getUage()}" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-inline">
            <input type="text" name="uaddress" lay-verify="required" value="${userInfo.getUaddress()}"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">目前状态</label>
        <div class="layui-input-block">
            <input type="radio" name="ustate" value="0" title="现用" ${("0" eq userInfo.getUstate())?'checked':""}>
            <input type="radio" name="ustate" value="1" title="注销" ${("1" eq userInfo.getUstate())?'checked':""}>
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn" lay-submit="" lay-filter="demo2">跳转式提交</button>
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
