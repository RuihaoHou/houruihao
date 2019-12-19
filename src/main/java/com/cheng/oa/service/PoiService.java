package com.cheng.oa.service;

import javax.servlet.http.HttpServletRequest;

public interface PoiService {

    String createExcel(String username, HttpServletRequest request);

}
