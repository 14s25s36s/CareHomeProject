package com.careHome.dao;

import com.careHome.pojo.LStateName;

import java.util.List;

public interface StateDao {
    List<LStateName> selectAllStateInfo(int start, int limit);

    int selectCountStateHouse();

    List<LStateName> selectOneStateInfo(String checktext, int start, int limit);

    int selectCountState(String checktext);

    int LiveExist(String lstate);

    int deleteState(String id);

    int addState(String lstate, String lstatename);

    int updateState(String id, String lstate, String lstatename);
}
