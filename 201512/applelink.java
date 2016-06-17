package appledaily;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class applelink {
	   public static void main(String[] args) throws IOException{
		    Document doc;
		    String domain = "http://www.appledaily.com.tw";
			doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();
	
			Elements rtddt = doc.select(".rtddt a");
			for(Element li: rtddt){
				String alink = li.select("a").attr("href");
				String category = li.select("h2").text();
				getDetail(domain + alink, category);
			}
	   }
		public static void getDetail(String alink, String category) throws IOException {
			Document doc;
			DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy¦~MM¤ëdd¤éHH:mm");
			
			doc = Jsoup
					.connect(alink)
					.get();
			String title = doc.select("#h1").text();
			String content = doc.select("div.articulum.trans").text();
			String time = doc.select("div.gggs > time").text();
			DateTime dt = DateTime.parse(time, fmt);
			System.out.println(title + " " + dt + " " + category+ " " +content);

		}
}
