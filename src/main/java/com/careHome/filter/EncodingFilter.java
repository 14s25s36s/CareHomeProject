package com.careHome.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter({"/*", "/**"})
public class EncodingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //filter工作时，执行doFilter方法，这个方法分为两部分
        //请求到达过滤器后执行代码直到chain.doFilter()方法，进入到下一个过滤器或者资源（Servlet）中执行
        //执行完Servlet后，回应对象，再从之前的chain.doFilter再继续运行
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
