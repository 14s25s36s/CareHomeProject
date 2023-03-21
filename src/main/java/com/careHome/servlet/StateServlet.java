package com.careHome.servlet;

import com.careHome.service.StateService;
import com.careHome.service.impl.StateServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/state/*")
public class StateServlet extends SuperServlet {
    StateService stateService = new StateServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute(ACTION_NAME);
        if ("tostateinfo".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/stateinfo.jsp").forward(req, resp);
        } else if ("stateinfo".equals(action)) {
            stateService.getStateList(req, resp);
        } else if ("deletestate".equals(action)) {
            stateService.deleteState(req, resp);
        } else if ("addstate".equals(action)) {
            stateService.addState(req, resp);
        } else if ("updatestate".equals(action)) {
            stateService.updateState(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
