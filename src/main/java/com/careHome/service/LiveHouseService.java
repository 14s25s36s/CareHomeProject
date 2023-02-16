package com.careHome.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LiveHouseService {
    public void hasLive(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    public void deleteLiveInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void toUpdateLiveInfo(HttpServletRequest req, HttpServletResponse resp);

    public void updateLiveInfo(HttpServletRequest req, HttpServletResponse resp);

    public void addLiveInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
