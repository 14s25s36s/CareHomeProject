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
    <title>员工信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<table class="layui-table" lay-data="{url:'care/careinfo',page:true,id:'carelist',toolbar:'#headerbtns'}"
       lay-filter="carelist">
    <thead>
    <tr>
        <th lay-data="{field:'uid',sort:true}">ID</th>
        <th lay-data="{field:'uname'}">姓名</th>
        <th lay-data="{field:'usex'}">性别</th>
        <th lay-data="{field:'uage'}">年龄</th>
        <th lay-data="{field:'uaddress'}">家庭住址</th>
        <th lay-data="{field:'ustate',templet:'#userstate'}">状态</th>
        <th lay-data="{field:'aid'}">账户id</th>
        <th lay-data="{field:'permissions',templet:'#accountpermission'}">账户权限</th>
        <th lay-data="{toolbar:'#btntool'}">操作</th>
    </tr>
    </thead>
</table>

<!-- START 添加员工 -->
<div id="addcareform" style="display:none;" class="layui-form" lay-filter="addcareform">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="uname" autocomplete="off" placeholder="请输入姓名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="usex" value="男" title="男" checked>
            <input type="radio" name="usex" value="女" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="text" name="uage" autocomplete="off" placeholder="请输入年龄" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-block">
            <input type="text" name="uaddress" autocomplete="off" placeholder="请输入家庭住址" class="layui-input">
        </div>
    </div>

</div>
<!-- END 添加员工 -->

<!-- START 编辑员工 -->
<div id="updatecareform" style="display:none;" class="layui-form" lay-filter="updatecareform">
    <input type="hidden" name="uid">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="uname" autocomplete="off" placeholder="请输入姓名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="usex" value="男" title="男" checked>
            <input type="radio" name="usex" value="女" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="text" name="uage" autocomplete="off" placeholder="请输入年龄" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-block">
            <input type="text" name="uaddress" autocomplete="off" placeholder="请输入家庭住址" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">目前状态</label>
        <div class="layui-input-block">
            <input type="radio" name="ustate" value="0" title="在院" checked>
            <input type="radio" name="ustate" value="1" title="出差">
        </div>
    </div>

</div>
<!-- END 编辑员工 -->

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

<script type="text/html" id="userstate">
    {{# if(d.ustate == '0'){ }}
    在院
    {{# } else { }}
    出差
    {{# } }}
</script>
<script type="text/html" id="accountpermission">
    {{# if(d.permissions == '0'){ }}
    超级管理员
    {{# } else if(d.permissions == '1'){ }}
    员工
    {{# } else if(d.permissions == '2'){ }}
    普通用户
    {{# } else { }}
    注销
    {{# } }}
</script>
<script type="text/javascript" src="static/lib/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/js/operatecare.js"></script>
</table>
</body>
</html>
