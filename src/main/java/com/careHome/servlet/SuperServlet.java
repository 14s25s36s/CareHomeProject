package com.careHome.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SuperServlet extends HttpServlet {
    public static final String ACTION_NAME="$action$";
    // 改造servlet路径的处理
    // 原来save delete edit select每一个都要创建一个新的servlet
    // 最好将staff的所有请求都交给一个servlet来处理

    // 路径可以设置为 staff/save staff/delete goods/edit
    // 针对以上的路径进行解析
    // 获取路径，得到第二级路径，进行判断执行不同的方法

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取浏览器访问路径
        String uri = req.getRequestURI();
        //获取二级路径
        int index = uri.lastIndexOf("/");
        String action = uri.substring(index + 1);
        // 将action放到request中
        // request是一个域对象
        req.setAttribute(ACTION_NAME,action);
        // get,post分路
        super.service(req, resp);
    }
    // 过滤器 监听器 初识jsp 重定向 转发

}
