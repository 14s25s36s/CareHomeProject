package com.careHome.dao.impl;

import com.careHome.dao.StateDao;
import com.careHome.pojo.LStateName;
import com.careHome.utils.JDBCUtils;

import java.util.List;

public class StateDaoImpl implements StateDao {
    @Override
    public List<LStateName> selectAllStateInfo(int start, int limit) {
        String sql = "SELECT * FROM lstatename WHERE deleted=0 LIMIT ?,?";
        List<LStateName> stateList = JDBCUtils.selectData(sql, LStateName.class, start, limit);
        return stateList;
    }

    @Override
    public int selectCountStateHouse() {
        String sql = "SELECT COUNT(*) FROM lstatename WHERE deleted=0";
        int count = JDBCUtils.getInt(sql);
        return count;
    }

    @Override
    public List<LStateName> selectOneStateInfo(String checktext, int start, int limit) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT * FROM lstatename WHERE lstatename LIKE ? AND deleted=0 LIMIT ?,?";
        List<LStateName> stateList = JDBCUtils.selectData(sql, LStateName.class, checktext, start, limit);
        return stateList;
    }

    @Override
    public int selectCountState(String checktext) {
        checktext = "%" + checktext + "%";
        String sql = "SELECT COUNT(*) FROM lstatename WHERE lstatename LIKE ? AND deleted=0";
        int count = JDBCUtils.getPreparedInt(sql, checktext);
        return count;
    }

    @Override
    public int deleteState(String id) {
        String sql = "UPDATE lstatename SET deleted=1 WHERE id=?";
        int result = JDBCUtils.updateData(sql, id);
        return result;
    }

    @Override
    public int addState(String lstate, String lstatename) {
        String sql = "INSERT INTO lstatename (lstate,lstatename) VALUES (?,?)";
        int result = JDBCUtils.insertData(sql, lstate, lstatename);
        return result;
    }

    @Override
    public int LiveExist(String lstate) {
        String sql = "SELECT COUNT(*) FROM liveinfo WHERE lstate=? AND deleted=0 LIMIT 1";
        int count = JDBCUtils.getPreparedInt(sql, lstate);
        return count;
    }

    @Override
    public int updateState(String id, String lstate, String lstatename) {
        String sql = "UPDATE lstatename SET lstate=?,lstatename=? WHERE id=?";
        int result = JDBCUtils.updateData(sql, lstate, lstatename, id);
        return result;
    }
}
