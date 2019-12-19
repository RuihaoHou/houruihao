package com.cheng.oa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.oa.domain.TbUser;
import com.cheng.oa.service.UserService;
import com.cheng.oa.vo.PageBean;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;

	// 登录
	@RequestMapping("/login")
	public String login(TbUser user, String ishave, HttpServletRequest request, HttpServletResponse response) {
		String options = ishave;
		if ("remember".equals(options)) {

			Cookie cookie = new Cookie("loginName", user.getLoginname());
			cookie.setMaxAge(Integer.MAX_VALUE);

			Cookie cookie1 = new Cookie("remember", "checked='checked'");
			cookie1.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
			response.addCookie(cookie1);

		} else {// 没有"记住用户名"
				// 清除掉Cookie信息
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie ck : cookies) {
					
					if (ck.getValue().equals(user.getLoginname())) {
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
					if (ck.getValue().equals("checked='checked'")) {
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
					
				}
			}
		}

		// 开始验证登录
		TbUser login = userservice.login(user);
		if (login != null) {

			HttpSession session = request.getSession();
			session.setAttribute("user_session", login);
			session.setMaxInactiveInterval(24 * 60 * 60);
			return "index";
		} else {
			request.setAttribute("error", "用户名或者密码错误！");
			return "login";
		}

	}

	// 用户查询
	@RequestMapping("/user/selectUser")
	public String serachUser() {
		System.out.println("serach");

		return "user/user";
	}

	// 展示所有 一进来就展示
	@RequestMapping("/userJson1")
	@ResponseBody
	public PageBean<TbUser> queryInfo1(Integer pageNumber, Integer pageSize) {
		System.out.println("userJson1");
		// 查询所有
		PageBean<TbUser> pageBean = userservice.showAll(pageNumber, pageSize);
		return pageBean;
	}

	@RequestMapping("/userJson")
	@ResponseBody
	public PageBean<TbUser> queryInfo(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String username,
			@RequestParam(defaultValue = "0") String status) {

		PageBean<TbUser> pageBean = userservice.select(username, status);

		return pageBean;
	}

	@RequestMapping("/user/addUser")
	public String addUser(TbUser user, Integer flag, HttpServletResponse response) {

		if (flag == 1) {
			return "user/showAddUser";
		} else {

			// 开始真正的添加
			if (userservice.addUser(user)) {
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

	// 修改数据
	@RequestMapping("/user/updateUser")
	public String updateUser(Integer flag, Integer id, Model model, TbUser user, HttpServletResponse response) {

		if (flag == 1) {
			TbUser user1 = userservice.findUserById(id);

			model.addAttribute("user", user1);

			return "user/showUpdateUser";

		} else {

			if (userservice.updateUser(user)) {
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

	// 删除数据和批量删除
	// ctx}/user/removeUser?flag=1&id=" + id;
	@RequestMapping("/user/removeUser")
	public void deleteUser(Integer flag, Integer id, HttpServletResponse response,
			@RequestParam(value = "ids[]", required = false) Integer[] ids) {

		System.out.println(ids);
		if (flag == 1) {
			if (userservice.deleteById(id)) {
				try {
					response.getWriter().print("success");

				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {
				try {
					response.getWriter().print("error");
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		} else {
			// 批量删除
			if (userservice.deleteBybatch(ids)) {
				try {
					response.getWriter().print("success");
				} catch (IOException e) {

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

	}

	
	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
