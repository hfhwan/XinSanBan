package com.hfh.xinsanban.dao;

import redis.clients.jedis.Jedis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfh.xinsanban.Constant;
import com.hfh.xinsanban.pojo.News;

public class NewsDao {
	Jedis jedis = new Jedis(Constant.redisHost);
	ObjectMapper om = new ObjectMapper();
	
	public void save(News news) throws JsonProcessingException {
		String newsJson = om.writeValueAsString(news);
		double score = Double.parseDouble(news.getsDate());
		jedis.zadd("addr-" + news.getAddr(), score, newsJson);
	}

	public boolean exist(News news) throws JsonProcessingException {
		String newsJson = om.writeValueAsString(news);
		Long rank = jedis.zrank("addr-" + news.getAddr(), newsJson);
		if(rank == null){
			return false;
		}else{
			return true;
		}
	}
}
