package com.cheng.oa.service;

import com.cheng.oa.domain.TbDept;
import com.cheng.oa.vo.PageBean;

public interface DeptService {

    PageBean<TbDept> showDept(Integer pageNumber, Integer pageSize, String dname);

    boolean addDept(TbDept dept);

    boolean deleteById(Integer id);

    boolean deleteBybatch(Integer[] ids);

    TbDept findDeptById(Integer id);

    boolean updateDept(TbDept dept);
}
