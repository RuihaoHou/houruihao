package com.cheng.oa.mapper;

import com.cheng.oa.domain.TbDept;
import com.cheng.oa.domain.TbJob;
import com.cheng.oa.domain.TbJobExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbJobMapper {
    long countByExample(TbJobExample example);

    int deleteByExample(TbJobExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbJob record);

    int insertSelective(TbJob record);

    List<TbJob> selectByExample(TbJobExample example);

    TbJob selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbJob record, @Param("example") TbJobExample example);

    int updateByExample(@Param("record") TbJob record, @Param("example") TbJobExample example);

    int updateByPrimaryKeySelective(TbJob record);

    int updateByPrimaryKey(TbJob record);
    
    List<TbJob> selectPage(HashMap<String, Integer> map);

	List<TbJob> selectPageByName(HashMap<String, Object> map);
}