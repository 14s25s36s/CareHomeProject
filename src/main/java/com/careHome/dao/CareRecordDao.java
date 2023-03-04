package com.careHome.dao;

import com.careHome.pojo.CareRecode;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;

import java.util.List;

public interface CareRecordDao {
    public List<CareRecode> selectAllCareRecord(Integer uid, int start, int limit);

    public int selectCountCareRecord(Integer uid);

    public List<CareRecode> selectOneCareRecord(String lname, Integer uid, int start, int limit);

    public int selectCountCareRecord(Integer lid, Integer uid);

    public int deletedOneCareRecord(String careid);

    public int updateCareRecord(String careid, String careinfo);

    public int addCareRecord(String lid, Integer uid, String careinfo);
}
