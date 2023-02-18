package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.UserInfoDao;
import com.careHome.dao.impl.UserInfoDaoImpl;
import com.careHome.pojo.UserInfo;
import com.careHome.service.UserInfoService;
import com.careHome.utils.LayListData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    UserInfoDao userInfoDao = new UserInfoDaoImpl();

    /**
     * 查询所有的用户信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void userInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String str_page = req.getParameter("page");
        String str_limit = req.getParameter("limit");
        String checktext = req.getParameter("checktext");
        int page = Integer.parseInt(str_page);
        int limit = Integer.parseInt(str_limit);
        int start = (page - 1) * limit;
        int count = 0;
        List<UserInfo> userInfoList = null;
        if (checktext == null) {
            userInfoList = userInfoDao.selectAllUserInfo(start, limit);
            count = userInfoDao.selectCountUserInfo();
        } else {
            userInfoList = userInfoDao.selectOneUserInfo(checktext, start, limit);
            count = userInfoDao.selectCountUserInfo(checktext);
        }
        LayListData layListData = new LayListData(count, userInfoList);
        String json = JSON.toJSONString(layListData);
        resp.getWriter().write(json);
    }

    /**
     * 删除用户信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void deleteuserinfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uid = req.getParameter("uid");
        int result = 0;
        int liveExist = userInfoDao.checkLiveInfoExist(uid);
        if (liveExist == 0) {
            result = userInfoDao.deletedOneUserInfo(uid);
            resp.getWriter().write(result);
        } else {
            resp.getWriter().write("该用户有家属入住，不可删除");
        }


    }

    /**
     * 前往修改用户信息
     *
     * @param req
     * @param resp
     */
    @Override
    public void toUpdateUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        UserInfo userInfo = userInfoDao.selectOneUserInfoByUid(uid).get(0);
        req.setAttribute("userInfo", userInfo);
    }

    /**
     * 修改用户信息
     *
     * @param req
     * @param resp
     */
    @Override
    public void updateUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        String uname = req.getParameter("uname");
        String usex = req.getParameter("usex");
        String uage = req.getParameter("uage");
        String uaddress = req.getParameter("uaddress");
        String ustate = req.getParameter("ustate");
        String msg = null;
        int result = userInfoDao.updateUserInfo(uid, uname, usex, uage, uaddress, ustate);
        if (result > 0) {
            msg = "修改成功";
            req.getSession().setAttribute("msg", msg);
        } else {
            msg = "修改失败";
            req.getSession().setAttribute("msg", msg);
        }
    }

    /**
     * 添加用户信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void addUserInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uname = req.getParameter("uname");
        String usex = req.getParameter("usex");
        String uage = req.getParameter("uage");
        String uaddress = req.getParameter("uaddress");
        int result = userInfoDao.addUserInfo(uname, usex, uage, uaddress);
        resp.getWriter().write(result);
    }
}
