package com.cheng.oa.serviceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheng.oa.domain.Message;
import com.cheng.oa.domain.TbDept;
import com.cheng.oa.domain.TbDeptExample;
import com.cheng.oa.domain.TbEmployee;
import com.cheng.oa.domain.TbEmployeeExample;
import com.cheng.oa.domain.TbJob;
import com.cheng.oa.domain.TbJobExample;
import com.cheng.oa.mapper.TbDeptMapper;
import com.cheng.oa.mapper.TbEmployeeMapper;
import com.cheng.oa.mapper.TbJobMapper;
import com.cheng.oa.service.EmployeeService;
import com.cheng.oa.vo.PageBean;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private TbEmployeeMapper mapper;

	@Autowired
	private TbJobMapper Jobmapper;

	@Autowired
	private TbDeptMapper Deptmapper;

	@Override
	public PageBean<TbEmployee> showEmployee(Integer pageNumber, Integer pageSize) {

		HashMap<String, Integer> map = new HashMap<>();
		map.put("startRow", (pageNumber - 1) * pageSize);
		map.put("pageSize", pageSize);
		List<TbEmployee> lists = mapper.selectAllByPage(map);

		for (TbEmployee tbEmployee : lists) {
			tbEmployee.setJob(Jobmapper.selectByPrimaryKey(tbEmployee.getJid()));
			tbEmployee.setDept(Deptmapper.selectByPrimaryKey(tbEmployee.getDid()));
		}

		PageBean<TbEmployee> pageBean = new PageBean<TbEmployee>();
		pageBean.setRows(lists);
		TbEmployeeExample example = new TbEmployeeExample();
		int total = mapper.selectByExample(example).size();
		pageBean.setTotal(total);
		return pageBean;

	}

	@Override
	public boolean addEmployee(TbEmployee employee) {
		return mapper.insert(employee) > 0;

	}

	@Override
	public boolean deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public boolean deleteBybatch(Integer[] ids) {
		TbEmployeeExample example = new TbEmployeeExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		return mapper.deleteByExample(example) > 0;

	}

	@Override
	public TbEmployee findEmployeeById(Integer id) {
		return mapper.selectByPrimaryKey(id);

	}

	@Override
	public boolean udpateEmployee(TbEmployee employee) {
		System.out.println("AAAAAAAupdate------------------");
		TbEmployeeExample example = new TbEmployeeExample();
		example.createCriteria().andIdEqualTo(employee.getId());
		mapper.updateByExample(employee, example);
		return mapper.updateByExample(employee, example) > 0;

	}

	@Override
	public List<Message> showAll() {

		// 获取所有

		return null;
	}

	@Override
	public List<TbJob> selectJob() {

		TbJobExample example = new TbJobExample();
		List<TbJob> jobs = Jobmapper.selectByExample(example);

		return jobs;
	}

	@Override
	public List<TbDept> selectDept() {
		TbDeptExample example = new TbDeptExample();
		List<TbDept> depts = Deptmapper.selectByExample(example);
		return depts;
	}

}
