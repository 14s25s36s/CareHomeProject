package com.careHome.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AreaService {
    public void selectProvinces(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void selectCitiesByProvinceid(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void selectAreasByCityid(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
