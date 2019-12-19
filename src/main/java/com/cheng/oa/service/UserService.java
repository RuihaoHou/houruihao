package com.cheng.oa.service;

import com.cheng.oa.domain.TbUser;
import com.cheng.oa.vo.PageBean;

public interface UserService {

    /**
     * 验证登录
     * 
     * @param user
     * @return
     */
    TbUser login(TbUser user);

    /**
     * 展示所有
     * 
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageBean<TbUser> showAll(Integer pageNumber, Integer pageSize);

    PageBean<TbUser> select(String username, String status);

    /**
     * 开始添加数据
     * 
     * @param user
     * @return
     */
    boolean addUser(TbUser user);

    TbUser findUserById(Integer id);

    boolean updateUser(TbUser user);

    boolean deleteById(Integer id);

    boolean deleteBybatch(Integer[] ids);

}
