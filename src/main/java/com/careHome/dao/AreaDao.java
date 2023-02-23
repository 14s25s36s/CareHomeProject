package com.careHome.dao;

import com.careHome.pojo.Area;
import com.careHome.pojo.City;
import com.careHome.pojo.Province;

import java.util.List;

public interface AreaDao {
    public List<Province> selectProvinces();

    public List<City> selectCitiesByProvinceid(Integer provinceid);

    public List<Area> selectAreasByCityid(Integer cityid);

    public List<Province> selectOneProvince(Integer provinceid);

    public List<City> selectOneCity(Integer cityid);

    public List<Area> selectOneArea(Integer areaid);

    public List<Province> selectOneProvinceByProvince(String province);

    public List<City> selectOneCityByCity(String city);

    public List<Area> selectOneAreaByArea(String area);
}
