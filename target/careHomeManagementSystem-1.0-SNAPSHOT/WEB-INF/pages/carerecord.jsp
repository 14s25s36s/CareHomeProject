<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/18
  Time: 9:18
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
    <title>体检记录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/lib/layui/css/layui.css"
          tppabs="static/lib/layui/css/layui.css" media="all">
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<table class="layui-table" lay-data="{url:'docare/careinfo',page:true,id:'carerecord',toolbar:'#headerbtns'}"
       lay-filter="carerecord">
    <thead>
    <tr>
        <th lay-data="{field:'careid',sort:true}">ID</th>
        <th lay-data="{field:'lname'}">老人姓名</th>
        <th lay-data="{field:'uname'}">护工姓名</th>
        <th lay-data="{field:'caredate'}">护理日期</th>
        <th lay-data="{field:'lasteditdate'}">最后修改日期</th>
        <th lay-data="{field:'careinfo'}">护理信息</th>
        <th lay-data="{toolbar:'#btntool'}">操作</th>
    </tr>
    </thead>
</table>

<!-- START 添加护理记录 -->
<div id="addcarerecord" style="display:none;" class="layui-form" lay-filter="addcarerecord">
    <input type="hidden" name="id">
    <div class="layui-form-item" pane="">
        <div class="layui-inline">
            <label class="layui-form-label">选择护理老人</label>
            <div class="layui-input-inline">
                <select id="lname" name="lid" lay-verify="required">
                    <option disabled selected>---请选择老人---</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">护理信息</label>
        <div class="layui-input-block">
            <input type="text" name="careinfo" autocomplete="off" placeholder="请输入护理信息" class="layui-input">
        </div>
    </div>
</div>
<!-- END 添加护理记录 -->
<!-- START 修改护理记录 -->
<div id="updatecarerecord" style="display:none;" class="layui-form" lay-filter="updatecarerecord">
    <input type="hidden" name="careid">
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">护理信息</label>
        <div class="layui-input-block">
            <textarea name="careinfo" autocomplete="off" placeholder="请输入护理信息" class="layui-textarea"></textarea>
        </div>
    </div>
</div>
<!-- END 修改护理记录 -->

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
                <input type="text" id="checktext" name="lname" class="layui-input" placeholder="输入住户id查询">
            </div>
        </div>
        <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="check">查询</button>
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="add" style="margin: 0px 100px">添加护理记录
        </button>
    </div>
</script>
<%-- END 表格头部按钮 --%>

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
<script type="text/javascript" src="static/js/carerecord.js"></script>
<script type="text/javascript">
    $.ajax({
        url: "docare/getlivebycareid",
        data: {},
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var $option = $("<option></option>").val(item.lid).text(item.lname);
                $("#lname").append($option);
            }
            form.render();
        }
    });
</script>
</table>
</body>
</html>
