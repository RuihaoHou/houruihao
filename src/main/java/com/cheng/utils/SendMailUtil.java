package com.cheng.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailUtil {

	public static boolean seadMail(String recerver, String title, String content) {
		Properties properties = new Properties();

		/*
		 * 邮件发送时的必要设置 smtp:Simple Mail Transport Protocol;，简单邮件传输协议
		 * 
		 * pop3:邮件接收协议 在接收邮件时候使用 指明邮件服务器地址
		 */
		// 指明邮件服务器地址
		properties.setProperty("mail.smtp.host", "localhost");
		// 使用邮件服务器发送邮件时是否验证
		properties.setProperty("mail.smtp.auto", "true");
		// 指明邮件服务器采用的协议
		properties.setProperty("mail.smtp.protocol", "smtp");

		/* 邮件会话对象 */
		Session session = Session.getInstance(properties);

		/* 构建消息对象 */
		MimeMessage mimeMessage = new MimeMessage(session);

		// 往消息里面设置内容

		// 获取一个传输对象
		try {

			// 设置发见人地址
			mimeMessage.setFrom(new InternetAddress("ckj1@163.com"));
			mimeMessage.setSubject(title);
			mimeMessage.setContent(content, "text/html;charset=UTF-8");
			Transport transport = session.getTransport();
			// smtp协议端口号：25
			// pop3协议端口号：110
			// 建立与服务器之间的连接
			// host:服务器地址
			// port：邮件服务器的端口号
			transport.connect("localhost", 25, "ckj1@163.com", "123");
			// 建立连接之后就可以发送信息
			transport.sendMessage(mimeMessage, new Address[] {
					// 把收件人的地址指定
					new InternetAddress(recerver) });
			transport.close();
			return true;

		} catch (NoSuchProviderException e) {

			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

/*	public static void main(String[] args) {
		boolean seadMail = SendMailUtil.seadMail("ckj2@163.com", "AAA", "BBBB");
		System.out.println(seadMail);
	}*/

}
