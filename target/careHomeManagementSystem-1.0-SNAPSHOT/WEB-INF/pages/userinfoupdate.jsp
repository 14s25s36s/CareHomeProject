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
        <div class="layui-inline">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-block">
                <input type="text" name="uage" id="date1" value="${userInfo.getUage()}" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-inline">
            <select id="province" lay-filter="province" lay-verify="required" name="province" _child="#city">
                <option value="${addressMap.get("provinceid")}">${addressMap.get("province")}</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="city" lay-filter="city" lay-verify="required" name="city" _child="#area">
                <option value="${addressMap.get("cityid")}">${addressMap.get("city")}</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="area" lay-filter="area" lay-verify="required" name="area" _child="">
                <option value="${addressMap.get("areaid")}">${addressMap.get("area")}</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn" lay-submit="" lay-filter="demo2">跳转式提交</button>
    </div>
</form>
<script type="text/javascript" src="static/lib/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/js/jquery.js"></script>
<script type="text/javascript" src="static/js/addressandage.js"></script>
<script type="text/javascript">
    layui.use('form', function () {
        const form = layui.form;
        form.render();
    });
</script>
</body>
</html>
