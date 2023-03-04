package com.careHome.dao.impl;

import com.careHome.dao.CareInfoDao;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
import com.careHome.utils.JDBCUtils;

import java.util.List;

public class CareInfoDaoImpl implements CareInfoDao {
    /**
     * 查询所有的护工信息
     *
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<UserInfo> selectAllCareInfo(int start, int limit) {
        String sql = "SELECT uid,uname,usex,uage,telephone,emergencycall,uaddress,ustate,aid,permissions,deleted " +
                "FROM userinfo WHERE permissions=1 and deleted=0 LIMIT ?,?";
        List<UserInfo> careInfoList = JDBCUtils.selectData(sql, UserInfo.class, start, limit);
        return careInfoList;
    }

    /**
     * 查询所有护工的数量
     *
     * @return
     */
    @Override
    public int selectCountCareInfo() {
        String sql = "SELECT COUNT(*) FROM userinfo WHERE permissions=1 AND deleted=0";
        int count = JDBCUtils.getInt(sql);
        return count;
    }

    /**
     * 用名字模糊查询护工的信息
     *
     * @param checktext
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<UserInfo> selectOneCareInfo(String checktext, int start, int limit) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT uid,uname,usex,uage,telephone,emergencycall,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE uname LIKE ? AND permissions=1 AND deleted=0 LIMIT ?,? ";
        List<UserInfo> careInfoList = JDBCUtils.selectData(sql, UserInfo.class, checktext, start, limit);
        return careInfoList;
    }

    /**
     * 用名字模糊查询该名护工的数量
     *
     * @param checktext
     * @return
     */
    @Override
    public int selectCountCareInfo(String checktext) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT COUNT(*) FROM userinfo WHERE uname LIKE ? AND permissions=1 AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, checktext);
        return count;
    }

    /**
     * 删除护工
     *
     * @param uid
     * @return
     */
    @Override
    public int deletedOneCareInfo(String uid) {
        String sql = "UPDATE userinfo SET deleted=1 WHERE uid=? AND permissions=1";
        int result = JDBCUtils.deleteData(sql, uid);
        return result;
    }

    @Override
    public List<LiveInfo> getLiveInfoByCare(String careuid) {
        String sql = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,state.lstatename AS lstate,live.deleted AS deleted,user.uname AS uname" +
                ",live.careuid AS careuid,care.careuname AS careuname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " JOIN lstatename AS state ON live.lstate=state.lstate" +
                " JOIN careuser AS care ON live.careuid=care.careuid" +
                " WHERE live.deleted=0 AND user.deleted=0 AND live.careuid=?";
        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sql, LiveInfo.class, careuid);
        return liveInfoList;
    }

}
