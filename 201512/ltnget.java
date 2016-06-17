package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ltnget {
	   public static void main(String[] args) throws IOException{
		    Document doc;
			doc = Jsoup.connect("http://news.ltn.com.tw/list/BreakingNews").get();

			Elements rtddt = doc.select(".lipic");
			for(Element li: rtddt){
				String title = li.select(".picword").text();
				System.out.println(title);
			}
	   }
}
