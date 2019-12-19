package com.cheng.oa.serviceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheng.oa.domain.TbDoc;
import com.cheng.oa.domain.TbDocExample;
import com.cheng.oa.domain.TbUser;
import com.cheng.oa.mapper.TbDocMapper;
import com.cheng.oa.mapper.TbUserMapper;
import com.cheng.oa.service.DocumentService;
import com.cheng.oa.vo.PageBean;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private TbDocMapper mapper;
	@Autowired
	private TbUserMapper usermapper;

	@Override
	public PageBean<TbDoc> show(Integer pageNumber, Integer pageSize, String title) {

		if (!title.equals("")) {
			System.out.println("-------------");

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageNumber - 1) * pageSize);
			map.put("end", pageSize);
			map.put("title", "%" + title + "%");
			List<TbDoc> docs = mapper.selectPageBeanByTitle(map);
			for (TbDoc tbDoc : docs) {
				tbDoc.setUser(usermapper.selectByPrimaryKey(tbDoc.getUid()));
			}

			PageBean<TbDoc> pageBean = new PageBean<TbDoc>();
			pageBean.setRows(docs);
			pageBean.setTotal(mapper.countByExample(new TbDocExample()));
			return pageBean;
		} else {

			HashMap<String, Integer> map = new HashMap<String, Integer>();

			map.put("start", (pageNumber - 1) * pageSize);
			map.put("end", pageSize);
			List<TbDoc> docs = mapper.selectPageBean(map);
			for (TbDoc tbDoc : docs) {
				tbDoc.setUser(usermapper.selectByPrimaryKey(tbDoc.getUid()));
			}

			PageBean<TbDoc> pageBean = new PageBean<TbDoc>();
			pageBean.setRows(docs);
			pageBean.setTotal(mapper.countByExample(new TbDocExample()));
			return pageBean;
		}

	}

	@Override
	public boolean saveDoc(TbDoc doc) {

		doc.setDate(new Date());
		return mapper.insert(doc) > 0;
	}

	@Override
	public boolean delete(Integer id) {
		return mapper.deleteByPrimaryKey(id) > 0;

	}

	@Override
	public boolean deleteBybatch(Integer[] ids) {
		TbDocExample example = new TbDocExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		return mapper.deleteByExample(example) > 0;

	}

}
