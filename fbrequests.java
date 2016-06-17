package appledaily;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class fbrequests {
	public static void main(String[] args) throws IOException, ParseException {
		String doc;
		String ACCESSTOKEN = "";
		doc = Jsoup.connect("https://graph.facebook.com/v2.6/1026127657423850/comments?access_token=" + ACCESSTOKEN)
				.ignoreContentType(true).execute().body();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(doc);
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray dt = (JSONArray) jsonObject.get("data");
		 Iterator<JSONObject> driveIterator=dt.iterator();
		while(driveIterator.hasNext()) {
			 JSONObject driveJSON=driveIterator.next();
			String str1 = (String) driveJSON.get("created_time");
			JSONObject jo = (JSONObject) driveJSON.get("from");
			String n = (String) jo.get("name");
			System.out.println(n);

		}

		//System.out.println(doc);
	}
}