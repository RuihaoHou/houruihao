package com.cheng.oa.service;

import com.cheng.oa.domain.TbAdvice;
import com.cheng.oa.vo.PageBean;

public interface NoticeService {

    PageBean<TbAdvice> showAll(Integer pageNum, Integer pageSize, String title);

    boolean addNotice(TbAdvice advice, Integer uid);

    TbAdvice selectById(Integer id);

    boolean updateNotice(TbAdvice advice);

    boolean deleteById(Integer id);

    boolean deleteByIdBatch(Integer[] id);

}
