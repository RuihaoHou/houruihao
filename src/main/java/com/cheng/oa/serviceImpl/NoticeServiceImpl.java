package com.cheng.oa.serviceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cheng.oa.domain.TbAdvice;
import com.cheng.oa.domain.TbAdviceExample;
import com.cheng.oa.domain.TbUser;
import com.cheng.oa.mapper.TbAdviceMapper;
import com.cheng.oa.mapper.TbUserMapper;
import com.cheng.oa.service.NoticeService;
import com.cheng.oa.vo.PageBean;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private TbAdviceMapper mapper;

	@Autowired
	private TbUserMapper usermapper;

	@Override
	public PageBean<TbAdvice> showAll(Integer pageNum, Integer pageSize, String title) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		int rowStart = (pageNum - 1) * pageSize;
		map.put("rowStart", rowStart);
		map.put("end", pageSize);

		if (!title.equals("")) {

			map.put("title", title);
			List<TbAdvice> advices = mapper.selectPageBeantitle(map);
			for (TbAdvice tbAdvice : advices) {
				
				TbUser user = usermapper.selectByPrimaryKey(tbAdvice.getUid());
				tbAdvice.setUser(user);
			}
			PageBean<TbAdvice> pageBean = new PageBean<TbAdvice>();
			pageBean.setRows(advices);

			pageBean.setTotal(mapper.countByExample(new TbAdviceExample()));
			return pageBean;
		}

		List<TbAdvice> advices2 = mapper.selectPageBean(map);

		PageBean<TbAdvice> pageBean = new PageBean<TbAdvice>();
		for (TbAdvice tbAdvice : advices2) {

			TbUser user = usermapper.selectByPrimaryKey(tbAdvice.getUid());
			tbAdvice.setUser(user);
		}
		pageBean.setRows(advices2);
		pageBean.setTotal(mapper.countByExample(new TbAdviceExample()));

		return pageBean;
	}

	@Override
	public boolean addNotice(TbAdvice advice, Integer uid) {

		advice.setCreatedate(new Date());
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("createDate", new Date());
		map.put("title", advice.getTitle());
		map.put("content", advice.getContent());
		map.put("uid", uid);

		return mapper.insertByUid(map) > 0;

	}

	@Override
	public TbAdvice selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);

	}

	@Override
	public boolean updateNotice(TbAdvice advice) {
		advice.setCreatedate(new Date());
		return mapper.updateByPrimaryKeyWithBLOBs(advice) > 0;

	}

	@Override
	public boolean deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id) > 0;

	}

	@Override
	public boolean deleteByIdBatch(Integer[] ids) {

		TbAdviceExample example = new TbAdviceExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		return mapper.deleteByExample(example) > 0;
	}

}
