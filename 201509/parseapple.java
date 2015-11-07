package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parseapple {
	   public static void main(String[] args) throws IOException{
		   Document doc;
			doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();
			Elements rtddt = doc.select(".rtddt"); //id -> #, class -> .
			for(Element li: rtddt){
				String title = li.select("h1").text();
				String time = li.select("time").text();
				String link = li.select("a").attr("href");
				
				System.out.println(title + " " + time + " " + link);
			}
	   
	   }
}
