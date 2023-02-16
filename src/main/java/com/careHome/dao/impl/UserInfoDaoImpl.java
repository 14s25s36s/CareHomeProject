package com.careHome.dao.impl;

import com.careHome.dao.UserInfoDao;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
import com.careHome.utils.JDBCUtils;

import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao {
    @Override
    public List<UserInfo> selectAllUserInfo(int start, int limit) {
        String sql = "SELECT uid,uname,usex,uage,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE deleted=0 LIMIT ?,? ";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, start, limit);
        return userInfoList;
    }

    @Override
    public int selectCountUserInfo() {
        String sql = "SELECT COUNT(*) FROM userinfo WHERE deleted=0";
        int count = JDBCUtils.getInt(sql);
        return count;
    }

    @Override
    public List<UserInfo> selectOneUserInfo(String checktext, int start, int limit) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT uid,uname,usex,uage,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE uname LIKE ? AND deleted=0 LIMIT ?,? ";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, checktext, start, limit);
        return userInfoList;
    }

    @Override
    public int selectCountUserInfo(String checktext) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT COUNT(*) FROM userinfo WHERE uname LIKE ? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, checktext);
        return count;
    }

    @Override
    public int deletedOneUserInfo(String uid) {
        String sql = "UPDATE userinfo SET deleted=1 WHERE uid=?";
        int result = JDBCUtils.deleteData(sql, uid);
        return result;
    }

    @Override
    public List<UserInfo> selectOneUserInfoByUid(String uid) {
        String sql = "SELECT uid,uname,usex,uage,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE uid=?";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, uid);
        return userInfoList;
    }

    @Override
    public int updateUserInfo(String uid, String uname, String usex, String uage, String uaddress, String ustate) {
        String sql = "UPDATE userinfo SET uname=?,usex=?,uage=?,uaddress=?,ustate=? WHERE uid=?";
        int result = JDBCUtils.updateData(sql, uname, usex, uage, uaddress, ustate, uid);
        return result;
    }

    @Override
    public int addUserInfo(String uname, String usex, String uage, String uaddress) {
        String sql = "INSERT INTO userinfo (uname,usex,uage,uaddress) VALUES(?,?,?,?)";
        int result = JDBCUtils.insertData(sql, uname, usex, uage, uaddress);
        return result;
    }

    @Override
    public int checkLiveInfoExist(String uid) {
        String sql = "SELECT COUNT(*) FROM liveinfo WHERE uid=? AND deleted=0 LIMIT 1";
        int result = JDBCUtils.getPreparedInt(sql, uid);
        return result;
    }
}
