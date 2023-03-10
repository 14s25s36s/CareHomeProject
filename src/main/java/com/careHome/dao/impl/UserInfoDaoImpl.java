package com.careHome.dao.impl;

import com.careHome.dao.UserInfoDao;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
import com.careHome.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;

import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao {

    /**
     * 查询所有用户信息
     *
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<UserInfo> selectAllUserInfo(int start, int limit) {
        String sql = "SELECT uid,uname,usex,uage,telephone,emergencycall,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE deleted=0 LIMIT ?,? ";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, start, limit);
        return userInfoList;
    }

    /**
     * 查询所有用户的数量
     *
     * @return
     */
    @Override
    public int selectCountUserInfo() {
        String sql = "SELECT COUNT(*) FROM userinfo WHERE deleted=0";
        int count = JDBCUtils.getInt(sql);
        return count;
    }

    /**
     * 模糊查询在姓名条件下所有的用户的信息
     *
     * @param checktext
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<UserInfo> selectOneUserInfo(String checktext, int start, int limit) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT uid,uname,usex,uage,telephone,emergencycall,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE uname LIKE ? OR usex LIKE ? OR ustate LIKE ? AND deleted=0 LIMIT ?,? ";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, checktext, checktext, checktext, start, limit);
        return userInfoList;
    }

    @Override
    public List<UserInfo> selectUserInfoByUstate(String ustate, int start, int limit) {
        String sql = "SELECT uid,uname,usex,uage,telephone,emergencycall,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE ustate=? AND deleted=0 LIMIT ?,? ";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, ustate, start, limit);
        return userInfoList;
    }

    @Override
    public int selectCountUserInfoByUstate(String ustate) {
        String sql = "SELECT COUNT(*) FROM userinfo WHERE ustate=? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, ustate);
        return count;
    }

    @Override
    public int addCareUser(Integer uid, String uname) {
        String sql = "INSERT INTO careuser (careuid,careuname) VALUES (?,?)";
        int result = JDBCUtils.insertData(sql, uid, uname);
        return result;
    }

    @Override
    public List<UserInfo> selectOneUserInfoByAid(int aid) {
        String sql = "SELECT uid,uname,usex,uage,telephone,emergencycall,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE aid=?";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, aid);
        return userInfoList;
    }

    /**
     * 模糊查询在姓名条件下的所有的用户数量
     *
     * @param checktext
     * @return
     */
    @Override
    public int selectCountUserInfo(String checktext) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT COUNT(*) FROM userinfo WHERE uname LIKE ? OR usex LIKE ? OR ustate LIKE ? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, checktext, checktext, checktext);
        return count;
    }

    /**
     * 删除一条用户信息
     *
     * @param uid
     * @return
     */
    @Override
    public int deletedOneUserInfo(String uid) {
        String sql = "UPDATE userinfo SET deleted=1 WHERE uid=?";
        int result = JDBCUtils.deleteData(sql, uid);
        return result;
    }

    /**
     * 通过uid查询一条用户信息
     *
     * @param uid
     * @return
     */
    @Override
    public List<UserInfo> selectOneUserInfoByUid(String uid) {
        String sql = "SELECT uid,uname,usex,uage,telephone,emergencycall,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE uid=?";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, uid);
        return userInfoList;
    }

    /**
     * 修改用户信息
     *
     * @param uid
     * @param uname
     * @param usex
     * @param uage
     * @param uaddress
     * @return
     */
    @Override
    public int updateUserInfo(String uid, String uname, String usex, String uage, String uaddress, String telephone, String emergencycall) {
        String sql = "UPDATE userinfo SET uname=?,usex=?,uage=?,uaddress=?,telephone=?,emergencycall=? WHERE uid=?";
        int result = JDBCUtils.updateData(sql, uname, usex, uage, uaddress, telephone, emergencycall, uid);
        return result;
    }

    @Override
    public int deletedCareUserByUid(String uid) {
        String sql = "UPDATE careuser SET deleted=1 WHERE careuid=?";
        int result = JDBCUtils.deleteData(sql, uid);
        return result;
    }

    @Override
    public int deletedAccountByAid(Integer aid) {
        String sql = "UPDATE account SET deleted=1 WHERE aid=?";
        int result = JDBCUtils.deleteData(sql, aid);
        return result;
    }

    /**
     * 添加用户信息
     *
     * @param uname
     * @param usex
     * @param uage
     * @param uaddress
     * @return
     */
    @Override
    public int addUserInfo(String uname, String usex, String uage, String uaddress, String permissions, String telephone, String emergencycall, Integer aid) {
        String sql = "INSERT INTO userinfo (uname,usex,uage,uaddress,permissions,telephone,emergencycall,aid) VALUES(?,?,?,?,?,?,?,?)";
        int result = JDBCUtils.insertData(sql, uname, usex, uage, uaddress, permissions, telephone, emergencycall, aid);
        return result;
    }

    /**
     * 检查用户家属是否存在
     *
     * @param uid
     * @return
     */
    @Override
    public int checkLiveInfoExist(String uid) {
        String sql = "SELECT COUNT(*) FROM liveinfo WHERE uid=? AND deleted=0 LIMIT 1";
        int result = JDBCUtils.getPreparedInt(sql, uid);
        return result;
    }

    @Override
    public int updateUserState(String ustate, String uid) {
        String sql = "UPDATE userinfo SET ustate=? WHERE uid=?";
        int result = JDBCUtils.updateData(sql, ustate, uid);
        return result;
    }
}
