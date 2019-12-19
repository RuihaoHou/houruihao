package com.cheng.oa.service;

import com.cheng.oa.domain.TbDoc;
import com.cheng.oa.vo.PageBean;

public interface DocumentService {

    PageBean<TbDoc> show(Integer pageNumber, Integer pageSize, String title);

    boolean saveDoc(TbDoc doc);

    boolean delete(Integer id);

    boolean deleteBybatch(Integer[] ids);
}
