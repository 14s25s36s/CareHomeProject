package com.careHome.servlet;

import com.careHome.pojo.Account;
import com.careHome.service.LoginService;
import com.careHome.service.impl.LoginServiceImpl;
import com.careHome.utils.Sys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/*")
public class LoginServlet extends SuperServlet {

    LoginService loginService = new LoginServiceImpl();

    /**
     * doGet方法获取请求并向Service层转发数据
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute(ACTION_NAME);
        if ("tologin".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/login.jsp").forward(req, resp);
        } else if ("dologin".equals(action)) {
            loginService.login(req, resp);
        } else if ("adminmain".equals(action)) {
            Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
            if (account.getPermissions() == 0) {
                req.getRequestDispatcher("../WEB-INF/pages/main.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/failed.jsp");
            }

        } else if ("caremain".equals(action)) {
            Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
            if (account.getPermissions() == 1) {
                req.getRequestDispatcher("../WEB-INF/pages/caremain.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/failed.jsp");
            }
        } else if ("usermain".equals(action)) {
            Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
            if (account.getPermissions() == 2) {
                req.getRequestDispatcher("../WEB-INF/pages/usermain.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/failed.jsp");
            }
        } else if ("judgeaccount".equals(action)) {
            loginService.checkExist(req, resp);
        } else if ("register".equals(action)) {
            loginService.register(req, resp);
        } else if ("loginexit".equals(action)) {
            req.getSession().removeAttribute(Sys.LOGIN_USER);
            resp.sendRedirect(req.getContextPath() + "/login/tologin");
        }
//        else if ("tomyinfo".equals(action)) {
//            loginService.toMyInfo(req, resp);
//            req.getRequestDispatcher("../WEB-INF/pages/myinfo.jsp").forward(req, resp);
//        } else if ("toupdatemyinfo".equals(action)) {
//            loginService.toMyInfo(req, resp);
//            req.getRequestDispatcher("../WEB-INF/pages/updatemyinfo.jsp").forward(req, resp);
//        } else if ("updatemyinfo".equals(action)) {
//            loginService.updateMyInfo(req, resp);
//            resp.sendRedirect(req.getContextPath() + "/mysuccess.jsp");
//        } else if ("toaddfamily".equals(action)) {
//            req.getRequestDispatcher("../WEB-INF/pages/addfamilyinfo.jsp").forward(req, resp);
//        } else if ("addfamilyinfo".equals(action)) {
//            loginService.addFamilyInfo(req, resp);
//            resp.sendRedirect(req.getContextPath() + "/myfamilysuccess.jsp");
//        } else if ("tomyfamilyinfo".equals(action)) {
//            req.getRequestDispatcher("../WEB-INF/pages/myfamilyinfo.jsp").forward(req, resp);
//        } else if ("myfamilyinfo".equals(action)) {
//            loginService.myFamilyInfo(req, resp);
//        } else if ("myfamilyname".equals(action)) {
//            loginService.myFamilyName(req, resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
