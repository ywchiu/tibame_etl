package appledaily;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class fbrequest {
   public static void main(String[] args) throws IOException, ParseException{
	   String doc;
	   String ACCESSTOKEN = "<APPKEY>|<APPSECRET>";
		//doc = Jsoup.connect("https://graph.facebook.com/v2.5/DoctorKoWJ/posts?access_token=" + ACCESSTOKEN)
		//		.ignoreContentType(true).execute().body();
		doc = Jsoup.connect("https://graph.facebook.com/v2.5/981189421947301/comments?access_token=" + ACCESSTOKEN)
				.ignoreContentType(true).execute().body();
				
		JSONParser parser =new JSONParser();
		Object obj=parser.parse(doc);
		JSONObject jsonObject=(JSONObject)obj;
		JSONArray dt=(JSONArray)jsonObject.get("data");
		Iterator<JSONObject> fbiter = dt.iterator();
		while(fbiter.hasNext()){
			JSONObject fbjson = fbiter.next();
			System.out.println(fbjson.get("message"));
		}
		
		
   }
}