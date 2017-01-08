package hfh.com.poi;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class CorpDao {
	Jedis jedis = new Jedis(Constant.redisHost);
	ObjectMapper om = new ObjectMapper();
	
	
	public void loadAllCorps(Set<Corp> corps) {
		corps.clear();
		Set<String> keysCorps = jedis.keys(Constant.keyCorp + "*");
		ObjectMapper om = new ObjectMapper();
		for (String keyCorp : keysCorps) {
			String corpStr = jedis.get(keyCorp);
			Corp corp;
			try {
				corp = om.readValue(corpStr, Corp.class);
				corps.add(corp);
			} catch (IOException e) {
				//TODO
				e.printStackTrace();
			}
		}
		System.out.println("loadAllCorps:" + corps.size());
	}
	
	//返回多个。有时候证券公司会匹配”关于**证券辅导**公司”的title
	public Set<Corp> findCorpByNews(News news, Set<Corp> corps) throws IOException {
		Set<Corp> corpsByNews = new HashSet<Corp>();
		for (Corp corp : corps) {
			if (news.getTitle().indexOf(corp.name) > -1) {
				corpsByNews.add(corp);
				System.out.println("find!!!!:" + news.addr + ":" + corp.no + "(" + corp.name + ")" + news);
			}
		}
		return corpsByNews;
	}
	
	public void save(Corp corp) throws JsonProcessingException {
		String corpJson = om.writeValueAsString(corp);
		jedis.set(Constant.keyCorp + corp.getNo(),  corpJson);
	}

	public Set<Corp> findCorpToSend() throws IOException {
		Set<Corp> corpsToSend = new HashSet<Corp>();
		Set<Corp> corps = new HashSet<Corp>();
		loadAllCorps(corps);
		for (Corp corp : corps) {
			
			if(corp.getSendStatus().equals(Constant.corpWaitSend))
			{
				corpsToSend.add(corp);
			}
		}
		return corpsToSend;
	}
}
