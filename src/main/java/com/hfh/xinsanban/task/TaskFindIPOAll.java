package com.hfh.xinsanban.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.hfh.xinsanban.Constant;
import com.hfh.xinsanban.dao.CorpDao;
import com.hfh.xinsanban.mail.MailSender;
import com.hfh.xinsanban.pojo.Corp;
import com.hfh.xinsanban.pojo.News;

public class TaskFindIPOAll extends TimerTask {
	CorpDao corpDao = new CorpDao();

	@Override
	public void run() {
		try {
			System.out.println(Constant.getDate() +"-TaskFindIPOAll begin");
			MailSender mailSender = new MailSender();
			Set<Corp> corps = corpDao.findCorpToSend();
			StringBuffer sbMailContent = new StringBuffer();

			if (corps.size() > 0) {
				for (Corp corp : corps) {
					System.out.println(corp);
					sbMailContent.append(corp.getNo()).append(":").append(corp.getName())
							.append("<br/>");
					for (News news : corp.getNews()) {
						sbMailContent.append(news.toString()).append("<br/>");
					}
					
					sbMailContent.append("<br/>");
				}
				sbMailContent.append("仔细核对标题和时间,会有误报！");
				System.out.println("-----"+ corps.size());
				String []recev = Constant.MailRecive.split(",");
				List<String> recipients = new ArrayList<String>();
				System.out.println("send to:");
				for(int i = 0; i < recev.length; i++){
					recipients.add(recev[i]);
					System.out.println(recev[i]);
				}
				mailSender.send(recipients, "find!!!!!!! xinsanban",
						sbMailContent.toString());
				
				for (Corp corp : corps) {
					corpDao.saveFind(corp);
					
					corp.setSendStatus(Constant.corpSended);
					corpDao.save(corp);
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Constant.getDate() +"-TaskFindIPOAll complete");
	}
}
