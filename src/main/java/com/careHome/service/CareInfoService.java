package com.careHome.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CareInfoService {

    public void allCareInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void deleteCareInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void getLiveByCare(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
