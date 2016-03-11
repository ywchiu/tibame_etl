package appledaily;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class fbrequest {
	public static void main(String[] args) throws IOException, SQLException, ParseException {
		String ACCESSTOKEN = "<KEY>|<SECRET>";
		String doc = Jsoup.connect("https://graph.facebook.com/v2.5/1006565249406176/comments?filter=stream&access_token=" + ACCESSTOKEN)
				.ignoreContentType(true).execute().body();
		
		//System.out.println(doc);
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
