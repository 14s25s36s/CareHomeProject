package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.LoginDao;
import com.careHome.dao.impl.LoginDaoImpl;
import com.careHome.pojo.Account;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
import com.careHome.service.LoginService;
import com.careHome.utils.LayListData;
import com.careHome.utils.Sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginServiceImpl implements LoginService {

    LoginDao loginDao = new LoginDaoImpl();

    /**
     * 登陆的方法
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userAccount = req.getParameter("useraccount");
        String password = req.getParameter("password");
        Account account = loginDao.selectOnAccountInfo(userAccount).get(0);
        if (userAccount.equals(account.getUseraccount()) && password.equals(account.getPassword()) && 0 == account.getPermissions()) {
            req.getSession().setAttribute(Sys.LOGIN_USER, account);
            resp.getWriter().write("超级管理员登陆成功");
        } else if (userAccount.equals(account.getUseraccount()) && password.equals(account.getPassword()) && 1 == account.getPermissions()) {
            req.getSession().setAttribute(Sys.LOGIN_USER, account);
            resp.getWriter().write("员工登陆成功");
        } else if (userAccount.equals(account.getUseraccount()) && password.equals(account.getPassword()) && 2 == account.getPermissions()) {
            req.getSession().setAttribute(Sys.LOGIN_USER, account);
            resp.getWriter().write("登陆成功");
        } else {
            resp.getWriter().write("登陆失败");
        }
    }

    /**
     * 检查用户名是否已经被注册
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void checkExist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String registeruseraccount = req.getParameter("registeruseraccount");
        int count = loginDao.selectCheckExist(registeruseraccount);
        String msg = null;
        if (count > 0) {
            msg = "用户名已存在";
            resp.getWriter().write(msg);
        } else {
            msg = "用户名不存在";
            resp.getWriter().write(msg);
        }
    }

    /**
     * 实现注册的方法
     *
     * @param req
     * @param resp
     */
    @Override
    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String useraccount = req.getParameter("registeruseraccount");
        String password = req.getParameter("registerpass");
        int result = loginDao.addUserAccount(useraccount, password);
        Account account = loginDao.selectOnAccountInfo(useraccount).get(0);
        int aid = account.getAid();
        int resultOther = loginDao.addAidtoUserInfo(aid);
        String msg = null;
        if (result > 0 && resultOther > 0) {
            msg = "注册成功";
            resp.getWriter().write(msg);
        } else {
            msg = "注册失败";
            resp.getWriter().write(msg);
        }
    }

    @Override
    public void toMyInfo(HttpServletRequest req, HttpServletResponse resp) {
        Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
        Integer aid = account.getAid();
        UserInfo userInfo = loginDao.selectMyUserInfo(aid).get(0);
        req.setAttribute("userinfo", userInfo);
    }

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
