package com.cheng.oa.mapper;

import com.cheng.oa.domain.TbAdvice;
import com.cheng.oa.domain.TbAdviceExample;
import com.cheng.oa.vo.PageBean;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAdviceMapper {
	long countByExample(TbAdviceExample example);

	int deleteByExample(TbAdviceExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbAdvice record);

	int insertSelective(TbAdvice record);

	List<TbAdvice> selectByExampleWithBLOBs(TbAdviceExample example);

	List<TbAdvice> selectByExample(TbAdviceExample example);

	TbAdvice selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbAdvice record, @Param("example") TbAdviceExample example);

	int updateByExampleWithBLOBs(@Param("record") TbAdvice record, @Param("example") TbAdviceExample example);

	int updateByExample(@Param("record") TbAdvice record, @Param("example") TbAdviceExample example);

	int updateByPrimaryKeySelective(TbAdvice record);

	int updateByPrimaryKeyWithBLOBs(TbAdvice record);

	int updateByPrimaryKey(TbAdvice record);

	List<TbAdvice> selectPageBean(HashMap<String, Object> map);

	List<TbAdvice> selectPageBeantitle(HashMap<String, Object> map);

	int insertByUid(HashMap<String, Object> map);

}