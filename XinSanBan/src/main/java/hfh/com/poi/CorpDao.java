package hfh.com.poi;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class CorpDao {
	Jedis jedis = new Jedis(Constant.redisHost);
	ObjectMapper om = new ObjectMapper();
	private Set<Corp> corps = new HashSet<Corp>();
	
	private void loadAllCorps() throws IOException {
		corps.clear();
		Set<String> corpsStr = jedis.smembers(Constant.keyCorps);
		ObjectMapper om = new ObjectMapper();
		for (String corpStr : corpsStr) {
			Corp corp = om.readValue(corpStr, Corp.class);
			corps.add(corp);
		}
	}
	
	//返回多个。有时候证券公司会匹配”关于**证券辅导**公司”的title
	public Set<Corp> findCorpByNews(News news) throws IOException {
		loadAllCorps();
		for (Corp corp : corps) {
			if (news.getTitle().indexOf(corp.name) > -1) {
				corps.add(corp);
				System.out.println("find!!!!:" + news.addr + ":" + corp.no + "(" + corp.name + ")" + news);
			}
		}
		return corps;
	}
	
	public void save(Corp corp) throws JsonProcessingException {
		String corpJson = om.writeValueAsString(corp);
		jedis.sadd(Constant.keyCorps,  corpJson);
	}

	public Set<Corp> findCorpToSend() throws IOException {
		Set<Corp> corpsToSend = new HashSet<Corp>();
		loadAllCorps();
		for (Corp corp : corps) {
			if(corp.getSendStatus().equals(Constant.corpWaitSend))
			{
				corpsToSend.add(corp);
			}
		}
		return corpsToSend;
	}
}
