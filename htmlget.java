package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class htmlget {
	   public static void main(String[] args) throws IOException{
		    Document doc;
			doc = Jsoup.connect("http://120.97.6.98:8000/").get();
			String html = doc.html();
			System.out.println(html);	   
	   }
}
