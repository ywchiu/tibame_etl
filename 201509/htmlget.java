package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class htmlget {
   public static void main(String[] args) throws IOException{
	   Document doc;
		doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
		String html = doc.html();
		System.out.println(html);

	   
		   
   }
}
