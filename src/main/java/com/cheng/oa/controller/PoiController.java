package com.cheng.oa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cheng.oa.service.PoiService;

@Controller
public class PoiController {
	
	@Autowired
	private PoiService service;

	@RequestMapping("/poi/createPoi")
	public String getPoi() {

		return "poi/poi";
	}

	/* 生成execle 并下载 */
	@RequestMapping("/poi/createExcel")
	public String createExcel(String username,HttpServletRequest request) {
		//创建的execle 的名字
		String filename = service.createExcel(username, request);
		System.out.println(filename);
		return "redirect:/doc/downloadDocument?filename="+filename;
	}

}
