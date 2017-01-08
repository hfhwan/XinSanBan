package hfh.com.poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class TaskFindIPONew extends TimerTask {
	Jedis jedis = new Jedis(Constant.redisHost);
	List<Corp> corps = new ArrayList<Corp>();

	@Override
	public void run() {
		try {
			System.out.println(new Date() + "TaskFindIPONew begin");
			loadAllCorps();
			Set<String> keyAddrNews = jedis.keys("addr-*");
			for (String key : keyAddrNews) {
				Set<String> newses = jedis.zrevrange(key, 0, -1);
				for (String news : newses) {
					compareNewsAndCorp(key, news);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jedis.close();
		System.out.println(new Date() + " TaskFindIPOAll complete");
	}

	private void loadAllCorps() throws JsonParseException, JsonMappingException, IOException {
		Set<String> corpsStr = jedis.smembers(Constant.keyCorps);
		ObjectMapper om = new ObjectMapper();
		for (String corpStr : corpsStr) {
			Corp corp = om.readValue(corpStr, Corp.class);
			corps.add(corp);
		}
	}

	public void compareNewsAndCorp(String shenfeng, String news) {
		for (Corp corp : corps) {
			if (news.indexOf(corp.name) > -1) {
				System.out.println("find!!!!:" + shenfeng + ":" + corp.no + "(" + corp.name + ")" + news);
				jedis.hset("find-" + corp.no, news, corp.name);
				jedis.hset("find-" + corp.no, "send", "false");
			}
		}
	}
}
