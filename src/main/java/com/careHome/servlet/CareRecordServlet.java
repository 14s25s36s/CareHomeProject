package com.careHome.servlet;

import com.careHome.service.CareRecordService;
import com.careHome.service.impl.CareRecordServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/docare/*")
public class CareRecordServlet extends SuperServlet {

    CareRecordService careRecordService = new CareRecordServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute(ACTION_NAME);
        if ("tocarerecord".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/carerecord.jsp").forward(req, resp);
        } else if ("careinfo".equals(action)) {
            careRecordService.careInfo(req, resp);
        } else if ("deletecarerecord".equals(action)) {
            careRecordService.deleteCareRecord(req, resp);
        } else if ("toupdatecarerecord".equals(action)) {
            careRecordService.toUpdateCareRecord(req, resp);
            req.getRequestDispatcher("../WEB-INF/pages/carerecordupdate.jsp").forward(req, resp);
        } else if ("updatecarerecord".equals(action)) {
            careRecordService.updateCareRecord(req, resp);
            resp.sendRedirect(req.getContextPath() + "/successcare.jsp");
        } else if ("addcarerecord".equals(action)) {
            careRecordService.addCareRecord(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
