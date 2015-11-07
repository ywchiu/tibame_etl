package appledaily;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parseapple3 {
	public static void main(String[] args) throws Exception {
		String appleStr = "appledaily";
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

			Matcher matcher2 = pattern2.matcher(link);
			boolean linkfound = matcher2.find();
			if (!linkfound) {
				link = domain + li.select("a").attr("href");
			}
			readarticle(link);
		}
	}

	public static void readarticle(String article_url) throws Exception {
		Document detail = Jsoup.connect(article_url).get();
		String title = detail.select("#h1").text();
		String summary = detail.select("#summary").text();
		String time = detail.select(".gggs time").text();
		String popularity = "";

		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy¶~MM§Îdd§ÈHH:mm");
		DateTime dt = DateTime.parse(time, fmt);
		String patternStr = "(.+)\\((\\d+)\\)";

		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(detail.select(".clicked").text());
		boolean matchFound = matcher.find();
		if (matchFound) {
			popularity = matcher.group(2);
		}
		System.out.println(title + " " + popularity + " " + dt);

	}
}
