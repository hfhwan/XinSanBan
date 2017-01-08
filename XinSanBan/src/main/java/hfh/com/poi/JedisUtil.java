package hfh.com.poi;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import redis.clients.jedis.Jedis;

public class JedisUtil {

	public static void importNews(Jedis jedis, Document doc, String shenfeng) throws InterruptedException {
		Elements eles = doc.getElementsByClass("fl_list");
		Element e = eles.get(0);
		Elements eleHrefs = e.getElementsByTag("a");
		for (Element eHref : eleHrefs) {
			String title = eHref.text();
			Element eDate = eHref.nextElementSibling();
			String sDate = eDate.text();
			
			String sD = sDate.replace("-","");
			double score = Double.parseDouble(sD);
			
			String nowNews = sDate + "@" + title;
			Long rank = jedis.zrank("addr-" + shenfeng, nowNews);
			if( rank == null){
				jedis.zadd("addr-" + shenfeng, score, nowNews);
				System.out.println("addr-" + shenfeng + null);
			}else{
				System.out.println("addr-" + shenfeng + rank);
			}
		}
		Thread.sleep(5000);
	}

}
