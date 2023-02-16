package com.careHome.dao;

import com.careHome.pojo.UserInfo;

import java.util.List;

public interface UserInfoDao {
    public List<UserInfo> selectAllUserInfo(int start, int limit);

    public int selectCountUserInfo();

    public List<UserInfo> selectOneUserInfo(String checktext, int start, int limit);

    public int selectCountUserInfo(String checktext);

    public int deletedOneUserInfo(String uid);

    public List<UserInfo> selectOneUserInfoByUid(String uid);

    public int updateUserInfo(String uid, String uname, String usex, String uage, String uaddress, String ustate);

    public int addUserInfo(String uname, String usex, String uage, String uaddress);

    public int checkLiveInfoExist(String uid);
}
