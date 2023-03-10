package com.careHome.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginService {
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void checkExist(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void getVerifyCode(HttpServletRequest req, HttpServletResponse resp);

    public void judgeCode(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
