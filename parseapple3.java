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
	public static void main(String[] args) throws IOException {
		Document doc;
		String appleStr = "appledaily";
		String domain = "http://www.appledaily.com.tw";
		
		Pattern pattern2 = Pattern.compile(appleStr);

		doc = Jsoup.connect(
				"http://www.appledaily.com.tw/realtimenews/section/new/").get();

		Elements rtddt = doc.select(".rtddt");
		for (Element li : rtddt) {
			
			String category = li.select("h2").text();
			String link = li.select("a").attr("href");

			Matcher matcher2 = pattern2.matcher(link);
			boolean matchFound2 = matcher2.find();
			if (!matchFound2) {
				link = domain + link;
			}

			readarticle(link);
		}
	}

	public static void readarticle(String link) throws IOException {
		Integer popularity = 0;
		String patternStr = "(.+)\\((\\d+)\\)";
		Pattern pattern = Pattern.compile(patternStr);
		
		Document detail = Jsoup.connect(link).get();
		String time  = detail.select(".gggs time").text();
		String summary = detail.select("#summary").text();
		String title = detail.select("#h1").text();
		String clicked = detail.select(".clicked").text();
		
		Matcher matcher = pattern.matcher(clicked);
		boolean matchFound = matcher.find();
		if (matchFound) {
			popularity = Integer.parseInt(matcher.group(2));
		}
		
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy¶~MM§Îdd§ÈHH:mm");
		DateTime dt = DateTime.parse(time, fmt);
		System.out.println(dt);
		System.out.println(title);
		System.out.println(popularity);

	}
}