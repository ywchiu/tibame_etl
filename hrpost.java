package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class hrpost {
	public static void main(String args[]) throws IOException{
		Document doc;
		doc = Jsoup.connect("https://www.thsrc.com.tw/tw/TimeTable/SearchResult")
	    	      .data("StartStation", "977abb69-413a-4ccf-a109-0272c24fd490")
	    	      .data("EndStation", "60831846-f0e4-47f6-9b5b-46323ebdcef7")
	    	      .data("SearchDate", "2016/06/17")
	    	      .data("SearchTime", "10:30")
	    	      .data("SearchWay", "DepartureInMandarin")
	    	      .post();
  
		String html = doc.html();
		System.out.println(html);

	}
}
