package com.hfh.xinsanban;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Constant {

	public static String NeeqFilePath = "d:\\neeq.xlsx";
	public static String FUNC = "ALL";
	
	public static String redisHost = "192.168.0.107";
	public static String keyCorp = "corp-";
	public static String MailRecive = "hfhwan@163.com";
	
//	public static Set<String> corps = new HashSet<String>();
	
	//N-不要发 W-等待发 Y-已经发
	public static String corpNoSend = "N";
	public static String corpWaitSend = "W";
	public static String corpSended = "Y";
	
	public static Map<String, String> ShenFengUrl = new HashMap<String, String>();
	static {
		Properties properties = new Properties();
		try {
//			properties.load( Constant.class.getClassLoader().getResourceAsStream("config.properties") );
			properties.load( new FileInputStream("config.properties") );
			Constant.NeeqFilePath = properties.getProperty("NeeqFilePath");
			Constant.FUNC = properties.getProperty("FUNC");
			Constant.redisHost = properties.getProperty("redisHost");
			Constant.MailRecive = properties.getProperty("MailRecive");
			
			System.out.println(Constant.NeeqFilePath);
			System.out.println(Constant.FUNC);
			System.out.println(Constant.redisHost);
			System.out.println(Constant.MailRecive);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("config.properties read error, use default.");
		}
		
		ShenFengUrl.put("beijing" , "http://www.csrc.gov.cn/pub/beijing/bjfdqyxx/bjfdqyjbqk/"         );
		ShenFengUrl.put("tianjin" , "http://www.csrc.gov.cn/pub/tianjin/tjfdqyxx/tjfdqyjbqk/"         );
		ShenFengUrl.put("heibei" , "http://www.csrc.gov.cn/pub/hebei/hbfdqyxx/hbfdqyjbqk/"           );
		ShenFengUrl.put("shanxi" , "http://www.csrc.gov.cn/pub/shanxi/sxfdqyxx/sxfdqyjbqk/"          );
		ShenFengUrl.put("neimenggu" , "http://www.csrc.gov.cn/pub/neimenggu/nmgfdqyxx/nmgfdqyjbqk/"     );
		ShenFengUrl.put("liaoning" , "http://www.csrc.gov.cn/pub/liaoning/lnfdqyxx/lnfdqyjbqk/"        );
		ShenFengUrl.put("heilongjiang" , "http://www.csrc.gov.cn/pub/heilongjiang/hljfdqyxx/hljfdqyjbqk/"  );
		ShenFengUrl.put("shanghai" , "http://www.csrc.gov.cn/pub/shanghai/shfdqyxx/shfdqyjbqk/"        );
		ShenFengUrl.put("jiangsu" , "http://www.csrc.gov.cn/pub/jiangsu/jsfdqyxx/jsfdqyjbqk/"         );
		ShenFengUrl.put("zhejiang" , "http://www.csrc.gov.cn/pub/zhejiang/zjfdqyxx/zjfdqyjbqk/"        );
		ShenFengUrl.put("jiangxi" , "http://www.csrc.gov.cn/pub/jiangxi/jxfdqyxx/jxfdqyjbqk/"         );
		ShenFengUrl.put("shandong" , "http://www.csrc.gov.cn/pub/shandong/sdfdqyxx/sdfdqyjbqk/"        );
		ShenFengUrl.put("henan" , "http://www.csrc.gov.cn/pub/henan/hnfdqyxx/hnfdqyjbqk/"           );
		ShenFengUrl.put("hunan" , "http://www.csrc.gov.cn/pub/hunan/hunfdqyxx/"                     );
		ShenFengUrl.put("guangdong" , "http://www.csrc.gov.cn/pub/guangdong/gdfdqyxx/gdfdqyjbqk/"       );
		ShenFengUrl.put("chongqing" , "http://www.csrc.gov.cn/pub/chongqing/chqfdqyxx/chqfdqyjbqk/"     );
		ShenFengUrl.put("sichuan" , "http://www.csrc.gov.cn/pub/sichuan/scfdqyxx/scfdqyjbqk/"         );
		ShenFengUrl.put("yunnan" , "http://www.csrc.gov.cn/pub/yunnan/yunfdqyxx/yunfdqyjbqk/"        );
		ShenFengUrl.put("xizang" , "http://www.csrc.gov.cn/pub/xizang/xizfdqyxx/xizfdqyjbqk/"        );
		ShenFengUrl.put("gansu" , "http://www.csrc.gov.cn/pub/gansu/gasfdqyxx/gasfdqyjbqk/"         );
		ShenFengUrl.put("qinghai" , "http://www.csrc.gov.cn/pub/qinghai/qihfdqyxx/qihfdqyjbqk/"       );
		ShenFengUrl.put("ningxia" , "http://www.csrc.gov.cn/pub/ningxia/nixfdqyxx/nixfdqyjbqk/"       );
		ShenFengUrl.put("xinjiang" , "http://www.csrc.gov.cn/pub/xinjiang/xijfdqyxx/xijfdqyjbqk/"      );
		ShenFengUrl.put("shenzhen" , "http://www.csrc.gov.cn/pub/shenzhen/shzfdqyxx/shzfdqyjbqk/"      );
		ShenFengUrl.put("dalian" , "http://www.csrc.gov.cn/pub/dalian/dlfsqyxx/dlfdqyjbqk/"          );
		ShenFengUrl.put("ningbo" , "http://www.csrc.gov.cn/pub/ningbo/nibfdqyxx/nibfdqyjbqk/"        );
	}
	
	public static String getDate()
	{
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String sDate = df.format(date);
		return sDate;
	}
}
