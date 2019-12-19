package com.cheng.oa.mapper;

import com.cheng.oa.domain.TbSign;
import com.cheng.oa.domain.TbSignExample;
import com.cheng.oa.vo.SingChart;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSignMapper {
	long countByExample(TbSignExample example);

	int deleteByExample(TbSignExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbSign record);

	int insertSelective(TbSign record);

	List<TbSign> selectByExample(TbSignExample example);

	TbSign selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbSign record, @Param("example") TbSignExample example);

	int updateByExample(@Param("record") TbSign record, @Param("example") TbSignExample example);

	int updateByPrimaryKeySelective(TbSign record);

	int updateByPrimaryKey(TbSign record);

	List<TbSign> selectPageBean(HashMap<String, Object> map);

	List<TbSign> selectPageBeanByTime(HashMap<String, Object> map);

	Integer selectPageBeanByTimeCount(HashMap<String, Object> map);

	Integer selectPageBeanCount(HashMap<String, Object> map);
	
	List<SingChart> findSignChart(String createTime);

}