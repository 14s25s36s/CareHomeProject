package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.LoginDao;
import com.careHome.dao.impl.LoginDaoImpl;
import com.careHome.pojo.Account;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
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
import java.util.List;
import java.util.Locale;

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
        Account account = null;
        String decryptPwd = null;
        String dbPwd = null;
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
    public void toMyInfo(HttpServletRequest req, HttpServletResponse resp) {
        Account account = (Account) req.getSession().getAttribute(Sys.LOGIN_USER);
        Integer aid = account.getAid();
        UserInfo userInfo = null;
        List<UserInfo> userInfoList = loginDao.selectMyUserInfo(aid);
        if (userInfoList.size() > 0) {
            userInfo = userInfoList.get(0);
            req.getSession().setAttribute(Sys.USER_INFO, userInfo);
        }

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
        Integer uid = null;
        List<UserInfo> userInfoList = loginDao.selectMyUserInfo(aid);
        if (userInfoList.size() > 0) {
            uid = userInfoList.get(0).getUid();
        }
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
        Integer uid = null;
        List<UserInfo> userInfoList = loginDao.selectMyUserInfo(aid);
        if (userInfoList.size() > 0) {
            uid = userInfoList.get(0).getUid();
        }
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
        Integer uid = null;
        List<UserInfo> userInfoList = loginDao.selectMyUserInfo(aid);
        if (userInfoList.size() > 0) {
            uid = userInfoList.get(0).getUid();
        }
        List<LiveInfo> liveInfoList = loginDao.selectOneMyFamilyInfo(uid);
        String json = JSON.toJSONString(liveInfoList);
        resp.getWriter().write(json);
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
