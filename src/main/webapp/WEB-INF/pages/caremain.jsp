<%--
  Created by IntelliJ IDEA.
  User: 14434
  Date: 2023/2/14
  Time: 20:57
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
    <title>主页</title>
    <link rel="stylesheet" href="static/lib/layui/css/layui.css">
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">layout demo</div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <%--        <ul class="layui-nav layui-layout-left">--%>
        <%--            <!-- 移动端显示 -->--%>
        <%--            <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">--%>
        <%--                <i class="layui-icon layui-icon-spread-left"></i>--%>
        <%--            </li>--%>

        <%--            <li class="layui-nav-item layui-hide-xs"><a href="">nav 1</a></li>--%>
        <%--            <li class="layui-nav-item layui-hide-xs"><a href="">nav 2</a></li>--%>
        <%--            <li class="layui-nav-item layui-hide-xs"><a href="">nav 3</a></li>--%>
        <%--            <li class="layui-nav-item">--%>
        <%--                <a href="javascript:;">nav groups</a>--%>
        <%--                <dl class="layui-nav-child">--%>
        <%--                    <dd><a href="">menu 11</a></dd>--%>
        <%--                    <dd><a href="">menu 22</a></dd>--%>
        <%--                    <dd><a href="">menu 33</a></dd>--%>
        <%--                </dl>--%>
        <%--            </li>--%>
        <%--        </ul>--%>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide layui-show-md-inline-block">
                <a href="javascript:;">
                    <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
                         class="layui-nav-img">
                    tester
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">Your Profile</a></dd>
                    <dd><a href="">Settings</a></dd>
                    <dd><a href="">Sign out</a></dd>
                </dl>
            </li>

        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">人员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" _url="user/touserinfo">用户管理</a></dd>
                        <dd><a href="javascript:;" _url="live/livehouse">入住人员清单</a></dd>
                        <dd><a href="javascript:;" _url="care/tocareinfo">工作人员信息</a></dd>
                        <dd><a href="javascript:;" _url="live/toaddlive">入住人员信息登记</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">menu group 2</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">list 1</a></dd>
                        <dd><a href="javascript:;">list 2</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">click menu item</a></li>
                <li class="layui-nav-item"><a href="">the links</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe src="" id="main" style="width: 100%;height: 98%;border: none"></iframe>
        <%--        <!-- 内容主体区域 -->--%>
        <%--        <div style="padding: 15px;">内容主体区域。记得修改 layui.css 和 js 的路径</div>--%>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        底部固定区域
    </div>
</div>
<script type="text/javascript" src="static/lib/layui/layui.all.js"></script>
<script>
    //JS
    layui.use(['element', 'layer', 'util'], function () {
        var element = layui.element
            , layer = layui.layer
            , util = layui.util
            , $ = layui.$;

        //头部事件
        util.event('lay-header-event', {
            //左侧菜单事件
            menuLeft: function (othis) {
                layer.msg('展开左侧菜单的操作', {icon: 0});
            }
            , menuRight: function () {
                layer.open({
                    type: 1
                    , content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
                    , area: ['260px', '100%']
                    , offset: 'rt' //右上角
                    , anim: 5
                    , shadeClose: true
                });
            }
        });
        $("[_url]").click(function () {
            var url = $(this).attr("_url");
            $("#main").attr("src", url);
        });
    });

</script>

</body>
</html>

