package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class fbrequest {
   public static void main(String[] args) throws IOException{
	   String doc;
	   String ACCESSTOKEN = "";
		doc = Jsoup.connect("https://graph.facebook.com/v2.4/1053352801350345/comments?access_token=" + ACCESSTOKEN)
				.ignoreContentType(true).execute().body();
				
		System.out.println(doc);	   
   }
}
