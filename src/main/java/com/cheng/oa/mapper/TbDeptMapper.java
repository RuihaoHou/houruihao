package com.cheng.oa.mapper;

import com.cheng.oa.domain.TbDept;
import com.cheng.oa.domain.TbDeptExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDeptMapper {
	long countByExample(TbDeptExample example);

	int deleteByExample(TbDeptExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbDept record);

	int insertSelective(TbDept record);

	List<TbDept> selectByExample(TbDeptExample example);

	TbDept selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbDept record, @Param("example") TbDeptExample example);

	int updateByExample(@Param("record") TbDept record, @Param("example") TbDeptExample example);

	int updateByPrimaryKeySelective(TbDept record);

	int updateByPrimaryKey(TbDept record);

	List<TbDept> selectPage(HashMap<String, Integer> map);

	List<TbDept> selectPageByName(HashMap<String, Object> map);
	
	Integer selectLikeCount(String name);

}