package com.cheng.oa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.oa.domain.TbDept;
import com.cheng.oa.domain.TbEmployee;
import com.cheng.oa.domain.TbJob;
import com.cheng.oa.service.EmployeeService;

import com.cheng.oa.vo.PageBean;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/employee/selectEmployee")
	public String selectEmployee(HttpSession session) {

		List<TbDept> deptList = employeeService.selectDept();
		List<TbJob> jobList = employeeService.selectJob();
		session.setAttribute("deptList", deptList);
		session.setAttribute("jobList", jobList);

		return "employee/employee";
	}

	// 展示所有信息
	@RequestMapping("/ejson")
	public @ResponseBody PageBean<TbEmployee> showAllEmployee(Integer pageNumber, Integer pageSize) {
		System.out.println(pageNumber);
		System.out.println(pageSize);
		PageBean<TbEmployee> pageBean = employeeService.showEmployee(pageNumber, pageSize);
		System.out.println(pageBean);
		return pageBean;
	}

	// 更新
	@RequestMapping("/employee/updateEmployee")
	public String updateEmployee(Integer flag, Integer id, Model model, String name, TbEmployee employee,
			HttpServletResponse response) {
		System.out.println("udpate");
		if (flag == 1) {
			TbEmployee employee1 = employeeService.findEmployeeById(id);
			model.addAttribute("employee", employee1);
			return "employee/showUpdateEmployee";
		} else {
			try {
				Date date = new Date();
				employee.setCreatedate(date);
				if (employeeService.udpateEmployee(employee)) {
					response.getWriter().print("success");
				} else {
					response.getWriter().println("error");
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
			return null;
		}
	}

	// 添加员工信息
	@RequestMapping("/employee/addEmployee")
	public String addEmployee(Integer flag, Integer jid, HttpSession session, TbEmployee employee,
			HttpServletResponse resonse) {
		if (flag == 1) {

			List<TbDept> deptList = employeeService.selectDept();
			List<TbJob> jobList = employeeService.selectJob();
			session.setAttribute("deptList", deptList);
			session.setAttribute("jobList", jobList);
			return "employee/showAddEmployee";

		} else {
			// 添加
			Date date = new Date();
			employee.setCreatedate(date);
			if (employeeService.addEmployee(employee)) {

				try {
					resonse.getWriter().print("success");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					resonse.getWriter().print("error");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return null;

	}

	// 删除
	// /employee/removeEmployee

	@RequestMapping("/employee/removeEmployee")
	public String deleteEmployee(Integer flag, Integer id,
			@RequestParam(required = false, value = "ids[]") Integer[] ids, HttpServletResponse response) {
		boolean result = false;
		if (flag == 1) {
			System.out.println("AAAA");
			if (employeeService.deleteById(id)) {
				return "employee/employee";
			}
		} else {
			result = employeeService.deleteBybatch(ids);
		}

		if (result) {
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
		return null;

	}
}
