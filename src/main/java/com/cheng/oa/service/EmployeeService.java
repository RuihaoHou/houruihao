package com.cheng.oa.service;

import java.util.List;

import com.cheng.oa.domain.Message;
import com.cheng.oa.domain.TbDept;
import com.cheng.oa.domain.TbEmployee;
import com.cheng.oa.domain.TbJob;
import com.cheng.oa.vo.PageBean;

public interface EmployeeService {

    PageBean<TbEmployee> showEmployee(Integer pageNumber, Integer pageSize);

    boolean addEmployee(TbEmployee employee);

    boolean deleteById(Integer id);

    boolean deleteBybatch(Integer[] ids);

    TbEmployee findEmployeeById(Integer id);

    boolean udpateEmployee(TbEmployee employee);

    List<Message> showAll();

    List<TbJob> selectJob();

    List<TbDept> selectDept();

}
