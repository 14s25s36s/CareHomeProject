package com.careHome.servlet;

import com.careHome.service.LiveHouseService;
import com.careHome.service.impl.LiveHouseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/live/*")
public class LiveHouseServlet extends SuperServlet {

    LiveHouseService liveHouseService = new LiveHouseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute(ACTION_NAME);
        if ("haslive".equals(action)) {
            liveHouseService.hasLive(req, resp);
        } else if ("livehouse".equals(action)) {
            req.getRequestDispatcher("../WEB-INF/pages/liveinfo.jsp").forward(req, resp);
        } else if ("deleteliveinfo".equals(action)) {
            liveHouseService.deleteLiveInfo(req, resp);
        } else if ("toupdateliveinfo".equals(action)) {
            liveHouseService.toUpdateLiveInfo(req, resp);
            req.getRequestDispatcher("../WEB-INF/pages/liveinfoupdate.jsp").forward(req, resp);
        } else if ("updateliveinfo".equals(action)) {
            liveHouseService.updateLiveInfo(req, resp);
            resp.sendRedirect(req.getContextPath() + "/success.jsp");
        } else if ("addliveinfo".equals(action)) {
            liveHouseService.addLiveInfo(req, resp);
        } else if ("carelist".equals(action)) {
            liveHouseService.getCareList(req, resp);
        } else if ("statelist".equals(action)) {
            liveHouseService.getStateList(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
