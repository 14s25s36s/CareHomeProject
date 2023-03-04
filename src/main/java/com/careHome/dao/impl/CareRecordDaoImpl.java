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
                "live.lname AS lname,care.caredate AS caredate,care.lasteditdate AS lasteditdate," +
                "care.careinfo AS careinfo,care.deleted AS deleted " +
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
     * @param lname
     * @param uid
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<CareRecode> selectOneCareRecord(String lname, Integer uid, int start, int limit) {
        lname = "%" + lname + "%";
        String sql = "SELECT care.careid AS careid,care.uid AS uid,user.uname AS uname,care.lid AS lid," +
                "live.lname AS lname,care.caredate AS caredate,care.lasteditdate AS lasteditdate," +
                "care.careinfo AS careinfo,care.deleted AS deleted " +
                "FROM care_record AS care JOIN userinfo AS user ON care.uid=user.uid JOIN liveinfo AS live " +
                "ON care.lid=live.lid WHERE care.uid=? AND live.lname LIKE ? AND care.deleted=0 LIMIT ?,?";
        List<CareRecode> careRecodeList = JDBCUtils.selectData(sql, CareRecode.class, uid, lname, start, limit);
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
     * 修改护理记录信息
     *
     * @param careid
     * @param careinfo
     * @return
     */
    @Override
    public int updateCareRecord(String careid, String careinfo) {
        String sql = "UPDATE care_record SET careinfo=?,lasteditdate=now() WHERE careid=?";
        int result = JDBCUtils.updateData(sql, careinfo, careid);
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
    public int addCareRecord(String lid, Integer uid, String careinfo) {
        String sql = "INSERT INTO care_record (lid,uid,careinfo,lasteditdate) VALUES (?,?,?,now())";
        int result = JDBCUtils.insertData(sql, lid, uid, careinfo);
        return result;
    }
}
