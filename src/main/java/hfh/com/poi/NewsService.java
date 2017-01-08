package hfh.com.poi;

import java.io.IOException;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsService {
	NewsDao newsDao = new NewsDao();
	CorpDao corpDao = new CorpDao();
	
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
				Set<Corp> corps = corpDao.findCorpByNews(news);
				for (Corp corp : corps) {
					corp.getNews().add(news);
					corp.setSendStatus(Constant.corpWaitSend);
					corpDao.save(corp);
				}
			}
		}
	}
}
