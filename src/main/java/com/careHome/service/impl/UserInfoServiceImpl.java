package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.AreaDao;
import com.careHome.dao.LoginDao;
import com.careHome.dao.UserInfoDao;
import com.careHome.dao.impl.AreaDaoImpl;
import com.careHome.dao.impl.LoginDaoImpl;
import com.careHome.dao.impl.UserInfoDaoImpl;
import com.careHome.pojo.*;
import com.careHome.service.UserInfoService;
import com.careHome.utils.LayListData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoServiceImpl implements UserInfoService {
    UserInfoDao userInfoDao = new UserInfoDaoImpl();
    AreaDao areaDao = new AreaDaoImpl();
    LoginDao loginDao = new LoginDaoImpl();

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
            if (checktext.equals("封号")) {
                checktext = "1";
            } else if (checktext.equals("现用")) {
                checktext = "0";
            }
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
        int accountResult = 0;
        int careResult = 0;
        Integer aid = null;
        Integer permissions = null;
        UserInfo userInfo = null;
        int liveExist = userInfoDao.checkLiveInfoExist(uid);
        if (liveExist == 0) {
            List<UserInfo> userInfoList = userInfoDao.selectOneUserInfoByUid(uid);
            if (userInfoList.size() > 0) {
                userInfo = userInfoList.get(0);
                aid = userInfo.getAid();
                permissions = userInfo.getPermissions();
            }
            result = userInfoDao.deletedOneUserInfo(uid);
            accountResult = userInfoDao.deletedAccountByAid(aid);
            if (permissions == 1) {
                careResult = userInfoDao.deletedCareUserByUid(uid);
            }
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
        Map<String, Object> addressMap = new HashMap<String, Object>();
        String address = userInfo.getUaddress();
        String[] areas = null;
        Province province = null;
        City city = null;
        Area area = null;
        Integer provinceid = null;
        Integer cityid = null;
        Integer areaid = null;
        if (address != null) {
            areas = address.split("-");
            List<Province> provinceList = areaDao.selectOneProvinceByProvince(areas[0]);
            List<City> cityList = areaDao.selectOneCityByCity(areas[1]);
            List<Area> areaList = areaDao.selectOneAreaByArea(areas[2]);
            if (provinceList.size() > 0) {
                province = provinceList.get(0);
                provinceid = province.getProvinceid();
                addressMap.put("province", areas[0]);
                addressMap.put("provinceid", provinceid);
            }
            if (cityList.size() > 0) {
                city = cityList.get(0);
                cityid = city.getCityid();
                addressMap.put("city", areas[1]);
                addressMap.put("cityid", cityid);
            }
            if (areaList.size() > 0) {
                area = areaList.get(0);
                areaid = area.getAreaid();
                addressMap.put("area", areas[2]);
                addressMap.put("areaid", areaid);
            }

            req.setAttribute("addressMap", addressMap);
        }

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
        String telephone = req.getParameter("telephone");
        String emergencycall = req.getParameter("emergencycall");
        Integer provinceid = Integer.parseInt(req.getParameter("province"));
        Integer cityid = Integer.parseInt(req.getParameter("city"));
        Integer areaid = Integer.parseInt(req.getParameter("area"));
        List<Province> provinceList = areaDao.selectOneProvince(provinceid);
        List<City> cityList = areaDao.selectOneCity(cityid);
        List<Area> areaList = areaDao.selectOneArea(areaid);
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
        String telephone = req.getParameter("telephone");
        String emergencycall = req.getParameter("emergencycall");
        Integer provinceid = Integer.parseInt(req.getParameter("province"));
        Integer cityid = Integer.parseInt(req.getParameter("city"));
        Integer areaid = Integer.parseInt(req.getParameter("area"));
        List<Province> provinceList = areaDao.selectOneProvince(provinceid);
        List<City> cityList = areaDao.selectOneCity(cityid);
        List<Area> areaList = areaDao.selectOneArea(areaid);
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
        String useraccount = req.getParameter("useraccount");
        String permissions = req.getParameter("permissions");
        int result = -1;
        int accountResult = loginDao.addUserAccountByAdministrator(useraccount, permissions);
        int aid = -1;
        List<Account> accountList = null;
        Account accoun = null;
        List<UserInfo> userInfoList = null;
        UserInfo userInfo = null;
        Integer uid = null;
        int rowCount = -1;
        if (accountResult > 0) {
            accountList = loginDao.selectOnAccountInfoByUserAccount(useraccount);
            if (accountList.size() > 0) {
                accoun = accountList.get(0);
                aid = accoun.getAid();
                result = userInfoDao.addUserInfo(uname, usex, uage, uaddress, permissions, telephone, emergencycall, aid);
            }
        }
        String msg = null;
        if (result > 0) {
            userInfoList = userInfoDao.selectOneUserInfoByAid(aid);
            if (userInfoList.size() > 0) {
                userInfo = userInfoList.get(0);
                uid = userInfo.getUid();
                rowCount = userInfoDao.addCareUser(uid, uname);
            }
            if (rowCount > 0) {
                msg = "添加成功";
                resp.getWriter().write(msg);
            } else {
                msg = "添加失败";
                resp.getWriter().write(msg);
            }
        } else {
            msg = "添加失败";
            resp.getWriter().write(msg);
        }

    }

    @Override
    public void updateUserState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ustate = req.getParameter("ustate");
        String uid = req.getParameter("uid");
        int result = 0;
        String unban = "0";
        String ban = "1";
        String msg = null;
        if (ustate.equals(ban)) {
            result = userInfoDao.updateUserState(unban, uid);
        } else if (ustate.equals(unban)) {
            result = userInfoDao.updateUserState(ban, uid);
        }
        if (result > 0) {
            msg = "修改成功";
            resp.getWriter().write(msg);
        } else {
            msg = "修改失败";
            resp.getWriter().write(msg);
        }

    }
}
