package com.careHome.filter;

import com.careHome.utils.Sys;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/live/*", "/user/*", "/care/*", "/docare/*", "/myinfo/*"})
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        System.out.println("我进来了");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html/charset=UTF-8");
        HttpSession session = req.getSession();
        //如果session中有Sys.LOGIN_USER对应的数据，该用户就已经登陆成功了
        Object loginUser = session.getAttribute(Sys.LOGIN_USER);
        String msg = null;
        if (loginUser != null) {
            chain.doFilter(req, resp);
            System.out.println("我出去了");
        } else {
            msg = "您还未登陆";
            req.getSession().setAttribute("msg", msg);
            resp.sendRedirect(req.getContextPath() + "/tologin.jsp");
            System.out.println("我出去了null");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
