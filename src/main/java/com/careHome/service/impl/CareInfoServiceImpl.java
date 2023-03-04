package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.CareInfoDao;
import com.careHome.dao.impl.CareInfoDaoImpl;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
import com.careHome.service.CareInfoService;
import com.careHome.utils.LayListData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CareInfoServiceImpl implements CareInfoService {

    CareInfoDao careInfoDao = new CareInfoDaoImpl();

    /**
     * 获取所有的护工个人信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void allCareInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String str_page = req.getParameter("page");
        String str_limit = req.getParameter("limit");
        String checktext = req.getParameter("checktext");
        int page = Integer.parseInt(str_page);
        int limit = Integer.parseInt(str_limit);
        int start = (page - 1) * limit;
        int count = 0;
        List<UserInfo> careInfoList = null;
        if (checktext == null) {
            careInfoList = careInfoDao.selectAllCareInfo(start, limit);
            count = careInfoDao.selectCountCareInfo();
        } else {
            careInfoList = careInfoDao.selectOneCareInfo(checktext, start, limit);
            count = careInfoDao.selectCountCareInfo(checktext);
        }
        LayListData layListData = new LayListData(count, careInfoList);
        String json = JSON.toJSONString(layListData);
        resp.getWriter().write(json);
    }

    /**
     * 删除护工信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void deleteCareInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uid = req.getParameter("uid");
        int result = careInfoDao.deletedOneCareInfo(uid);
        resp.getWriter().write(result);
    }

    @Override
    public void getLiveByCare(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String careuid = req.getParameter("uid");
        System.out.println(careuid);
        List<LiveInfo> liveInfoList = careInfoDao.getLiveInfoByCare(careuid);
        System.out.println(liveInfoList);
        String json = JSON.toJSONString(liveInfoList);
        resp.getWriter().write(json);
    }
}
