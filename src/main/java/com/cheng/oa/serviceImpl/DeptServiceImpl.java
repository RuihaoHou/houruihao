package com.cheng.oa.serviceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheng.oa.domain.TbDept;
import com.cheng.oa.domain.TbDeptExample;
import com.cheng.oa.domain.TbJob;
import com.cheng.oa.domain.TbJobExample;
import com.cheng.oa.domain.TbUserExample;
import com.cheng.oa.mapper.TbDeptMapper;
import com.cheng.oa.service.DeptService;
import com.cheng.oa.vo.PageBean;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private TbDeptMapper mapper;

	@Override
	public PageBean<TbDept> showDept(Integer pageNumber, Integer pageSize, String dname) {
		System.out.println(dname);
		
		if (!dname.equals("")) {
			// select * from tb_dept where name=#{name} limit #{rowStart},#{rowEnd}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", "%"+dname+"%");
			int rowStart = (pageNumber - 1) * pageSize;
			map.put("rowStart", rowStart);
			map.put("rowEnd", pageSize);
			List<TbDept> tbDepts = mapper.selectPageByName(map);
			PageBean<TbDept> pageBean = new PageBean<TbDept>();
			pageBean.setRows(tbDepts);
			Integer total = mapper.selectLikeCount("%"+dname+"%");
			System.out.println(total);
			pageBean.setTotal(total);
			return pageBean;
		} else {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			int rowStart = (pageNumber - 1) * pageSize;
			map.put("rowStart", rowStart);
			map.put("rowEnd", pageSize);
			List<TbDept> tbDepts = mapper.selectPage(map);
			PageBean<TbDept> pageBean = new PageBean<TbDept>();
			pageBean.setRows(tbDepts);
			TbDeptExample example = new TbDeptExample();
			List<TbDept> tball = mapper.selectByExample(example);
			pageBean.setTotal(tball.size());
			return pageBean;
		}
	}

	@Override
	public boolean addDept(TbDept dept) {
		return mapper.insertSelective(dept) > 0;

	}

	@Override
	public boolean deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public boolean deleteBybatch(Integer[] ids) {
		TbDeptExample example = new TbDeptExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		return mapper.deleteByExample(example) > 0;
	}

	@Override
	public TbDept findDeptById(Integer id) {
		TbDept selectByPrimaryKey = mapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	@Override
	public boolean updateDept(TbDept dept) {
		TbDeptExample example = new TbDeptExample();
		example.createCriteria().andIdEqualTo(dept.getId());
		return mapper.updateByExampleSelective(dept, example) > 0;

	}

}
