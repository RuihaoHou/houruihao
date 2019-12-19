package com.cheng.oa.service;

import com.cheng.oa.domain.TbJob;
import com.cheng.oa.vo.PageBean;

public interface JobService {
    PageBean<TbJob> showJob(Integer pageNumber, Integer pageSize, String name);

    boolean addJob(TbJob job);

    boolean deleteById(Integer id);

    boolean deleteBybatch(Integer[] ids);

    TbJob findJobById(Integer id);

    boolean udpateJob(TbJob job);

}
