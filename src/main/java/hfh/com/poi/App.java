package hfh.com.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.Timer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		long day = 1000 * 60 * 60 *24;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TaskImportCorp(), 1, day);
		timer.scheduleAtFixedRate(new TaskImportNewsAll(), 10000, day);
		timer.scheduleAtFixedRate(new TaskImportNews(), 20000, day);
		timer.scheduleAtFixedRate(new TaskFindIPOAll(), 30000, day);
		
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
