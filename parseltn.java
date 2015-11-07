package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parseltn {
	public static void main(String[] args) throws IOException{
	    Document doc;
		doc = Jsoup.connect("http://news.ltn.com.tw/list/BreakingNews").get();
		Elements rtddt = doc.select(".lipic");
		for(Element li: rtddt){
			String title = li.select(".picword").text();
			//String category = li.select("h2").text();
			String time = li.select("span").text();
			//String a = li.select("a").text();
			System.out.println(title + " " + time);
		}
   }
}
