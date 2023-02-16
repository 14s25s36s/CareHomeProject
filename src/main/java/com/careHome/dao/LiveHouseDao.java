package com.careHome.dao;

import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;

import java.util.List;

public interface LiveHouseDao {

    public List<LiveInfo> selectAllLiveInfo(int page, int limit);

    public int selectCountLiveHouse();

    public int selectCountLiveHouse(String checktext);

    public List<LiveInfo> selectOneLiveHouseInfo(String checktext, int page, int limit);

    public List<LiveInfo> selectOneLiveInfo(String lid);

    public int deletedOneLiveInfo(String lid);

    public int updateLiveInfo(String lname, String lage, String lsex, String lstate, String lid);

    public int addUserInfo(String lname, String lage, String lsex, String uid);
}
