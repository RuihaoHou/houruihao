package com.cheng.oa.service;

import java.util.List;

import com.cheng.oa.domain.TbSign;
import com.cheng.oa.vo.PageBean;
import com.cheng.oa.vo.SingChart;

public interface SignService {

    PageBean<TbSign> showAll(Integer pageNum, Integer pageSize, String startDate, String endDate);

    List<SingChart> findSignCharts(String beignday);
}
