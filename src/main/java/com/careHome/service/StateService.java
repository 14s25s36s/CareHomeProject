package com.careHome.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface StateService {
    public void getStateList(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void deleteState(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void addState(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void updateState(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
