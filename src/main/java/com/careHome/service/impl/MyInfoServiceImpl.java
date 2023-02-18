package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.LoginDao;
import com.careHome.dao.impl.LoginDaoImpl;
import com.careHome.pojo.Account;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
import com.careHome.service.MyInfoService;
import com.careHome.utils.LayListData;
import com.careHome.utils.Sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyInfoServiceImpl implements MyInfoService {
    LoginDao loginDao = new LoginDaoImpl();

    /**
     * 前往我的个人信息
     *
     * @param req
     * @param resp
     */
    @Override
    public void toMyInfo(HttpServletRequest req, HttpServletResponse resp) {
        Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
        Integer aid = account.getAid();
        UserInfo userInfo = loginDao.selectMyUserInfo(aid).get(0);
        req.setAttribute("userinfo", userInfo);
    }

    /**
     * 修改我的个人信息
     *
     * @param req
     * @param resp
     */
    @Override
    public void updateMyInfo(HttpServletRequest req, HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        String uname = req.getParameter("uname");
        String usex = req.getParameter("usex");
        String uage = req.getParameter("uage");
        String uaddress = req.getParameter("uaddress");
        int result = loginDao.updateMyInfo(uid, uname, usex, uage, uaddress);
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
     * 添加家属信息
     *
     * @param req
     * @param resp
     */
    @Override
    public void addFamilyInfo(HttpServletRequest req, HttpServletResponse resp) {
        Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
        Integer aid = account.getAid();
        Integer uid = loginDao.selectMyUserInfo(aid).get(0).getUid();
        String lname = req.getParameter("lname");
        String lage = req.getParameter("lage");
        String lsex = req.getParameter("lsex");
        int result = loginDao.addFamilyInfo(lname, lage, lsex, uid);
        String msg = null;
        if (result > 0) {
            msg = "家属添加成功";
            req.getSession().setAttribute("msg", msg);
        } else {
            msg = "家属添加失败";
            req.getSession().setAttribute("msg", msg);
        }
    }

    /**
     * 我的家属信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void myFamilyInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
        Integer aid = account.getAid();
        Integer uid = loginDao.selectMyUserInfo(aid).get(0).getUid();
        String str_page = req.getParameter("page");
        String str_limit = req.getParameter("limit");
        String lname = req.getParameter("lname");
        System.out.println("lname:" + lname);
        int page = Integer.parseInt(str_page);
        int limit = Integer.parseInt(str_limit);
        int start = (page - 1) * limit;
        int count = 0;
        List<LiveInfo> liveInfoList = null;
        if (lname == null) {
            liveInfoList = loginDao.selectMyFamilyInfo(uid, start, limit);
            count = loginDao.selectCountFamily(uid);
        } else {
            liveInfoList = loginDao.selectOneMyFamilyInfo(lname, uid, start, limit);
            count = loginDao.selectCountFamily(lname, uid);
        }
        LayListData layListData = new LayListData(count, liveInfoList);
        String json = JSON.toJSONString(layListData);
        resp.getWriter().write(json);
    }

    /**
     * 我的家属姓名
     * 用于下拉列表
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void myFamilyName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
        Integer aid = account.getAid();
        Integer uid = loginDao.selectMyUserInfo(aid).get(0).getUid();
        List<LiveInfo> liveInfoList = loginDao.selectOneMyFamilyInfo(uid);
        String json = JSON.toJSONString(liveInfoList);
        resp.getWriter().write(json);
    }
}
