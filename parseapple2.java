package appledaily;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parseapple2 {
	public static void main(String[] args) throws IOException {
		Document doc;
		doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();
		Elements rtddt = doc.select(".rtddt"); // id -> #, class -> .
		for (Element li : rtddt) {
			String link = li.select("a").attr("href");
			String article_link = "http://www.appledaily.com.tw" + link;
			getArticle(article_link);
		
		}

	}
	
	public static void getArticle(String article_link) throws IOException {
			Document doc2 = Jsoup.connect(article_link).get();
			String title = doc2.select("#h1").text(); // id -> #, class -> .
			String summary = doc2.select("#summary").text(); // id -> #, class -> .
			String time = doc2.select(".gggs time").text(); // id -> #, class -> .
			String popularity = doc2.select(".clicked").text(); // id -> #, class -> .
			String patternStr = "(.+)\\((\\d+)\\)";
			Pattern pattern = Pattern.compile(patternStr);
			Matcher matcher = pattern.matcher(popularity);
			boolean matchFound = matcher.find();
			if (matchFound) {

			System.out.println(title + "¡@" + summary + " " + time + "" + Integer.parseInt(matcher.group(2)));
			}

	}
}
