package com.cheng.utils;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.cheng.oa.domain.Auth;

/*
 *
 *  mailusername=jackchengvips@163.com
   	mailpassword=Ckj2352400400
	mailname=googlecode
	smtphost=smtp.163.com
	port=25*/

public class SendEmailIntenetUtil {

	private static Session session;

	public static boolean sendEmail(String receiver, String title, String content) {

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.163.com");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.host", "smtp.163.com");

		Auth auth = new Auth("13063310938@163.com", "Ckj2352400400");

		properties.put("mail.smtp.auth", "true");
		session = Session.getInstance(properties, auth);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom("13063310938@163.com");
			msg.setRecipients(MimeMessage.RecipientType.TO, receiver);

			msg.setSubject(title,"utf-8");
			msg.setContent(content,"text/html;charset=utf-8");
			Transport.send(msg);
			return true;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

}
