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
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>添加家属信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
</head>
<body>
<form class="layui-form layui-form-pane" action="myinfo/addfamilyinfo">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="lname" autocomplete="off" placeholder="请输入姓名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-block">
                <input type="text" name="lage" id="date1" placeholder="请选择出生日期" autocomplete="off"
                       class="layui-input" readonly>
            </div>
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="lsex" value="男" title="男" checked>
            <input type="radio" name="lsex" value="女" title="女">
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <div class="layui-inline">
            <label class="layui-form-label">选择护工</label>
            <div class="layui-input-inline">
                <select id="careuid" name="careuid" lay-verify="required">
                    <option disabled selected>---请选择护工---</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <div class="layui-inline">
            <label class="layui-form-label">自理能力</label>
            <div class="layui-input-inline">
                <select id="lstate" name="lstate" lay-verify="required">
                    <option disabled selected>---请选择自理能力---</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn" lay-submit="" lay-filter="demo2">添加家属信息</button>
    </div>
</form>
<script type="text/javascript" src="static/lib/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/js/addressandage.js"></script>
<script type="text/javascript">
    layui.use('form', function () {
        const form = layui.form;
        form.render();
    });
    $.ajax({
        url: "live/carelist",
        data: {},
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var $option = $("<option></option>").val(item.careuid).text(item.careuname);
                $("#careuid").append($option);
            }
            form.render();
        }
    });
    $.ajax({
        url: "live/statelist",
        data: {},
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var $option = $("<option></option>").val(item.lstate).text(item.lstatename);
                $("#lstate").append($option);
            }
            form.render();
        }
    });
</script>
</body>
</body>
</html>
