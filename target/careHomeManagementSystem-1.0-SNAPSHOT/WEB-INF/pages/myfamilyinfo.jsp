<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/15
  Time: 13:34
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
    <meta charset="utf-8">
    <title>我的家属信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="static/css/selectcss.css">
</head>
<body>
<div class="layui-inline">
    <label class="labelstyle">家属姓名:</label>
    <div class="layui-input-inline">
        <select class="selectstyle" id="familyinfo" name="familyinfo">
            <option disabled selected>请选择</option>
        </select>
    </div>
</div>
<table class="layui-hide" id="test" lay-filter="test"></table>
<table class="layui-table" lay-data="{url:'myinfo/myfamilyinfo',page:true,id:'familylist'}"
       lay-filter="familylist">
    <thead>
    <tr>
        <th lay-data="{field:'lid',sort:true}">ID</th>
        <th lay-data="{field:'lname'}">姓名</th>
        <th lay-data="{field:'lage'}">年龄</th>
        <th lay-data="{field:'lsex'}">性别</th>
        <th lay-data="{field:'uname'}">家属姓名</th>
        <th lay-data="{field:'lstate',templet:'#state'}">状态</th>
        <th lay-data="{toolbar:'#btntool'}">操作</th>
    </tr>
    </thead>
</table>

<%--START行内按钮--%>
<script type="text/html" id="btntool">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm layui-bg-red" lay-event="delete">删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
    </div>
</script>
<%--END行内按钮--%>

<script type="text/html" id="state">
    {{# if(d.lstate===''){ }}
    未办理
    {{# } else if(d.lstate===0){ }}
    住院
    {{# } else { }}
    出院
    {{# } }}
</script>
<script type="text/javascript" src="static/lib/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/js/jquery.js"></script>
<script type="text/javascript" src="static/js/myfamily.js"></script>
</table>
</body>
</html>

