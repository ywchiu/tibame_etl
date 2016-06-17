package appledaily;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class fbget {
	   public static void main(String[] args) throws IOException, ParseException{
		    String doc;
		    String ACCESSTOKEN = "1573154736309471|<ACCESSSECRET>";
			doc = Jsoup.connect("https://graph.facebook.com/v2.5/1051758401563219/comments?access_token="+ ACCESSTOKEN + "&filter=stream&format=json")
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
