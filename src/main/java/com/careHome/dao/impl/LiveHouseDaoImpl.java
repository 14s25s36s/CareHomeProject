package com.careHome.dao.impl;

import com.careHome.dao.LiveHouseDao;
import com.careHome.pojo.Care;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.Lstate;
import com.careHome.utils.JDBCUtils;

import java.util.List;

public class LiveHouseDaoImpl implements LiveHouseDao {
    /**
     * 查询所有的住户信息
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<LiveInfo> selectAllLiveInfo(int page, int limit) {
        String sql = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,state.lstatename AS lstate,live.deleted AS deleted,user.uname AS uname" +
                ",live.careuid AS careuid,care.careuname AS careuname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " JOIN lstatename AS state ON live.lstate=state.lstate" +
                " JOIN careuser AS care ON live.careuid=care.careuid" +
                " WHERE live.deleted=0 AND user.deleted=0 LIMIT ?,? ";

        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sql, LiveInfo.class, page, limit);
        return liveInfoList;
    }

    /**
     * 查询所有的住户数量
     *
     * @return
     */
    @Override
    public int selectCountLiveHouse() {
        String sql = "SELECT COUNT(*) FROM liveinfo WHERE deleted=0";
        int count = JDBCUtils.getInt(sql);
        return count;
    }

    /**
     * 模糊查询某姓名条件下住户的信息数量
     *
     * @param checktext
     * @return
     */
    @Override
    public int selectCountLiveHouse(String checktext) {
        String checktexts = "%" + checktext + "%";
        String sql = "SELECT COUNT(*) FROM liveinfo WHERE lname LIKE ? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, checktexts);
        return count;
    }

    /**
     * 模糊查询某一姓名条件下住户的信息
     *
     * @param checktext
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<LiveInfo> selectOneLiveHouseInfo(String checktext, int page, int limit) {
        checktext = "%" + checktext + "%";
        String sqls = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,state.lstatename AS lstate,live.deleted AS deleted,user.uname AS uname" +
                ",live.careuid AS careuid,care.careuname AS careuname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " JOIN lstatename AS state ON live.lstate=state.lstate" +
                " JOIN careuser AS care ON live.careuid=care.careuid" +
                " WHERE live.lname LIKE ? AND live.deleted=0 LIMIT ?,?";
        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sqls, LiveInfo.class, checktext, page, limit);
        return liveInfoList;
    }

    /**
     * 通过id查询一条住户信息
     *
     * @param lid
     * @return
     */
    @Override
    public List<LiveInfo> selectOneLiveInfo(String lid) {
        String sql = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,state.lstatename AS lstate,live.deleted AS deleted,user.uname AS uname" +
                ",live.careuid AS careuid,care.careuname AS careuname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " JOIN lstatename AS state ON live.lstate=state.lstate" +
                " JOIN careuser AS care ON live.careuid=care.careuid" +
                " WHERE live.lid=? AND live.deleted=0";
        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sql, LiveInfo.class, lid);
        return liveInfoList;
    }

    /**
     * 删除一条住户信息
     *
     * @param lid
     * @return
     */
    @Override
    public int deletedOneLiveInfo(String lid) {
        String sql = "UPDATE liveinfo SET deleted = 1 WHERE lid = ?";
        int result = JDBCUtils.deleteData(sql, lid);
        return result;
    }

    /**
     * 修改某一个住户信息
     *
     * @param lname
     * @param lage
     * @param lsex
     * @param lid
     * @return
     */
    @Override
    public int updateLiveInfo(String lname, String lage, String lsex, String careuid, String lstate, String lid) {
        String sql = "UPDATE liveinfo SET lname=?,lage=?,lsex=?,careuid=?,lstate=? WHERE lid=?";
        int result = JDBCUtils.updateData(sql, lname, lage, lsex, careuid, lstate, lid);
        return result;
    }

    /**
     * 添加一位住户信息
     *
     * @param lname
     * @param lage
     * @param lsex
     * @param uid
     * @return
     */
    @Override
    public int addUserInfo(String lname, String lage, String lsex, String uid, String careuid, String lstate) {
        String sql = "INSERT INTO liveinfo (lname,lage,lsex,uid,careuid,lstate) VALUES (?,?,?,?,?,?)";
        int result = JDBCUtils.insertData(sql, lname, lage, lsex, uid, careuid, lstate);
        return result;
    }

    @Override
    public List<Care> getCareList() {
        String sql = "SELECT id,careuid,careuname,deleted FROM careuser";
        List<Care> careList = JDBCUtils.selectData(sql, Care.class);
        return careList;
    }

    @Override
    public List<Lstate> getStateList() {
        String sql = "SELECT id,lstate,lstatename,deleted FROM lstatename";
        List<Lstate> lstateList = JDBCUtils.selectData(sql, Lstate.class);
        return lstateList;
    }
}
