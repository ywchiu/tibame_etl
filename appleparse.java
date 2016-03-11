package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class appleparse {
	   public static void main(String[] args) throws IOException{
		    Document doc;
			doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();
			//String html = doc.select(".rtddt a").text();
			//System.out.println(html);	
			Elements rtddt = doc.select(".rtddt a");
			for(Element li: rtddt){
				//String a = li.select("a").text();
				//System.out.println(a);
				String title = li.select("h1").text();
				String category = li.select("h2").text();
				String dt = li.select("time").text();
				System.out.println(title + " " + category + " " + dt);
			}
	   }
}
