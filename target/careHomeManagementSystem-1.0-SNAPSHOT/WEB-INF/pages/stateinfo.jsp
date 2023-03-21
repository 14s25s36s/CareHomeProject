<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/3/21
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<table class="layui-table" lay-data="{url:'state/stateinfo',page:true,id:'statelist',toolbar:'#headerbtns'}"
       lay-filter="statelist">
    <thead>
    <tr>
        <th lay-data="{field:'id',sort:true}">ID</th>
        <th lay-data="{field:'lstate'}">状态号</th>
        <th lay-data="{field:'lstatename'}">状态名</th>
        <th lay-data="{toolbar:'#btntool'}">操作</th>
    </tr>
    </thead>
</table>

<!-- START 添加状态 -->
<div id="addstateform" style="display:none;" class="layui-form" lay-filter="addstateform">
    <input type="hidden" name="id">
    <div class="layui-form-item">
        <label class="layui-form-label">状态号</label>
        <div class="layui-input-block">
            <input type="text" name="lstate" autocomplete="off" placeholder="请输入姓名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态名</label>
        <div class="layui-input-block">
            <input type="text" name="lstatename" autocomplete="off" placeholder="请输入姓名" class="layui-input">
        </div>
    </div>
</div>
<!-- END 添加状态 -->
<!-- START 修改状态 -->
<div id="updatestate" style="display:none;" class="layui-form" lay-filter="updatestate">
    <input type="hidden" name="id">
    <div class="layui-form-item">
        <label class="layui-form-label">状态号</label>
        <div class="layui-input-block">
            <input type="text" name="lstate" autocomplete="off" placeholder="请输入姓名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态名</label>
        <div class="layui-input-block">
            <input type="text" name="lstatename" autocomplete="off" placeholder="请输入姓名" class="layui-input">
        </div>
    </div>
</div>
<!-- END 修改状态 -->

<%--START行内按钮--%>
<script type="text/html" id="btntool">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm layui-bg-red" lay-event="delete">删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
    </div>
</script>
<%--END行内按钮--%>
<%-- START 表格头部按钮 --%>
<script type="text/html" id="headerbtns">
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" id="checktext" name="checktext" class="layui-input">
            </div>
        </div>
        <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="check">查询</button>
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="add" style="margin: 0px 100px">新增用户信息
        </button>
    </div>
</script>
<%-- END 表格头部按钮 --%>

<%--<script type="text/html" id="state">--%>
<%--    {{# if(d.lstate===''){ }}--%>
<%--    未办理--%>
<%--    {{# } else if(d.lstate===0){ }}--%>
<%--    可以自理--%>
<%--    {{# } else { }}--%>
<%--    不能自理--%>
<%--    {{# } }}--%>
<%--</script>--%>
<script type="text/javascript" src="static/lib/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/js/stateoperate.js"></script>
<script type="text/javascript" src="static/js/addressandage.js"></script>
</table>
</body>
</html>
