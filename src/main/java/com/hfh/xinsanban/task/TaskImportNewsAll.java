package com.hfh.xinsanban.task;

import java.io.IOException;
import java.util.Date;
import java.util.Map.Entry;
import java.util.TimerTask;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.hfh.xinsanban.Constant;
import com.hfh.xinsanban.service.NewsService;

public class TaskImportNewsAll extends TimerTask {
	NewsService newsService = new NewsService();
	
	@Override
	public void run() {
		System.out.println(Constant.getDate() + "-TaskImportNewsAll begin");
		newsService.loadAllCorps();
		Document doc;
		try {
			for (Entry<String, String> url : Constant.ShenFengUrl.entrySet()) {
				System.out.println("news add begin:" + url.getKey());
				int i  = 0;
				for (i = 1; i < 10000; i++) {
					try {
						//第一页是index，不带数字。这里面不加了
						doc = Jsoup.connect(url.getValue() + "index_" + i + ".html").get();
					} catch (HttpStatusException e1) {
						if(e1.getStatusCode() == 404){
							break; //最后一页了
						}else{
							throw e1;
						}
					} 
					
					newsService.importNews(doc, url.getKey());
					Thread.sleep(10000);
				}
				System.out.println(new Date() + "news add end:" + url.getKey() + " pages:" + i);
			}
		} catch (InterruptedException e1) {
			//TODO mail
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO mail
			e1.printStackTrace();
		}
		System.out.println(Constant.getDate() +"-TaskImportNewsAll complete");
	}
}
