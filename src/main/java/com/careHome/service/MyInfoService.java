package com.careHome.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyInfoService {

    public void updateMyInfo(HttpServletRequest req, HttpServletResponse resp);

    public void addFamilyInfo(HttpServletRequest req, HttpServletResponse resp);

    public void myFamilyInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void myFamilyName(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
