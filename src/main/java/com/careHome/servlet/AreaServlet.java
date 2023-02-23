package com.careHome.servlet;


import com.careHome.service.AreaService;
import com.careHome.service.impl.AreaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/address/*")
public class AreaServlet extends SuperServlet {
    AreaService areaService = new AreaServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute(ACTION_NAME);
        if ("province".equals(action)) {
            areaService.selectProvinces(req, resp);
        } else if ("city".equals(action)) {
            areaService.selectCitiesByProvinceid(req, resp);
        } else if ("area".equals(action)) {
            areaService.selectAreasByCityid(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
