package appledaily;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parseapple2 {
	public static void main(String[] args) throws Exception {
		String patternStr = "(.+)\\((\\d+)\\)";
		String appleStr = "appledaily";
		Pattern pattern = Pattern.compile(patternStr);
		Pattern pattern2 = Pattern.compile(appleStr);
		Document doc;
		String domain = "http://www.appledaily.com.tw";
		doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();

		Elements rtddt = doc.select(".rtddt");
		for (Element li : rtddt) {
			String title = li.select("h1").text();
			String category = li.select("h2").text();
			String time = li.select("time").text();
			String link = li.select("a").attr("href");

			Matcher matcher = pattern.matcher(title);
			Matcher matcher2 = pattern2.matcher(link);
			boolean matchFound2 = matcher2.find();
			if (!matchFound2) {
				link = domain + li.select("a").attr("href");
			}
			boolean matchFound = matcher.find();
			if (matchFound) {
				// System.out.println(link);
				readarticle(link);
			}

		}
	}

	public static void readarticle(String article_url) throws Exception {
		Document detail = Jsoup.connect(article_url).get();
		Elements summary = detail.select("#summary");
		System.out.println(summary.text());

	}
}
