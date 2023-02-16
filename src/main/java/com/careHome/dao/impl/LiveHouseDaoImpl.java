package com.careHome.dao.impl;

import com.careHome.dao.LiveHouseDao;
import com.careHome.pojo.LiveInfo;
import com.careHome.utils.JDBCUtils;

import java.util.List;

public class LiveHouseDaoImpl implements LiveHouseDao {
    @Override
    public List<LiveInfo> selectAllLiveInfo(int page, int limit) {
        String sql = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,live.lstate AS lstate,live.deleted AS deleted,user.uname AS uname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " WHERE live.deleted=0 AND user.deleted=0 LIMIT ?,? ";

        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sql, LiveInfo.class, page, limit);
        return liveInfoList;
    }

    @Override
    public int selectCountLiveHouse() {
        String sql = "SELECT COUNT(*) FROM liveinfo WHERE deleted=0";
        int count = JDBCUtils.getInt(sql);
        return count;
    }

    @Override
    public int selectCountLiveHouse(String checktext) {
        String checktexts = "%" + checktext + "%";
        String sql = "SELECT COUNT(*) FROM liveinfo WHERE lname LIKE ? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, checktexts);
        return count;
    }

    @Override
    public List<LiveInfo> selectOneLiveHouseInfo(String checktext, int page, int limit) {
        checktext = "%" + checktext + "%";
        String sqls = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,live.lstate AS lstate,live.deleted AS deleted,user.uname AS uname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " WHERE live.lname LIKE ? AND live.deleted=0 LIMIT ?,?";
        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sqls, LiveInfo.class, checktext, page, limit);
        return liveInfoList;
    }

    @Override
    public List<LiveInfo> selectOneLiveInfo(String lid) {
        String sql = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,live.lstate AS lstate,live.deleted AS deleted,user.uname AS uname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " WHERE live.lid=? AND live.deleted=0";
        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sql, LiveInfo.class, lid);
        return liveInfoList;
    }

    @Override
    public int deletedOneLiveInfo(String lid) {
        String sql = "UPDATE liveinfo SET deleted = 1 WHERE lid = ?";
        int result = JDBCUtils.deleteData(sql, lid);
        return result;
    }

    @Override
    public int updateLiveInfo(String lname, String lage, String lsex, String lstate, String lid) {
        String sql = "UPDATE liveinfo SET lname=?,lage=?,lsex=?,lstate=? WHERE lid=?";
        int result = JDBCUtils.updateData(sql, lname, lage, lsex, lstate, lid);
        return result;
    }

    @Override
    public int addUserInfo(String lname, String lage, String lsex, String uid) {
        String sql = "INSERT INTO liveinfo (lname,lage,lsex,uid) VALUES (?,?,?,?)";
        int result = JDBCUtils.insertData(sql, lname, lage, lsex, uid);
        return result;
    }


}
