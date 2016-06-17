package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ltn {
public static void main(String args[]) throws IOException{
	Document doc;
	doc = Jsoup.connect("http://news.ltn.com.tw/list/BreakingNews").get();
    //String ele = doc.select(".rtddt a").text();
	Elements rtddt = doc.select(".lipic"); // id -> #, class -> .
	for (Element li : rtddt) {
		String title = li.select("a").text();
		String dt = li.select("span").text();
		System.out.println(title + " " + dt);
	}
	

}
}
