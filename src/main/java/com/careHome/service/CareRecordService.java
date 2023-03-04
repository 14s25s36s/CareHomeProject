package com.careHome.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CareRecordService {
    public void careInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void deleteCareRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void updateCareRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void addCareRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public void getLiveByCareid(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
