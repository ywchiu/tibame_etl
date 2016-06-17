package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class appleparse2 {
public static void main(String args[]) throws IOException{
	Document doc;
	doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();
    //String ele = doc.select(".rtddt a").text();
	Elements rtddt = doc.select(".rtddt a"); // id -> #, class -> .
	for (Element li : rtddt) {
		String title = li.select("h1").text();
		String category = li.select("h2").text();
		System.out.println(title + " " + category);
	}
	

}
}
