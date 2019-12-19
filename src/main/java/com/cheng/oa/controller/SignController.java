package com.cheng.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cheng.oa.domain.TbSign;
import com.cheng.oa.service.SignService;
import com.cheng.oa.vo.PageBean;
import com.cheng.oa.vo.SingChart;

@Controller
public class SignController {

	@Autowired
	private SignService service;

	// 展示打卡信息
	@RequestMapping("/sign/selectSign")
	public String showSign() {
		return "sign/sign";
	}

	@RequestMapping("/sign/signJson")
	public @ResponseBody PageBean<TbSign> showAll(Integer pageNum, Integer pageSize,
			@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {

		PageBean<TbSign> pageBean = service.showAll(pageNum, pageSize, startDate, endDate);
		System.out.println(pageBean.getTotal());
		System.out.println(pageBean.getRows().get(0).getCreatetime());

		System.out.println("helloword");

		return pageBean;
	}

	@RequestMapping("/sign/showChart")
	public String showChart() {

		return "sign/signCharts";
	}

	@RequestMapping("/sign/chartsJson")
	public @ResponseBody List<SingChart> chartsJson(@RequestParam(defaultValue = "1900-01-01")  String beginDay) {

		return service.findSignCharts(beginDay);

	}
	// ${ctx}/sign/decideSign" 判断打卡状态 返回的是code 1--0

	// <!--判断用户今天是否已经打卡 -->${ctx}/sign/decideSign"

}
