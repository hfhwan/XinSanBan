package hfh.com.poi;

import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class NewsDao {
	Jedis jedis = new Jedis(Constant.redisHost);
	ObjectMapper om = new ObjectMapper();
	
	public void save(News news) throws JsonProcessingException {
		String newsJson = om.writeValueAsString(news);
		double score = Double.parseDouble(news.getsDate());
		jedis.zadd("addr-" + news.addr, score, newsJson);
	}

	public boolean exist(News news) throws JsonProcessingException {
		String newsJson = om.writeValueAsString(news);
		Long rank = jedis.zrank("addr-" + news.addr, newsJson);
		if(rank == null){
			return false;
		}else{
			return true;
		}
	}
}
