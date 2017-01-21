package com.hfh.xinsanban.mail;

import java.util.logging.Logger;

public class MailSendThread extends Thread {
	private static Logger logger = null;//LogManager.getLogger(MailSendThread.class.getName());
	private String mailContent;
	private String recipient;
	
	private MailSender mailSender;

	public MailSendThread(String username, String activateMD5, String recipient ) {
//		mailContent = "请点击下面链接激活帐户，完成注册。<br/>" + "<a href=" + Constant.SITE_URL
//				+ "/user/activate?username=" + username + "&code="
//				+ activateMD5 + ">" + Constant.SITE_URL
//				+ "/user/activate?username=" + username + "&code="
//				+ activateMD5 + "</a><br/><br/>"
//				+ "如果该链接无法点击，请直接拷贝以上网址到浏览器地址栏中访问。<br/>"
//				+ "<p>请保存好此邮件，当你无法使用VPN时，请尝试：<br/>"
//				+ "1. 使用浏览器访问www.ftqvpn.com <br/>"
//				+ "2. 如果无法访问，我们的网站可能已经被屏蔽。请回复此邮件任意内容，我们会发送给你最新的VPN使用方式<br/>"
//				+ "3. 或关注微信：ftqvpn <img  src='" + Constant.SITE_URL + "/img/weixin.jpg'>" +
//				"微博:ftqvpn<img  src='"+Constant.SITE_URL+"/img/weibo.png'></p>";
		mailSender = new MailSender();
		this.recipient = recipient;
	}

	@Override
	public void run() {
		try {
			mailSender.send(recipient, "佛跳墙VPN注册", this.mailContent);
		} catch (Exception e) {
//			logger.error("发送邮件失败", e);
			try {
				mailSender.send(recipient, "佛跳墙VPN注册", this.mailContent);
			} catch (Exception ee) {
//				logger.error("发送邮件再次失败", ee);
			}
		}
	}
}
