package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.LiveHouseDao;
import com.careHome.dao.impl.LiveHouseDaoImpl;
import com.careHome.pojo.Care;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.Lstate;
import com.careHome.service.LiveHouseService;
import com.careHome.utils.LayListData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LiveHouseServiceImpl implements LiveHouseService {
    LiveHouseDao liveHouseDao = new LiveHouseDaoImpl();

    /**
     * 查看所有住户信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void hasLive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String str_page = req.getParameter("page");
        String str_limit = req.getParameter("limit");
        String checktext = req.getParameter("checktext");
        int page = Integer.parseInt(str_page);
        int limit = Integer.parseInt(str_limit);
        int start = (page - 1) * limit;
        int count = 0;
        List<LiveInfo> liveInfoList = null;
        if (checktext == null) {
            liveInfoList = liveHouseDao.selectAllLiveInfo(start, limit);
            count = liveHouseDao.selectCountLiveHouse();
        } else {
            liveInfoList = liveHouseDao.selectOneLiveHouseInfo(checktext, start, limit);
            count = liveHouseDao.selectCountLiveHouse(checktext);
        }
        LayListData layListData = new LayListData(count, liveInfoList);
        String json = JSON.toJSONString(layListData);
        resp.getWriter().write(json);
    }

    /**
     * 删除住户信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void deleteLiveInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lid = req.getParameter("lid");
        int result = liveHouseDao.deletedOneLiveInfo(lid);
        String msg = null;
        if (result > 0) {
            msg = "删除成功";
            resp.getWriter().write(msg);
        } else {
            msg = "删除失败";
            resp.getWriter().write(msg);
        }
    }

    /**
     * 前往修改住户信息页面
     *
     * @param req
     * @param resp
     */
    @Override
    public void toUpdateLiveInfo(HttpServletRequest req, HttpServletResponse resp) {
        String lid = req.getParameter("lid");
        LiveInfo liveInfo = liveHouseDao.selectOneLiveInfo(lid).get(0);
        req.setAttribute("liveInfo", liveInfo);
    }

    @Override
    public void getStateList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Lstate> lstateList = liveHouseDao.getStateList();
        String json = JSON.toJSONString(lstateList);
        resp.getWriter().write(json);
    }

    /**
     * 修改住户信息
     *
     * @param req
     * @param resp
     */
    @Override
    public void updateLiveInfo(HttpServletRequest req, HttpServletResponse resp) {
        String lname = req.getParameter("lname");
        String lage = req.getParameter("lage");
        String lsex = req.getParameter("lsex");
        String careuid = req.getParameter("careuid");
        String lstate = req.getParameter("lstate");
        String lid = req.getParameter("lid");
        String msg = null;
        int result = liveHouseDao.updateLiveInfo(lname, lage, lsex, careuid, lstate, lid);
        if (result > 0) {
            msg = "修改成功";
            req.getSession().setAttribute("msg", msg);
        } else {
            msg = "修改失败";
            req.getSession().setAttribute("msg", msg);
        }

    }

    /**
     * 添加住户信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void addLiveInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lname = req.getParameter("lname");
        String lage = req.getParameter("lage");
        String lsex = req.getParameter("lsex");
        String uid = req.getParameter("uid");
        String careuid = req.getParameter("careuid");
        String lstate = req.getParameter("lstate");
        int result = liveHouseDao.addUserInfo(lname, lage, lsex, uid, careuid, lstate);
        resp.getWriter().write(result);
    }

    @Override
    public void getCareList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Care> careList = liveHouseDao.getCareList();
        String json = JSON.toJSONString(careList);
        resp.getWriter().write(json);
    }

}
