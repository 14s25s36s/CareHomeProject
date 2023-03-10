package com.careHome.dao.impl;

import com.careHome.dao.LoginDao;
import com.careHome.pojo.Account;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
import com.careHome.utils.JDBCUtils;

import java.util.List;

public class LoginDaoImpl implements LoginDao {
    /**
     * 查询账号信息
     *
     * @param userAccount
     * @return
     */
    @Override
    public List<Account> selectOnAccountInfo(String userAccount) {
        String sql = "SELECT ac.aid AS aid,ac.useraccount AS useraccount,ac.password AS password" +
                ",ac.permissions AS permissions,ac.astate AS astate,ac.deleted AS deleted" +
                " FROM account AS ac" +
                " JOIN userinfo AS user ON user.aid=ac.aid" +
                " WHERE ac.useraccount=? AND user.ustate=0 AND user.deleted=0";
        List<Account> accountList = JDBCUtils.selectData(sql, Account.class, userAccount);
        return accountList;
    }

    /**
     * 查询用户名是否存在
     *
     * @param registeruseraccount
     * @return
     */
    @Override
    public int selectCheckExist(String registeruseraccount) {
        String sql = "SELECT COUNT(*) FROM account WHERE useraccount=? LIMIT 1";
        int count = JDBCUtils.getPreparedInt(sql, registeruseraccount);
        return count;
    }

    /**
     * 向数据库中插入注册的用户账户
     *
     * @param useraccount
     * @param password
     * @return
     */
    @Override
    public int addUserAccount(String useraccount, String password) {
        String sql = "INSERT INTO account (useraccount,password) VALUES (?,?)";
        int result = JDBCUtils.insertData(sql, useraccount, password);
        return result;
    }

    /**
     * 超级管理员插入用户时给予的默认账号
     *
     * @param useraccount
     * @param permissions
     * @return
     */
    @Override
    public int addUserAccountByAdministrator(String useraccount, String permissions) {
        String sql = "INSERT INTO account (useraccount,password,permissions) VALUES (?,'Aa123456.',?)";
        int result = JDBCUtils.insertData(sql, useraccount, permissions);
        return result;
    }

    /**
     * 将账号aid添加进用户信息
     *
     * @param aid
     * @return
     */
    @Override
    public int addAidtoUserInfo(int aid) {
        String sql = "INSERT INTO userinfo (aid) VALUES (?)";
        int result = JDBCUtils.insertData(sql, aid);
        return result;
    }

    /**
     * 查询我的用户信息
     *
     * @param aid
     * @return
     */
    @Override
    public List<UserInfo> selectMyUserInfo(Integer aid) {
        String sql = "SELECT uid,uname,usex,uage,uaddress,ustate,telephone,emergencycall,aid,permissions,deleted FROM userinfo WHERE aid=?";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, aid);
        return userInfoList;
    }

    /**
     * 修改我的用户信息
     *
     * @param uid
     * @param uname
     * @param usex
     * @param uage
     * @param uaddress
     * @return
     */
    @Override
    public int updateMyInfo(String uid, String uname, String usex, String uage, String uaddress) {
        String sql = "UPDATE userinfo SET uname=?,usex=?,uage=?,uaddress=? WHERE uid=?";
        int result = JDBCUtils.updateData(sql, uname, usex, uage, uaddress, uid);
        return result;
    }

    /**
     * 添加我的家属信息
     *
     * @param lname
     * @param lage
     * @param lsex
     * @param uid
     * @return
     */
    @Override
    public int addFamilyInfo(String lname, String lage, String lsex, Integer uid, String careuid, String lstate) {
        String sql = "INSERT INTO liveinfo (lname,lage,lsex,uid,careuid,lstate) VALUES (?,?,?,?,?,?)";
        int result = JDBCUtils.insertData(sql, lname, lage, lsex, uid, careuid, lstate);
        return result;
    }

    /**
     * 查询我的家属信息
     *
     * @param uid
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<LiveInfo> selectMyFamilyInfo(int uid, int start, int limit) {
        String sql = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,state.lstatename AS lstate,live.deleted AS deleted,user.uname AS uname" +
                ",live.careuid AS careuid,care.careuname AS careuname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " JOIN lstatename AS state ON live.lstate=state.lstate" +
                " JOIN careuser AS care ON live.careuid=care.careuid" +
                " WHERE live.uid=? AND live.deleted=0 AND user.deleted=0 LIMIT ?,? ";

        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sql, LiveInfo.class, uid, start, limit);
        return liveInfoList;
    }

    /**
     * 查询我的家属的数量
     *
     * @param uid
     * @return
     */
    @Override
    public int selectCountFamily(int uid) {
        String sql = "SELECT COUNT(*) FROM liveinfo WHERE uid=? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, uid);
        return count;
    }

    /**
     * 查询一条我的家属的信息
     *
     * @param lname
     * @param uid
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<LiveInfo> selectOneMyFamilyInfo(String lname, Integer uid, int start, int limit) {
        String sqls = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,state.lstatename AS lstate,live.deleted AS deleted,user.uname AS uname" +
                ",live.careuid AS careuid,care.careuname AS careuname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " JOIN lstatename AS state ON live.lstate=state.lstate" +
                " JOIN careuser AS care ON live.careuid=care.careuid" +
                " WHERE lname=? AND live.uid=? AND live.deleted=0 AND user.deleted=0 LIMIT ?,? ";
        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sqls, LiveInfo.class, lname, uid, start, limit);
        return liveInfoList;
    }

    /**
     * 1条家属数量
     *
     * @param lname
     * @param uid
     * @return
     */
    @Override
    public int selectCountFamily(String lname, Integer uid) {
        String sql = "SELECT COUNT(*) FROM liveinfo WHERE lname=? AND uid=? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, lname, uid);
        return count;
    }

    /**
     * 查询一条我的家属数量
     *
     * @param uid
     * @return
     */
    @Override
    public List<LiveInfo> selectOneMyFamilyInfo(Integer uid) {
        String sqls = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,state.lstatename AS lstate,live.deleted AS deleted,user.uname AS uname" +
                ",live.careuid AS careuid,care.careuname AS careuname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " JOIN lstatename AS state ON live.lstate=state.lstate" +
                " JOIN careuser AS care ON live.careuid=care.careuid" +
                " WHERE live.uid=? AND live.deleted=0 AND user.deleted=0";
        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sqls, LiveInfo.class, uid);
        return liveInfoList;
    }

    @Override
    public int updatePassword(Integer aid, String password) {
        String sql = "UPDATE account SET password=? WHERE aid=?";
        int result = JDBCUtils.updateData(sql, password, aid);
        return result;
    }

    @Override
    public List<Account> selectOnAccountInfoByUserAccount(String useraccount) {
        String sql = "SELECT aid,useraccount,password,permissions,astate,deleted " +
                "FROM account WHERE useraccount=? AND deleted=0";
        List<Account> accountList = JDBCUtils.selectData(sql, Account.class, useraccount);
        return accountList;
    }


}
