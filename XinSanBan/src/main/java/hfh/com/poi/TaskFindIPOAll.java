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

public class TaskFindIPOAll extends TimerTask {
	CorpDao corpDao = new CorpDao();

	@Override
	public void run() {
		try {
			System.out.println(new Date() + " TaskFindIPOAll begin");
			Set<Corp> corps = corpDao.findCorpToSend();
			for (Corp corp : corps) {
				System.out.println(corp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new Date() + " TaskFindIPOAll complete");
	}
}
