package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.AreaDao;
import com.careHome.dao.LoginDao;
import com.careHome.dao.UserInfoDao;
import com.careHome.dao.impl.AreaDaoImpl;
import com.careHome.dao.impl.LoginDaoImpl;
import com.careHome.dao.impl.UserInfoDaoImpl;
import com.careHome.pojo.*;
import com.careHome.service.MyInfoService;
import com.careHome.utils.LayListData;
import com.careHome.utils.MD5Utils;
import com.careHome.utils.Sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyInfoServiceImpl implements MyInfoService {
    LoginDao loginDao = new LoginDaoImpl();
    UserInfoDao userInfoDao = new UserInfoDaoImpl();
    AreaDao areaDao = new AreaDaoImpl();

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
        String telephone = req.getParameter("telephone");
        String emergencycall = req.getParameter("emergencycall");
        Integer provinceid = Integer.parseInt(req.getParameter("province"));
        Integer cityid = Integer.parseInt(req.getParameter("city"));
        Integer areaid = Integer.parseInt(req.getParameter("area"));
        List<Province> provinceList = areaDao.selectOneProvince(provinceid);
        List<City> cityList = areaDao.selectOneCity(cityid);
        List<Area> areaList = areaDao.selectOneArea(areaid);
        UserInfo userInfo = null;
        String province = null;
        if (provinceList.size() > 0) {
            Province province1 = provinceList.get(0);
            province = province1.getProvince();
        }
        String city = null;
        if (cityList.size() > 0) {
            City city1 = cityList.get(0);
            city = city1.getCity();
        }
        String area = null;
        if (areaList.size() > 0) {
            Area area1 = areaList.get(0);
            area = area1.getArea();
        }
        String uaddress = province + "-" + city + "-" + area;
        String msg = null;
        int result = userInfoDao.updateUserInfo(uid, uname, usex, uage, uaddress, telephone, emergencycall);
        if (result > 0) {
            msg = "修改成功";
            Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
            Integer aid = account.getAid();
            List<UserInfo> userInfoList = loginDao.selectMyUserInfo(aid);
            if (userInfoList.size() > 0) {
                userInfo = userInfoList.get(0);
                req.getSession().setAttribute(Sys.USER_INFO, userInfo);
            }
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
        UserInfo userInfo = (UserInfo) req.getSession().getAttribute(Sys.USER_INFO);
        Integer uid = userInfo.getUid();
        String lname = req.getParameter("lname");
        String lage = req.getParameter("lage");
        String lsex = req.getParameter("lsex");
        String careuid = req.getParameter("careuid");
        String lstate = req.getParameter("lstate");
        int result = loginDao.addFamilyInfo(lname, lage, lsex, uid, careuid, lstate);
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
        UserInfo userInfo = (UserInfo) req.getSession().getAttribute(Sys.USER_INFO);
        Integer uid = userInfo.getUid();
        List<LiveInfo> liveInfoList = loginDao.selectOneMyFamilyInfo(uid);
        String json = JSON.toJSONString(liveInfoList);
        resp.getWriter().write(json);
    }

    @Override
    public void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
        Integer aid = account.getAid();
        String oldpassword = req.getParameter("oldpassword");
        String decryptPwd = MD5Utils.decrypt(account.getPassword(), oldpassword);
        String newpassword = req.getParameter("password");
        String newEncryptPwd = MD5Utils.decrypt(account.getPassword(), newpassword);
        String encryptPwd = MD5Utils.encrypt(newpassword);
        String msg = null;
        if (account.getPassword().equals(decryptPwd) && !decryptPwd.equals(newEncryptPwd)) {
            int result = loginDao.updatePassword(aid, encryptPwd);
            if (result > 0) {
                msg = "修改成功";
                resp.getWriter().write(msg);
            } else {
                msg = "修改失败";
                resp.getWriter().write(msg);
            }
        } else if (!account.getPassword().equals(decryptPwd)) {
            msg = "原密码错误";
            resp.getWriter().write(msg);
        }
    }
}
