package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.CareRecordDao;
import com.careHome.dao.LoginDao;
import com.careHome.dao.impl.CareRecordDaoImpl;
import com.careHome.dao.impl.LoginDaoImpl;
import com.careHome.pojo.Account;
import com.careHome.pojo.CareRecode;
import com.careHome.pojo.LiveInfo;
import com.careHome.service.CareRecordService;
import com.careHome.utils.LayListData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CareRecordServiceImpl implements CareRecordService {
    CareRecordDao careRecordDao = new CareRecordDaoImpl();
    LoginDao loginDao = new LoginDaoImpl();

    /**
     * 查询所有的护理记录
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void careInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        Integer aid = account.getAid();
        Integer uid = loginDao.selectMyUserInfo(aid).get(0).getUid();
        String str_page = req.getParameter("page");
        String str_limit = req.getParameter("limit");
        String lname = req.getParameter("lname");
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
            lid = careRecordDao.selectOneLiveLidByLname(lname).get(0).getLid();
            careRecodeList = careRecordDao.selectOneCareRecord(lid, uid, start, limit);
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
     * 前往修改护理记录页面
     *
     * @param req
     * @param resp
     */
    @Override
    public void toUpdateCareRecord(HttpServletRequest req, HttpServletResponse resp) {
        String careid = req.getParameter("careid");
        CareRecode careRecode = careRecordDao.selectOneCareRecordByCareid(careid).get(0);
        req.setAttribute("carerecord", careRecode);
    }

    /**
     * 修改护理记录
     *
     * @param req
     * @param resp
     */
    @Override
    public void updateCareRecord(HttpServletRequest req, HttpServletResponse resp) {
        String careid = req.getParameter("careid");
        String lname = req.getParameter("lname");
        Integer lid = careRecordDao.selectOneLiveLidByLname(lname).get(0).getLid();
        String uname = req.getParameter("uname");
        Integer uid = careRecordDao.selectOneUserUidByUnama(uname).get(0).getUid();
        String careinfo = req.getParameter("careinfo");
        int result = careRecordDao.updateCareRecord(careid, lid, uid, careinfo);
        String msg = null;
        if (result > 0) {
            msg = "修改成功";
            req.getSession().setAttribute("msg", msg);
        } else {
            msg = "修改失败";
            req.getSession().setAttribute("msg", msg);
        }
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
        String lname = req.getParameter("lname");
        Integer lid = careRecordDao.selectOneLiveLidByLname(lname).get(0).getLid();
        String uname = req.getParameter("uname");
        Integer uid = careRecordDao.selectOneUserUidByUnama(uname).get(0).getUid();
        String careinfo = req.getParameter("careinfo");
        int result = careRecordDao.addCareRecord(lid, uid, careinfo);
        resp.getWriter().write(result);
    }
}
