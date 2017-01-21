package com.hfh.xinsanban.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 简单邮件发送器，可单发，群发。
 * 
 */
public class MailSender {

	/**
	 * 发送邮件的props文件
	 */
	private final  Properties props = System.getProperties();
	/**
	 * 邮件服务器登录验证
	 */
	private  MailAuthenticator authenticator;

	/**
	 * 邮箱session
	 */
	private  Session session;

	public MailSender()
	{
		init("ftqvpn@sina.com", "abc@zzz3", "smtp.sina.com");
	}
	
	/**
	 * 初始化邮件发送器
	 * 
	 * @param smtpHostName
	 *            SMTP邮件服务器地址
	 * @param username
	 *            发送邮件的用户名(地址)
	 * @param password
	 *            发送邮件的密码
	 */
	public MailSender(final String smtpHostName, final String username,
			final String password) {
		init(username, password, smtpHostName);
	}

	/**
	 * 初始化
	 * 
	 * @param username
	 *            发送邮件的用户名(地址)
	 * @param password
	 *            密码
	 * @param smtpHostName
	 *            SMTP主机地址
	 */
	private void init(String username, String password, String smtpHostName) {
		// 初始化props
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostName);
//		 props.put("mail.smtp.socketFactory.port", 465);
//		 props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.ssl.enable", "true");
		// 验证
		authenticator = new MailAuthenticator(username, password);
		// 创建session
		session = Session.getDefaultInstance(props, authenticator);
	}

	/**
	 * 发送邮件
	 * 
	 * @param recipient
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(String recipient, String subject, Object content)
			throws AddressException, MessagingException {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getUsername()));
		// 设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);
	}

	/**
	 * 群发邮件
	 * 
	 * @param recipients
	 *            收件人们
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(List<String> recipients, String subject, Object content)
			throws AddressException, MessagingException {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getUsername()));
		// 设置收件人们
		final int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(RecipientType.TO, addresses);
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);
	}

	public static void main(String[] argv) {
		int fail = 0;
//		MailSender ms = new MailSender("smtp.mail.yahoo.com",
//				"ftqvpn@yahoo.com", "abc@zzz3");
//		MailSender ms = new MailSender("smtp.163.com",
//				"hfhwan@163.com", "51185114@hfh");
//		MailSender ms = new MailSender("smtp-mail.outlook.com",
//				"ftqvpn@outlook.com", "abc@zzz3");
		
//		for (int i = 0; i < 30; i++) {
//			try {
//				ms.send("ftqvpn@163.com", "vpntest中文"+i, "content中文"+i);
//				System.out.println("发送成功" + i);
//				Thread.sleep(5000);
//			} catch (Exception e) {
//				fail++;
//				e.printStackTrace();
//			}
//		}
		
		System.out.println("fail:" + fail);
		
		
		
	}

}
