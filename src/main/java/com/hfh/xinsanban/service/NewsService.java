package com.hfh.xinsanban.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hfh.xinsanban.Constant;
import com.hfh.xinsanban.dao.CorpDao;
import com.hfh.xinsanban.dao.NewsDao;
import com.hfh.xinsanban.pojo.Corp;
import com.hfh.xinsanban.pojo.News;

public class NewsService {
	NewsDao newsDao = new NewsDao();
	CorpDao corpDao = new CorpDao();
	private Set<Corp> corps = new HashSet<Corp>();
	
	public void loadAllCorps() {
		corpDao.loadAllCorps(corps);
	}
	
	
	public void importNews(Document doc, String shenfeng) throws InterruptedException, IOException {
		
		Elements eles = doc.getElementsByClass("fl_list");
		Element e = eles.get(0);
		Elements eleHrefs = e.getElementsByTag("a");
		for (Element eHref : eleHrefs) {
			String title = eHref.text();
			Element eDate = eHref.nextElementSibling();
			String sDate = eDate.text();
			String sD = sDate.replace("-","");
			
			News news = new News();
			news.setsDate(sD);
			news.setAddr(shenfeng);
			news.setTitle(title);
			
			//看一下新闻是否存在，不存在的话，比较是否有新消息
			boolean isExist = newsDao.exist(news);
			if( isExist){
//				System.out.println("addr-" + shenfeng);
			}else{
				newsDao.save(news);
				Set<Corp> corpsFinded = corpDao.findCorpByNews(news, corps);
				for (Corp corp : corpsFinded) {
					corp.getNews().add(news);
					corp.setSendStatus(Constant.corpWaitSend);
					corpDao.save(corp);
//					Constant.corps.add(corp.getNo());
					
					corps.remove(corp);
					corps.add(corp);
				}
			}
		}
	}
}
