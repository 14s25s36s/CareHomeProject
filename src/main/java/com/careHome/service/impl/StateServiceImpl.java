package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.StateDao;
import com.careHome.dao.impl.StateDaoImpl;
import com.careHome.pojo.LStateName;
import com.careHome.pojo.LiveInfo;
import com.careHome.service.StateService;
import com.careHome.utils.LayListData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StateServiceImpl implements StateService {
    StateDao stateDao = new StateDaoImpl();

    @Override
    public void getStateList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String str_page = req.getParameter("page");
        String str_limit = req.getParameter("limit");
        String checktext = req.getParameter("checktext");
        int page = Integer.parseInt(str_page);
        int limit = Integer.parseInt(str_limit);
        int start = (page - 1) * limit;
        int count = 0;
        List<LStateName> stateList = null;
        if (checktext == null) {
            stateList = stateDao.selectAllStateInfo(start, limit);
            count = stateDao.selectCountStateHouse();
        } else {
            stateList = stateDao.selectOneStateInfo(checktext, start, limit);
            count = stateDao.selectCountState(checktext);
        }
        LayListData layListData = new LayListData(count, stateList);
        String json = JSON.toJSONString(layListData);
        resp.getWriter().write(json);
    }

    @Override
    public void addState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lstate = req.getParameter("lstate");
        String lstatename = req.getParameter("lstatename");
        int result = stateDao.addState(lstate, lstatename);
        if (result > 0) {
            resp.getWriter().write("添加成功");
        } else {
            resp.getWriter().write("添加失败");
        }
    }

    @Override
    public void updateState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String lstate = req.getParameter("lstate");
        String lstatename = req.getParameter("lstatename");
        int result = stateDao.updateState(id, lstate, lstatename);
        if (result > 0) {
            resp.getWriter().write("修改成功");
        } else {
            resp.getWriter().write("修改失败");
        }
    }

    @Override
    public void deleteState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String lstate = req.getParameter("lstate");
        int result = stateDao.LiveExist(lstate);
        if (result > 0) {
            resp.getWriter().write("该分类下有住户，不可删除");
        } else {
            int delresult = stateDao.deleteState(id);
            if (delresult > 0) {
                resp.getWriter().write("删除成功");
            } else {
                resp.getWriter().write("删除失败");
            }
        }
    }
}
