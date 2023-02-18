package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.CareRecodeDao;
import com.careHome.dao.LoginDao;
import com.careHome.dao.impl.CareRecodeDaoImpl;
import com.careHome.dao.impl.LoginDaoImpl;
import com.careHome.pojo.Account;
import com.careHome.pojo.CareRecode;
import com.careHome.pojo.LiveInfo;
import com.careHome.service.CareRecodeService;
import com.careHome.utils.LayListData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CareRecodeServiceImpl implements CareRecodeService {
    CareRecodeDao careRecodeDao = new CareRecodeDaoImpl();
    LoginDao loginDao = new LoginDaoImpl();

    @Override
    public void careInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        Integer aid = account.getAid();
        Integer uid = loginDao.selectMyUserInfo(aid).get(0).getUid();
        String str_page = req.getParameter("page");
        String str_limit = req.getParameter("limit");
        String checktext = req.getParameter("checktext");
        int page = Integer.parseInt(str_page);
        int limit = Integer.parseInt(str_limit);
        int start = (page - 1) * limit;
        int count = 0;
        List<CareRecode> careRecodeList = null;
        if (checktext == null) {
            careRecodeList = careRecodeDao.selectAllCareRecode(uid, start, limit);
            count = careRecodeDao.selectCountCareRecode(uid);
        } else {
            careRecodeList = careRecodeDao.selectOneCareRecode(checktext, uid, start, limit);
            count = careRecodeDao.selectCountCareRecode(checktext, uid);
        }
        LayListData layListData = new LayListData(count, careRecodeList);
        String json = JSON.toJSONString(layListData);
        resp.getWriter().write(json);
    }
}
