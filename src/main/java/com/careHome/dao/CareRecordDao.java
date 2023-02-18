package com.careHome.dao;

import com.careHome.pojo.CareRecode;

import java.util.List;

public interface CareRecordDao {
    public List<CareRecode> selectAllCareRecord(Integer uid, int start, int limit);

    public int selectCountCareRecord(Integer uid);

    public List<CareRecode> selectOneCareRecode(String checktext, Integer uid, int start, int limit);

    public int selectCountCareRecord(String checktext, Integer uid);
}
