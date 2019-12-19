package com.cheng.oa.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import javax.mail.Header;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.ServiceDelegate;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cheng.oa.domain.TbDoc;
import com.cheng.oa.service.DocumentService;
import com.cheng.oa.vo.PageBean;

@Controller
public class DocumentController {

	@Autowired
	private DocumentService service;

	// docjson
	@RequestMapping("/doc/selectDocument")
	public String select() {
		return "document/document";
	}

	@RequestMapping("/docjson")
	public @ResponseBody PageBean<TbDoc> showAll(Integer pageNum, Integer pageSize,
			@RequestParam(value = "", required = false) String title) {
		return service.show(pageNum, pageSize, title);
	}

	@RequestMapping("/doc/addDocument")
	public String addDoc() {

		return "document/showAddDocument";
	}

	// 上传
	@RequestMapping("/doc/saveDocument")
	public @ResponseBody HashMap<String, Integer> upload(
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, TbDoc doc,
			ModelMap model) {

		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		System.out.println(path);
		String uuid = UUID.randomUUID().toString();
		String targetName = fileName + "--" + uuid;
		File targetFile = new File(path, targetName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 保存数据库
		doc.setFilename(targetName);
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		if (service.saveDoc(doc)) {
			map.put("code", 1);

		} else {
			map.put("code", 0);
		}

		return map;

	}

	@RequestMapping("/doc/downloadDocument")
	public ResponseEntity<byte[]> downLoad(String filename, HttpServletRequest request) {

		String path = request.getSession().getServletContext().getRealPath("upload");

		File file = new File(path, filename);

		if (file.exists()) {
			try {
				HttpHeaders headers = new HttpHeaders();

				String downName = new String(filename.getBytes("utf-8"), "ISO-8859-1");
				// 设置以附件的形式下载
				headers.setContentDispositionFormData("attachment", downName);
				// 设置文件内容以流的形式来下载
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

				// 用springmvc 提供的下载方式
				ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
						headers, HttpStatus.OK);

				return responseEntity;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;

	}

	// delete
	@RequestMapping("/doc/removeDocument")
	public String delete(Integer flag, Integer id, @RequestParam(required = false, value = "ids[]") Integer[] ids,
			HttpServletResponse response) {

		if (flag == 1) {

			if (service.delete(id)) {
				return "document/document";
			}
			return "forward:404.html";

		} else {
			if (service.deleteBybatch(ids)) {
				try {
					response.getWriter().print("success");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return null;
		}

	}

}
