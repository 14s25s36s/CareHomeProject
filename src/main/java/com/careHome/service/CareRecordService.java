package com.careHome.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CareRecordService {
    public void careInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
