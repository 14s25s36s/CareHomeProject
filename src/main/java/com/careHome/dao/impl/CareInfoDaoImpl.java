package com.careHome.dao.impl;

import com.careHome.dao.CareInfoDao;
import com.careHome.pojo.UserInfo;
import com.careHome.utils.JDBCUtils;

import java.util.List;

public class CareInfoDaoImpl implements CareInfoDao {
    @Override
    public List<UserInfo> selectAllCareInfo(int start, int limit) {
        String sql = "SELECT uid,uname,usex,uage,uaddress,ustate,aid,permissions,deleted " +
                "FROM userinfo WHERE permissions=1 and deleted=0 LIMIT ?,?";
        List<UserInfo> careInfoList = JDBCUtils.selectData(sql, UserInfo.class, start, limit);
        return careInfoList;
    }

    @Override
    public int selectCountCareInfo() {
        String sql = "SELECT COUNT(*) FROM userinfo WHERE permissions=1 AND deleted=0";
        int count = JDBCUtils.getInt(sql);
        return count;
    }

    @Override
    public List<UserInfo> selectOneCareInfo(String checktext, int start, int limit) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT uid,uname,usex,uage,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE uname LIKE ? AND permissions=1 AND deleted=0 LIMIT ?,? ";
        List<UserInfo> careInfoList = JDBCUtils.selectData(sql, UserInfo.class, checktext, start, limit);
        return careInfoList;
    }

    @Override
    public int selectCountCareInfo(String checktext) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT COUNT(*) FROM userinfo WHERE uname LIKE ? AND permissions=1 AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, checktext);
        return count;
    }

    @Override
    public int deletedOneCareInfo(String uid) {
        String sql = "UPDATE userinfo SET deleted=1 WHERE uid=? AND permissions=1";
        int result = JDBCUtils.deleteData(sql, uid);
        return result;
    }

    @Override
    public int updateCareInfo(String uid, String uname, String usex, String uage, String uaddress, String ustate) {
        String sql = "UPDATE userinfo SET uname=?,usex=?,uage=?,uaddress=?,ustate=? WHERE uid=? AND permissions=1";
        int result = JDBCUtils.updateData(sql, uname, usex, uage, uaddress, ustate, uid);
        return result;
    }

    @Override
    public int addCareInfo(String uname, String usex, String uage, String uaddress) {
        String sql = "INSERT INTO userinfo (uname,usex,uage,uaddress,permissions) VALUES(?,?,?,?,1)";
        int result = JDBCUtils.insertData(sql, uname, usex, uage, uaddress);
        return result;
    }

}
