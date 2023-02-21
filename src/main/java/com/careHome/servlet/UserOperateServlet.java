package com.careHome.servlet;

import com.careHome.service.LiveHouseService;
import com.careHome.service.UserInfoService;
import com.careHome.service.impl.UserInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class UserOperateServlet extends SuperServlet {
    UserInfoService userInfoService = new UserInfoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute(ACTION_NAME);
        if ("userinfo".equals(action)) {
            userInfoService.userInfo(req, resp);
        } else if ("touserinfo".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/userinfo.jsp").forward(req, resp);
        } else if ("deleteuserinfo".equals(action)) {
            userInfoService.deleteuserinfo(req, resp);
        } else if ("toupdateuserinfo".equals(action)) {
            userInfoService.toUpdateUserInfo(req, resp);
            req.getRequestDispatcher("../WEB-INF/pages/userinfoupdate.jsp").forward(req, resp);
        } else if ("updateuserinfo".equals(action)) {
            userInfoService.updateUserInfo(req, resp);
            resp.sendRedirect(req.getContextPath() + "/successuser.jsp");
        } else if ("adduserinfo".equals(action)) {
            userInfoService.addUserInfo(req, resp);
        } else if ("updateuserstate".equals(action)) {
            userInfoService.updateUserState(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
