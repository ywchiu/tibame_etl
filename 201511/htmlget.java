package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class htmlget {
	   public static void main(String[] args) throws IOException{
		    Document doc;
			doc = Jsoup.connect("http://en.wikipedia.org/").get();
			String html = doc.select(".firstHeading").text();
			System.out.println(html);	   
	   }
}
