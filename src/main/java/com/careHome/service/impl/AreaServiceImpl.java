package com.careHome.service.impl;

import com.alibaba.fastjson2.JSON;
import com.careHome.dao.AreaDao;
import com.careHome.dao.impl.AreaDaoImpl;
import com.careHome.pojo.Area;
import com.careHome.pojo.City;
import com.careHome.pojo.Province;
import com.careHome.service.AreaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AreaServiceImpl implements AreaService {
    AreaDao areaDao = new AreaDaoImpl();

    @Override
    public void selectProvinces(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Province> provinceList = areaDao.selectProvinces();
        String json = JSON.toJSONString(provinceList);
        resp.getWriter().write(json);
    }

    @Override
    public void selectCitiesByProvinceid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer provinceid = Integer.parseInt(req.getParameter("provinceid"));
        List<City> cityList = areaDao.selectCitiesByProvinceid(provinceid);
        String json = JSON.toJSONString(cityList);
        resp.getWriter().write(json);
    }

    @Override
    public void selectAreasByCityid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer cityid = Integer.parseInt(req.getParameter("cityid"));
        List<Area> areaList = areaDao.selectAreasByCityid(cityid);
        String json = JSON.toJSONString(areaList);
        resp.getWriter().write(json);
    }
}
