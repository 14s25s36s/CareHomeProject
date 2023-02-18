package com.careHome.dao;

import com.careHome.pojo.CareRecode;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;

import java.util.List;

public interface CareRecordDao {
    public List<CareRecode> selectAllCareRecord(Integer uid, int start, int limit);

    public int selectCountCareRecord(Integer uid);

    public List<CareRecode> selectOneCareRecord(Integer lid, Integer uid, int start, int limit);

    public int selectCountCareRecord(Integer lid, Integer uid);

    public int deletedOneCareRecord(String careid);

    public List<CareRecode> selectOneCareRecordByCareid(String careid);

    public List<LiveInfo> selectOneLiveLidByLname(String lname);

    public List<UserInfo> selectOneUserUidByUname(String uname);

    public int updateCareRecord(String careid, Integer lid, Integer uid, String careinfo);

    public int addCareRecord(Integer lid, Integer uid, String careinfo);
}
