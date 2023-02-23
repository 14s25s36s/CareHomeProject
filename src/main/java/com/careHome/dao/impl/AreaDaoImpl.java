package com.careHome.dao.impl;

import com.careHome.dao.AreaDao;
import com.careHome.pojo.Area;
import com.careHome.pojo.City;
import com.careHome.pojo.Province;
import com.careHome.utils.JDBCUtils;

import java.util.List;

public class AreaDaoImpl implements AreaDao {
    @Override
    public List<Province> selectProvinces() {
        String sql = "SELECT id,provinceid,province FROM provinces";
        List<Province> provinceList = JDBCUtils.selectData(sql, Province.class);
        return provinceList;
    }

    @Override
    public List<City> selectCitiesByProvinceid(Integer provinceid) {
        String sql = "SELECT id,cityid,city,provinceid FROM cities WHERE provinceid=?";
        List<City> cityList = JDBCUtils.selectData(sql, City.class, provinceid);
        return cityList;
    }

    @Override
    public List<Area> selectAreasByCityid(Integer cityid) {
        String sql = "SELECT id,areaid,area,cityid FROM areas WHERE cityid=?";
        List<Area> areaList = JDBCUtils.selectData(sql, Area.class, cityid);
        return areaList;
    }

    @Override
    public List<Province> selectOneProvince(Integer provinceid) {
        String sql = "SELECT id,provinceid,province FROM provinces WHERE provinceid=?";
        List<Province> provinceList = JDBCUtils.selectData(sql, Province.class, provinceid);
        return provinceList;
    }

    @Override
    public List<City> selectOneCity(Integer cityid) {
        String sql = "SELECT id,cityid,city,provinceid FROM cities WHERE cityid=?";
        List<City> cityList = JDBCUtils.selectData(sql, City.class, cityid);
        return cityList;
    }

    @Override
    public List<Area> selectOneArea(Integer areaid) {
        String sql = "SELECT id,areaid,area,cityid FROM areas WHERE areaid=?";
        List<Area> areaList = JDBCUtils.selectData(sql, Area.class, areaid);
        return areaList;
    }

    @Override
    public List<Province> selectOneProvinceByProvince(String province) {
        String sql = "SELECT id,provinceid,province FROM provinces WHERE province=?";
        List<Province> provinceList = JDBCUtils.selectData(sql, Province.class, province);
        return provinceList;
    }

    @Override
    public List<City> selectOneCityByCity(String city) {
        String sql = "SELECT id,cityid,city,provinceid FROM cities WHERE city=?";
        List<City> cityList = JDBCUtils.selectData(sql, City.class, city);
        return cityList;
    }

    @Override
    public List<Area> selectOneAreaByArea(String area) {
        String sql = "SELECT id,areaid,area,cityid FROM areas WHERE area=?";
        List<Area> areaList = JDBCUtils.selectData(sql, Area.class, area);
        return areaList;
    }
}
