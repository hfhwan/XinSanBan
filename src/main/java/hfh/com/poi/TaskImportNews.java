package hfh.com.poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class TaskImportNews extends TimerTask {
	NewsService newsService = new NewsService();
	@Override
	public void run() {
		System.out.println("TaskImportNews begin");
		newsService.loadAllCorps();
		Document doc = null;
		try {
			for (Entry<String, String> url : Constant.ShenFengUrl.entrySet()) {
				System.out.println("news add begin:" + url.getKey() );
				doc = Jsoup.connect(url.getValue()).get();
				newsService.importNews( doc, url.getKey());
				System.out.println("news add end:" + url.getKey());
				Thread.sleep(10000);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("TaskImportNews complete");
	}
}
