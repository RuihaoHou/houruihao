package com.cheng.oa.mapper;

import com.cheng.oa.domain.TbDoc;
import com.cheng.oa.domain.TbDocExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDocMapper {
	long countByExample(TbDocExample example);

	int deleteByExample(TbDocExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbDoc record);

	int insertSelective(TbDoc record);

	List<TbDoc> selectByExampleWithBLOBs(TbDocExample example);

	List<TbDoc> selectByExample(TbDocExample example);

	TbDoc selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbDoc record, @Param("example") TbDocExample example);

	int updateByExampleWithBLOBs(@Param("record") TbDoc record, @Param("example") TbDocExample example);

	int updateByExample(@Param("record") TbDoc record, @Param("example") TbDocExample example);

	int updateByPrimaryKeySelective(TbDoc record);

	int updateByPrimaryKeyWithBLOBs(TbDoc record);

	int updateByPrimaryKey(TbDoc record);

	List<TbDoc> selectPageBean(HashMap<String, Integer> map);

	List<TbDoc> selectPageBeanByTitle(HashMap<String, Object> map);
}