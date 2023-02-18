package com.careHome.dao.impl;

import com.careHome.dao.CareRecodeDao;
import com.careHome.pojo.CareRecode;

import java.util.List;

public class CareRecodeDaoImpl implements CareRecodeDao {
    @Override
    public List<CareRecode> selectAllCareRecode(Integer uid, int start, int limit) {
        String sql="SELECT care.careid AS careid,care.uid AS uid,user.uname AS uname,care.lid AS lid," +
                "live.lname AS lname,care.caredate AS caredate,care.careinfo AS careinfo,care.deleted AS deleted " +
                "FROM care_recode"
    }

    @Override
    public int selectCountCareRecode(Integer uid) {
        return 0;
    }

    @Override
    public List<CareRecode> selectOneCareRecode(String checktext, Integer uid, int start, int limit) {
        return null;
    }

    @Override
    public int selectCountCareRecode(String checktext, Integer uid) {
        return 0;
    }
}
