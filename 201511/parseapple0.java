package appledaily;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parseapple0 {
	public static void main(String[] args) throws IOException {
		Document doc;
		String patternStr = "(.+)\\((\\d+)\\)";
		String appleStr = "(.+)\\((\\d+)\\)";
		Pattern pattern = Pattern.compile(patternStr);
		Pattern pattern2 = Pattern.compile(appleStr);
		
		String domain = "http://www.appledaily.com.tw";
		doc = Jsoup.connect(
				"http://www.appledaily.com.tw/realtimenews/section/new/").get();


		Elements rtddt = doc.select(".rtddt");
		for (Element li : rtddt) {
			Integer Popularity = 0;
			String title = li.select("h1").text();
			String category = li.select("h2").text();
			String time = li.select("time").text();
			String link =  li.select("a").attr("href");
			
			Matcher matcher = pattern.matcher(title);
			boolean matchFound = matcher.find();
			if (matchFound) {
				Popularity = Integer.parseInt(matcher.group(2));
			}
			Matcher matcher2 = pattern.matcher(link);
			boolean matchFound2 = matcher2.find();
			if (!matchFound2) {
				link = domain + link;
			}
			
			System.out.println(title + " " + category + " " + time + " "
					+ link + " " + Popularity.toString());
		}
	}
}