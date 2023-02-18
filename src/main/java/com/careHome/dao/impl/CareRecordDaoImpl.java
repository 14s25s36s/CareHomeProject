package com.careHome.dao.impl;

import com.careHome.dao.CareRecordDao;
import com.careHome.pojo.CareRecode;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;
import com.careHome.utils.JDBCUtils;

import java.util.List;

public class CareRecordDaoImpl implements CareRecordDao {
    /**
     * 查询全部护理记录
     *
     * @param uid
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<CareRecode> selectAllCareRecord(Integer uid, int start, int limit) {
        String sql = "SELECT care.careid AS careid,care.uid AS uid,user.uname AS uname,care.lid AS lid," +
                "live.lname AS lname,care.caredate AS caredate,care.careinfo AS careinfo,care.deleted AS deleted " +
                "FROM care_record AS care JOIN userinfo AS user ON care.uid=user.uid JOIN liveinfo AS live " +
                "ON care.lid=live.lid WHERE care.uid=? AND care.deleted=0 LIMIT ?,?";
        List<CareRecode> careRecodeList = JDBCUtils.selectData(sql, CareRecode.class, uid, start, limit);
        return careRecodeList;
    }

    /**
     * 查询所有护理记录的数量
     *
     * @param uid
     * @return
     */
    @Override
    public int selectCountCareRecord(Integer uid) {
        String sql = "SELECT COUNT(*) FROM care_record WHERE uid=? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, uid);
        return count;
    }

    /**
     * 查询一位住户护理记录
     *
     * @param lid
     * @param uid
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<CareRecode> selectOneCareRecord(Integer lid, Integer uid, int start, int limit) {
        String sql = "SELECT care.careid AS careid,care.uid AS uid,user.uname AS uname,care.lid AS lid," +
                "live.lname AS lname,care.caredate AS caredate,care.careinfo AS careinfo,care.deleted AS deleted " +
                "FROM care_record AS care JOIN userinfo AS user ON care.uid=user.uid JOIN liveinfo AS live " +
                "ON care.lid=live.lid WHERE care.uid=? AND care.lid=? AND care.deleted=0 LIMIT ?,?";
        List<CareRecode> careRecodeList = JDBCUtils.selectData(sql, CareRecode.class, uid, lid, start, limit);
        return careRecodeList;
    }

    /**
     * 查询一位住户拥有多少条护理记录
     *
     * @param lid
     * @param uid
     * @return
     */
    @Override
    public int selectCountCareRecord(Integer lid, Integer uid) {
        String sql = "SELECT COUNT(*) FROM care_record WHERE uid=? AND lid=? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, uid, lid);
        return count;
    }

    /**
     * 删除护理记录
     *
     * @param careid
     * @return
     */
    @Override
    public int deletedOneCareRecord(String careid) {
        String sql = "UPDATE care_record SET deleted = 1 WHERE careid = ?";
        int result = JDBCUtils.deleteData(sql, careid);
        return result;
    }

    /**
     * 查询一条护理记录
     *
     * @param careid
     * @return
     */
    @Override
    public List<CareRecode> selectOneCareRecordByCareid(String careid) {
        String sql = "SELECT care.careid AS careid,care.uid AS uid,user.uname AS uname,care.lid AS lid," +
                "live.lname AS lname,care.caredate AS caredate,care.careinfo AS careinfo,care.deleted AS deleted " +
                "FROM care_record AS care JOIN userinfo AS user ON care.uid=user.uid JOIN liveinfo AS live " +
                "ON care.lid=live.lid WHERE care.careid=? AND care.deleted=0";
        List<CareRecode> careRecodeList = JDBCUtils.selectData(sql, CareRecode.class, careid);
        return careRecodeList;
    }

    /**
     * 查询一位住户信息
     *
     * @param lname
     * @return
     */
    @Override
    public List<LiveInfo> selectOneLiveLidByLname(String lname) {
        String sql = "SELECT live.lid AS lid,live.lname AS lname,live.lage AS lage,live.lsex AS lsex" +
                ",live.uid AS uid,live.lstate AS lstate,live.deleted AS deleted,user.uname AS uname" +
                " FROM liveinfo AS live JOIN userinfo AS user ON live.uid=user.uid" +
                " WHERE live.lname=? AND live.deleted=0";
        List<LiveInfo> liveInfoList = JDBCUtils.selectData(sql, LiveInfo.class, lname);
        return liveInfoList;
    }

    /**
     * 通过护工名字查出护工xinxi
     *
     * @param uname
     * @return
     */
    @Override
    public List<UserInfo> selectOneUserUidByUname(String uname) {
        String sql = "SELECT uid,uname,usex,uage,uaddress,ustate,aid,permissions,deleted" +
                " FROM userinfo WHERE uname=? AND deleted=0";
        List<UserInfo> userInfoList = JDBCUtils.selectData(sql, UserInfo.class, uname);
        return userInfoList;
    }

    /**
     * 修改护理记录信息
     *
     * @param careid
     * @param lid
     * @param uid
     * @param careinfo
     * @return
     */
    @Override
    public int updateCareRecord(String careid, Integer lid, Integer uid, String careinfo) {
        String sql = "UPDATE care_record SET lid=?,uid=?,careinfo=? WHERE careid=?";
        int result = JDBCUtils.updateData(sql, lid, uid, careinfo, careid);
        return result;
    }

    /**
     * 添加护理记录
     *
     * @param lid
     * @param uid
     * @param careinfo
     * @return
     */
    @Override
    public int addCareRecord(Integer lid, Integer uid, String careinfo) {
        String sql = "INSERT INTO care_record (lid,uid,careinfo) VALUES (?,?,?)";
        int result = JDBCUtils.insertData(sql, lid, uid, careinfo);
        return result;
    }
}
