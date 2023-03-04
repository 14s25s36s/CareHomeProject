package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.AreaDao;
import com.careHome.dao.LoginDao;
import com.careHome.dao.impl.AreaDaoImpl;
import com.careHome.dao.impl.LoginDaoImpl;
import com.careHome.pojo.*;
import com.careHome.service.LoginService;
import com.careHome.utils.LayListData;
import com.careHome.utils.MD5Utils;
import com.careHome.utils.Sys;
import com.careHome.utils.VerifyCode;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LoginServiceImpl implements LoginService {

    LoginDao loginDao = new LoginDaoImpl();
    AreaDao areaDao = new AreaDaoImpl();

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
        Account account = null;
        String decryptPwd = null;
        String dbPwd = null;
        String[] areas = null;
        Province province = null;
        City city = null;
        Area area = null;
        Integer provinceid = null;
        Integer cityid = null;
        Integer areaid = null;
        String address = null;
        Map<String, Object> addressMap = new HashMap<String, Object>();
        List<Account> accountList = loginDao.selectOnAccountInfo(userAccount);
        if (accountList.size() > 0) {
            account = accountList.get(0);
            dbPwd = account.getPassword();
            decryptPwd = MD5Utils.decrypt(account.getPassword(), password);
            if (userAccount.equals(account.getUseraccount()) && decryptPwd.equals(dbPwd) && 0 == account.getPermissions()) {
                req.getSession().setAttribute(Sys.LOGIN_USER, account);
                Integer aid = account.getAid();
                UserInfo userInfo = null;
                List<UserInfo> userInfoList = loginDao.selectMyUserInfo(aid);
                if (userInfoList.size() > 0) {
                    userInfo = userInfoList.get(0);
                    address = userInfo.getUaddress();
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
                        req.getSession().setAttribute(Sys.USER_ADDRESS, addressMap);
                    }
                    req.getSession().setAttribute(Sys.USER_INFO, userInfo);
                }
                resp.getWriter().write("超级管理员登陆成功");
            } else if (userAccount.equals(account.getUseraccount()) && decryptPwd.equals(dbPwd) && 1 == account.getPermissions()) {
                req.getSession().setAttribute(Sys.LOGIN_USER, account);
                Integer aid = account.getAid();
                UserInfo userInfo = null;
                List<UserInfo> userInfoList = loginDao.selectMyUserInfo(aid);
                if (userInfoList.size() > 0) {
                    userInfo = userInfoList.get(0);
                    address = userInfo.getUaddress();
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
                        req.getSession().setAttribute(Sys.USER_ADDRESS, addressMap);
                    }
                    req.getSession().setAttribute(Sys.USER_INFO, userInfo);
                }
                resp.getWriter().write("员工登陆成功");
            } else if (userAccount.equals(account.getUseraccount()) && decryptPwd.equals(dbPwd) && 2 == account.getPermissions()) {
                req.getSession().setAttribute(Sys.LOGIN_USER, account);
                Integer aid = account.getAid();
                UserInfo userInfo = null;
                List<UserInfo> userInfoList = loginDao.selectMyUserInfo(aid);
                if (userInfoList.size() > 0) {
                    userInfo = userInfoList.get(0);
                    address = userInfo.getUaddress();
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
                        req.getSession().setAttribute(Sys.USER_ADDRESS, addressMap);
                    }
                    req.getSession().setAttribute(Sys.USER_INFO, userInfo);
                }
                resp.getWriter().write("登陆成功");
            } else {
                resp.getWriter().write("登陆失败");
            }
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
        String encryptPwd = MD5Utils.encrypt(password);
        int result = loginDao.addUserAccount(useraccount, encryptPwd);
        Account account = null;
        List<Account> accountList = loginDao.selectOnAccountInfo(useraccount);
        int aid = -1;
        if (accountList.size() > 0) {
            account = accountList.get(0);
            aid = account.getAid();
        }
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
    public void getVerifyCode(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int width = 200;
            int height = 69;
            //生成对应宽高的初始图片
            BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //单独的一个类方法，出于代码复用考虑，进行了封装。
            //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
            String randomText = VerifyCode.drawRandomText(width, height, verifyImg);
            req.getSession().setAttribute("verifyCode", randomText);
            resp.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
            OutputStream os = resp.getOutputStream(); //获取文件输出流
            ImageIO.write(verifyImg, "png", os);//输出图片流
            os.flush();
            os.close();//关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void judgeCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("vercode").toLowerCase(Locale.ROOT);
        String verifyCode = (String) req.getSession().getAttribute("verifyCode");
        verifyCode = verifyCode.toLowerCase(Locale.ROOT);
        String msg = null;
        if (code.equals(verifyCode)) {
            msg = "验证码相同";
            resp.getWriter().write(msg);
        } else {
            msg = "验证码错误";
            resp.getWriter().write(msg);
        }
    }

}
