package com.hfh.xinsanban;

import java.io.IOException;
import java.util.Set;
import java.util.Timer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import redis.clients.jedis.Jedis;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfh.xinsanban.pojo.Corp;
import com.hfh.xinsanban.task.TaskFindIPOAll;
import com.hfh.xinsanban.task.TaskImportCorp;
import com.hfh.xinsanban.task.TaskImportNews;
import com.hfh.xinsanban.task.TaskImportNewsAll;

/**
 * Hello world!
 *
 */
public class Main {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		long day = 1000 * 60 * 60 *24;
		long Hour = 1000 * 60 * 60;
		Timer timer = new Timer();
		
		if(Constant.FUNC.equals("ALL"))
		{
			timer.scheduleAtFixedRate(new TaskImportCorp(), 1, day);
			timer.scheduleAtFixedRate(new TaskImportNewsAll(), 10000, day);
			timer.scheduleAtFixedRate(new TaskImportNews(), 20000, Hour);
			timer.scheduleAtFixedRate(new TaskFindIPOAll(), 30000, Hour+ 30000);
		}else if (Constant.FUNC.equals("MAIL")){
			timer.scheduleAtFixedRate(new TaskFindIPOAll(), 5000, day);
		}else{
			timer.scheduleAtFixedRate(new TaskImportNews(), 20000, Hour);
			timer.scheduleAtFixedRate(new TaskFindIPOAll(), 30000, Hour+ 30000);
		}
		
//		getFromRedis();
		

	}

	private static void getFromRedis() throws JsonParseException, JsonMappingException, IOException {
		Jedis jedis = new Jedis(Constant.redisHost);
		Set<String> keys = jedis.keys("*");
		
		for (String key : keys) {
			String value = jedis.get(key);
			ObjectMapper om = new ObjectMapper();
			Corp corp = om.readValue(value,	Corp.class);
			
			System.out.println(corp);
		}
		
		jedis.close();
	}

	
}
