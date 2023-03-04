package com.careHome.servlet;

import com.careHome.service.CareInfoService;
import com.careHome.service.impl.CareInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/care/*")
public class CareStaffServlet extends SuperServlet {

    CareInfoService careInfoService = new CareInfoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute(ACTION_NAME);
        if ("tocareinfo".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/careinfo.jsp").forward(req, resp);
        } else if ("careinfo".equals(action)) {
            careInfoService.allCareInfo(req, resp);
        } else if ("deletecareinfo".equals(action)) {
            careInfoService.deleteCareInfo(req, resp);
        } else if ("getlivebycare".equals(action)) {
            careInfoService.getLiveByCare(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
