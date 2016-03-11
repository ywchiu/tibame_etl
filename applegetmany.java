package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class applegetmany {
	public static void main(String[] args) throws IOException {
		Document doc;
		for (int i = 1; i < 50; i++) {
			doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/" + Integer.toString(i)).get();

			Elements rtddt = doc.select(".rtddt a");
			for (Element li : rtddt) {
				String title = li.select("h1").text();
				String category = li.select("h2").text();
				String dt = li.select("time").text();
				System.out.println(title + " " + category + " " + dt);
			}
		}
	}
}
