package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class appleget {
	   public static void main(String[] args) throws IOException{
		    Document doc;
			doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();
			String html = doc.html();
			System.out.println(html);	   
	   }
}
