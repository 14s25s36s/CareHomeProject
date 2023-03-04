package com.careHome.dao;

import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;

import java.util.List;

public interface CareInfoDao {
    public List<UserInfo> selectAllCareInfo(int start, int limit);

    public int selectCountCareInfo();

    public List<UserInfo> selectOneCareInfo(String checktext, int start, int limit);

    public int selectCountCareInfo(String checktext);

    public int deletedOneCareInfo(String uid);

    public List<LiveInfo> getLiveInfoByCare(String careid);
}
