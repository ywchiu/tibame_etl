package appledaily;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class timeapple {
	public static void main(String[] args) throws Exception {
		Document doc;
		doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();
		String domain = "http://www.appledaily.com.tw";
		String html = doc.html();

		Elements rtddt = doc.select(".rtddt");

		for (Element li : rtddt) {
			String time = li.select("time").text();
			String author = li.select("h2").text();
			String title = li.select("h1").text();
			String link = li.select("a").attr("href");
			readarticle(domain + link);
			//System.out.println(time + " " + author + " " + title + " " + domain + link);
		}
	}
	
	public static void readarticle(String article_url) throws Exception{
		
		Document doc;
		doc = Jsoup.connect(article_url).get();		
		
		// 2015年09月20日12:37
		String summary = doc.select("#summary").text();
		String time = doc.select(".gggs time").text() ;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日hh:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		Date dt = formatter.parse(time);

		System.out.println(dt);
	}
}