package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.CareInfoDao;
import com.careHome.dao.CareRecordDao;
import com.careHome.dao.LoginDao;
import com.careHome.dao.UserInfoDao;
import com.careHome.dao.impl.CareInfoDaoImpl;
import com.careHome.dao.impl.CareRecordDaoImpl;
import com.careHome.dao.impl.LoginDaoImpl;
import com.careHome.pojo.Account;
import com.careHome.pojo.CareRecode;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
import com.careHome.service.CareRecordService;
import com.careHome.utils.LayListData;
import com.careHome.utils.Sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CareRecordServiceImpl implements CareRecordService {
    CareRecordDao careRecordDao = new CareRecordDaoImpl();
    LoginDao loginDao = new LoginDaoImpl();
    CareInfoDao careInfoDao = new CareInfoDaoImpl();

    /**
     * 查询所有的护理记录
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void careInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserInfo userInfo = (UserInfo) req.getSession().getAttribute(Sys.USER_INFO);
        Integer uid = userInfo.getUid();
        String str_page = req.getParameter("page");
        String str_limit = req.getParameter("limit");
        String lname = req.getParameter("lname");
        List<LiveInfo> liveInfoList = null;
        LiveInfo liveInfo = null;
        int page = Integer.parseInt(str_page);
        int limit = Integer.parseInt(str_limit);
        int start = (page - 1) * limit;
        int count = 0;
        Integer lid = -1;
        List<CareRecode> careRecodeList = null;
        if (lname == null) {
            careRecodeList = careRecordDao.selectAllCareRecord(uid, start, limit);
            count = careRecordDao.selectCountCareRecord(uid);
        } else {
            careRecodeList = careRecordDao.selectOneCareRecord(lname, uid, start, limit);
            count = careRecordDao.selectCountCareRecord(lid, uid);
        }
        LayListData layListData = new LayListData(count, careRecodeList);
        String json = JSON.toJSONString(layListData);
        resp.getWriter().write(json);
    }

    /**
     * 删除护理记录
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void deleteCareRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String careid = req.getParameter("careid");
        int result = careRecordDao.deletedOneCareRecord(careid);
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
     * 修改护理记录
     *
     * @param req
     * @param resp
     */
    @Override
    public void updateCareRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String careid = req.getParameter("careid");
        String careinfo = req.getParameter("careinfo");
        int result = careRecordDao.updateCareRecord(careid, careinfo);
        String msg = null;
        if (result > 0) {
            msg = "修改成功";
            req.getSession().setAttribute("msg", msg);
        } else {
            msg = "修改失败";
            req.getSession().setAttribute("msg", msg);
        }
        resp.getWriter().write(msg);
    }

    /**
     * 添加护理记录
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void addCareRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserInfo userInfo = (UserInfo) req.getSession().getAttribute(Sys.USER_INFO);
        Integer uid = userInfo.getUid();
        String lid = req.getParameter("lid");
        String careinfo = req.getParameter("careinfo");
        int result = careRecordDao.addCareRecord(lid, uid, careinfo);
        resp.getWriter().write(result);
    }

    @Override
    public void getLiveByCareid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserInfo userInfo = (UserInfo) req.getSession().getAttribute(Sys.USER_INFO);
        String uid = String.valueOf(userInfo.getUid());
        List<LiveInfo> liveInfoList = careInfoDao.getLiveInfoByCare(uid);
        String json = JSON.toJSONString(liveInfoList);
        resp.getWriter().write(json);
    }
}
