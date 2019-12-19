package com.cheng.oa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.oa.domain.TbAdvice;
import com.cheng.oa.service.NoticeService;
import com.cheng.oa.vo.PageBean;
import com.cheng.utils.SDKTestSendTemplateSMS;
import com.cheng.utils.SendEmailIntenetUtil;
import com.cheng.utils.SendMailUtil;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("/notice/selectNotice")
	public String selectNotic() {
		return "notice/notice";
	}

	@RequestMapping("/noticeJson")
	public @ResponseBody PageBean<TbAdvice> selectNotice(Integer pageNum, Integer pageSize,
			@RequestParam(required = false) String title) {

		PageBean<TbAdvice> pageBean = noticeService.showAll(pageNum, pageSize, title);

		long total = pageBean.getTotal();

		List<TbAdvice> rows = pageBean.getRows();
		for (TbAdvice tbAdvice : rows) {
			System.out.println(tbAdvice);
		}
		return pageBean;
	}

	@RequestMapping("/notice/addNotice")
	public String addNotice(Integer flag, TbAdvice advice, Integer uid, HttpServletResponse response) {

		if (flag == 1) {
			return "notice/showAddNotice";

		} else {

			if (noticeService.addNotice(advice, uid)) {
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

	@RequestMapping("/notice/updateNotice")
	public String updateNotice(Integer flag, Integer id, TbAdvice advice, HttpServletRequest request,
			HttpServletResponse response) {

		if (flag == 1) {
			TbAdvice advice1 = noticeService.selectById(id);
			request.setAttribute("notice", advice1);
			return "notice/showUpdateNotice";

		} else {

			if (noticeService.updateNotice(advice)) {
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

	// notice/removeNotice
	@RequestMapping("/notice/removeNotice")
	public String deleteNotice(Integer flag, Integer id, @RequestParam(value = "ids[]", required = false) Integer[] ids,
			HttpServletResponse response) {
		boolean result = false;
		if (flag == 1) {
			if (noticeService.deleteById(id)) {
				result = true;

				return "notice/notice";
			}

		} else {
			if (noticeService.deleteByIdBatch(ids)) {
				result = true;
			}
		}
		try {
			if (result) {

				response.getWriter().print("success");

			} else {
				response.getWriter().print("error");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@RequestMapping("/notice/prenotice")
	public @ResponseBody TbAdvice prenotice(Integer id) {

		TbAdvice notice = noticeService.selectById(id);

		return notice;
	}

	// 发送邮件
	@RequestMapping("/notice/addEmail")
	public String sendEmail(Integer flag, String email, String title, String content, HttpServletResponse response) {
		if (flag == 1) {
			return "notice/showAddEmail";
		} else {
			if (SendEmailIntenetUtil.sendEmail(email, title, content)) {
				try {
					response.getWriter().print("success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@RequestMapping("/notice/addMsg")
	public String sendMessage(Integer flag, String phone, String content, HttpServletResponse response) {
		if (flag == 1) {
			return "notice/showAddMsg";

		} else {
			boolean sendMessage = SDKTestSendTemplateSMS.sendMessage(phone, content);
			if (sendMessage) {
				try {
					response.getWriter().print("success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return null;

	}

}
