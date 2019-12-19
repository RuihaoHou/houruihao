package com.cheng.oa.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheng.oa.domain.TbSign;
import com.cheng.oa.mapper.TbSignMapper;
import com.cheng.oa.mapper.TbUserMapper;
import com.cheng.oa.service.SignService;
import com.cheng.oa.vo.PageBean;
import com.cheng.oa.vo.SingChart;

@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private TbUserMapper usermapper;
	@Autowired
	private TbSignMapper mapper;

	@Override
	public PageBean<TbSign> showAll(Integer pageNum, Integer pageSize, String startDate, String endDate) {
		PageBean<TbSign> pageBean = new PageBean<TbSign>();

		if (!startDate.equals("") && !endDate.equals("")) {
			System.out.println("---A-------");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("startTime", startDate);
			map.put("endTime", endDate);
			map.put("start", (pageNum - 1) * pageSize);
			map.put("end", pageSize);
			List<TbSign> signs = mapper.selectPageBeanByTime(map);

			for (int i = 0; i < signs.size(); i++) {
				signs.get(i).setUser(usermapper.selectByPrimaryKey(signs.get(i).getUid()));
			}
			Integer total = mapper.selectPageBeanByTimeCount(map);

			pageBean.setRows(signs);
			pageBean.setTotal(total);
			return pageBean;

		} else {
			System.out.println("-B------------");

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageNum - 1) * pageSize);
			map.put("end", pageSize);
			List<TbSign> signs = mapper.selectPageBean(map);

			for (int i = 0; i < signs.size(); i++) {
				signs.get(i).setUser(usermapper.selectByPrimaryKey(signs.get(i).getUid()));
			}
			Integer total = mapper.selectPageBeanCount(map);

			pageBean.setRows(signs);
			pageBean.setTotal(total);
			return pageBean;
		}

	}

	@Override
	public List<SingChart> findSignCharts(String beignday) {

		return mapper.findSignChart(beignday);

	}

}
