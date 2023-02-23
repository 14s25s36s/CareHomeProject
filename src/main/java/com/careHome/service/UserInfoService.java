package com.careHome.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserInfoService {

    public void userInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void deleteuserinfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void toUpdateUserInfo(HttpServletRequest req, HttpServletResponse resp);

    public void updateUserInfo(HttpServletRequest req, HttpServletResponse resp);

    public void addUserInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void updateUserState(HttpServletRequest req, HttpServletResponse resp) throws IOException;

}
