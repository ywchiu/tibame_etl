package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parseapple {
	   public static void main(String[] args) throws IOException{
		    Document doc;
		    String domain = "http://www.appledaily.com.tw";
			doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();
			//String title = doc.select("title").text();
			//System.out.println(title);

			Elements rtddt = doc.select(".rtddt");
			for(Element li: rtddt){
				String title = li.select("h1").text();
				String category = li.select("h2").text();
				String time = li.select("time").text();
				String link = domain + li.select("a").attr("href");
				//String a = li.select("a").text();
				System.out.println(title + " " +category + " " + time + " " + link);
			}
	   }
}
