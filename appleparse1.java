package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class appleparse1 {
public static void main(String args[]) throws IOException{
	Document doc;
	doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();
    String ele = doc.select(".dddd time").text();
	String html = doc.html();
	System.out.println(ele);

}
}
