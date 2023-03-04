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
    <title>用户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<table class="layui-table" lay-data="{url:'user/userinfo',page:true,id:'userlist',toolbar:'#headerbtns'}"
       lay-filter="userlist">
    <thead>
    <tr>
        <th lay-data="{field:'uid',sort:true}">ID</th>
        <th lay-data="{field:'uname'}">姓名</th>
        <th lay-data="{field:'usex'}">性别</th>
        <th lay-data="{field:'uage',templet:function(d){
            if(d.uage===undefined){
                return null;
            }else{var agedatestr = JSON.stringify(d.uage);
                var nowdate = new Date();
                var nowyear = nowdate.getFullYear();
                var ageyear = agedatestr.slice(1,5);
                var age = Number(nowyear) - Number(ageyear);
                return age;
            }
            }}">年龄
        </th>
        <th lay-data="{field:'uaddress'}">家庭住址</th>
        <th lay-data="{field:'telephone'}">手机号码</th>
        <th lay-data="{field:'emergencycall'}">应急电话</th>
        <th lay-data="{field:'ustate',templet:'#userstate'}">状态</th>
        <th lay-data="{field:'aid'}">账户id</th>
        <th lay-data="{field:'permissions',templet:'#accountpermission'}">账户权限</th>
        <th lay-data="{toolbar:'#btntool'}">操作</th>
    </tr>
    </thead>
</table>

<!-- START 添加用户 -->
<div id="adduserform" style="display:none;" class="layui-form" lay-filter="adduserform">
    <input type="hidden" name="id">
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
        <label class="layui-form-label">手机号码</label>
        <div class="layui-input-block">
            <input type="text" id="telephone" name="telephone" autocomplete="off" placeholder="请输入手机号码"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">应急电话</label>
        <div class="layui-input-block">
            <input type="text" id="emergencycall" name="emergencycall" autocomplete="off" placeholder="请输入应急电话"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-block">
                <input type="text" name="uage" id="date1" autocomplete="off" class="layui-input" readonly>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-inline">
            <select id="province" lay-filter="province" lay-verify="required" name="province" _child="#city">
                <option selected disabled>请选择省</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="city" lay-filter="city" lay-verify="required" name="city" _child="#area">
                <option selected disabled>请选择市</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="area" lay-filter="area" lay-verify="required" name="area" _child="">
                <option selected disabled>请选择县/区</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">账号</label>
        <div class="layui-input-block">
            <input type="text" id="useraccount" name="useraccount" autocomplete="off" placeholder="请输入账号"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">账号权限</label>
        <div class="layui-input-block">
            <input type="radio" name="permissions" value="0" title="超级管理员">
            <input type="radio" name="permissions" value="1" title="管理员">
            <input type="radio" name="permissions" value="2" title="普通用户" checked>
        </div>
    </div>
</div>
<!-- END 添加用户 -->

<%--START行内按钮--%>

<script type="text/html" id="btntool">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm layui-bg-red" lay-event="delete">删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
        {{# if(d.ustate=='0'){ }}
        <button class="layui-btn layui-btn-sm layui-bg-orange" id="ban" lay-event="ban">封号</button>
        {{# }else{ }}
        <button class="layui-btn layui-btn-sm layui-bg-orange" id="unban" lay-event="unban">解封</button>
        {{# } }}
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

<script type="text/html" id="userage">

</script>
<script type="text/html" id="userstate">
    {{# if(d.ustate == '0'){ }}
    现用
    {{# } else { }}
    封号
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
<%--<script type="text/javascript" src="static/js/jquery.js"></script>--%>
<script type="text/javascript" src="static/js/addressandage.js"></script>
<script type="text/javascript" src="static/js/operateuser.js"></script>
</table>
</body>
</html>
