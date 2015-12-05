package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class htmlpost {

	   public static void main(String[] args) throws IOException{
		    Document doc;
			doc = Jsoup.connect("https://www.thsrc.com.tw/tw/TimeTable/SearchResult")
					.data("StartStation", "977abb69-413a-4ccf-a109-0272c24fd490")
					.data("EndStation", "fbd828d8-b1da-4b06-a3bd-680cdca4d2cd")
					.data("SearchDate", "2015/12/05")
					.data("SearchTime", "11:00")
					.data("SearchWay", "DepartureInMandarin")
					.validateTLSCertificates(false)
					.post();

			String html = doc.html();
			System.out.println(html);	   
	   }
}
