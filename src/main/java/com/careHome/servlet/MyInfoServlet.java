package com.careHome.servlet;

import com.careHome.service.MyInfoService;
import com.careHome.service.impl.MyInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myinfo/*")
public class MyInfoServlet extends SuperServlet {
    MyInfoService myInfoService = new MyInfoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute(ACTION_NAME);
        if ("tomyinfo".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/myinfo.jsp").forward(req, resp);
        } else if ("toupdatemyinfo".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/updatemyinfo.jsp").forward(req, resp);
        } else if ("updatemyinfo".equals(action)) {
            myInfoService.updateMyInfo(req, resp);
            resp.sendRedirect(req.getContextPath() + "/mysuccess.jsp");
        } else if ("toaddfamily".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/addfamilyinfo.jsp").forward(req, resp);
        } else if ("addfamilyinfo".equals(action)) {
            myInfoService.addFamilyInfo(req, resp);
            resp.sendRedirect(req.getContextPath() + "/myfamilysuccess.jsp");
        } else if ("tomyfamilyinfo".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/myfamilyinfo.jsp").forward(req, resp);
        } else if ("myfamilyinfo".equals(action)) {
            myInfoService.myFamilyInfo(req, resp);
        } else if ("myfamilyname".equals(action)) {
            myInfoService.myFamilyName(req, resp);
        } else if ("updatepassword".equals(action)) {
            myInfoService.updatePassword(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
