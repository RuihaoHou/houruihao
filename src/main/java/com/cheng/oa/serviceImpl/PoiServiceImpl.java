package com.cheng.oa.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheng.oa.domain.TbUser;
import com.cheng.oa.domain.TbUserExample;
import com.cheng.oa.mapper.TbUserMapper;
import com.cheng.oa.service.PoiService;

@Service
public class PoiServiceImpl implements PoiService {

	@Autowired
	private TbUserMapper usermapper;

	@Override
	public String createExcel(String username, HttpServletRequest request) {

		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameLike("%" + username + "%");
		List<TbUser> users = usermapper.selectByExample(example);
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet();
		// 创建第一行
		HSSFRow firstRow = sheet.createRow(0);
		// 在第一行创建对应的单元格也就是表中的字段
		TbUser user = users.get(0);
		JSONObject json = (JSONObject) JSONObject.toJSON(user);
		Set<String> keySet = json.keySet();
		int i = 0;
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			firstRow.createCell(i).setCellValue(key);
			i++;
		}

		for (int j = 1; j < users.size() + 1; j++) {
			HSSFRow createRow = sheet.createRow(j);
			TbUser user2 = users.get(j - 1);
			JSONObject json2 = (JSONObject) JSONObject.toJSON(user2);
			// 把每一遍历出来的user对象都转换成json 对象 并获取对应的key 和value
			// 现在要对每一列 进行赋值
			Iterator<String> iterator2 = json2.keySet().iterator();
			int k = 0;
			while (iterator2.hasNext()) {
				String key = (String) iterator2.next();
				Object object = json2.get(key);
				HSSFCell createCell = createRow.createCell(k);
				if (object instanceof Date) {
					createCell.setCellValue((Date) object);
				} else if (object instanceof Integer) {
					createCell.setCellValue((Integer) (object));
				} else {
					createCell.setCellValue((String) object);
				}
				k++;
			}
		}
		
		// 将生成好的excle 文件 输出到某一路径下 供别人下载
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		File file = new File(realPath + "/" + File.separator + System.currentTimeMillis() + ".xls");
		try {
			if (!file.exists()) {
				file.createNewFile();
				FileOutputStream stream;
				stream = FileUtils.openOutputStream(file);
				book.write(stream);
				book.close();
				stream.flush();
				stream.close();
				return file.getName();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
