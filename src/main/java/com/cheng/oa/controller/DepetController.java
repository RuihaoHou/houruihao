package com.cheng.oa.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.oa.domain.TbDept;
import com.cheng.oa.service.DeptService;
import com.cheng.oa.vo.PageBean;

@Controller
public class DepetController {

	@Autowired
	private DeptService deptservice;

	@RequestMapping("/dept/selectDept")
	public String showDept() {
		return "dept/dept";
	}

	@RequestMapping("/djson")
	public @ResponseBody PageBean<TbDept> showAllDept(Integer pageNumber, Integer pageSize, String dname) {

		PageBean<TbDept> pageBean = deptservice.showDept(pageNumber, pageSize, dname);
		System.out.println(pageBean);

		return pageBean;

	}

	@RequestMapping("/dept/addDept")
	public String addDept(Integer flag, TbDept dept, HttpServletResponse response) {
		if (flag == 1) {
			return "dept/showAddDept";

		} else {
			if (deptservice.addDept(dept)) {
				try {
					response.getWriter().print("success");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					response.getWriter().print("error");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@RequestMapping("/dept/removeDept")
	public String deleteDept(Integer flag, Integer id, @RequestParam(required = false, value = "ids[]") Integer[] ids,
			HttpServletResponse response) {
		PrintWriter writer;
		try {
			writer = response.getWriter();

			boolean result = false;
			if (flag == 1) {
				result = deptservice.deleteById(id);
				return "dept/dept";

			} else {
				result = deptservice.deleteBybatch(ids);

			}
			if (result) {
				writer.print("success");
			} else {
				writer.print("error");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// /dept/updateDept

	@RequestMapping("/dept/updateDept")
	public String udpateDept(Integer flag, Integer id, TbDept dept, Model model, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();

			if (flag == 1) {// 跳转到修改信息页面
				model.addAttribute("dept", deptservice.findDeptById(id));
				return "dept/showUpdateDept";
			} else {

				if (deptservice.updateDept(dept)) {
					writer.print("success");
				} else {
					writer.print("error");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
