package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class parsewiki {
	   public static void main(String[] args) throws IOException{
		    Document doc;
			doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
			String title = doc.select("title").text();
			String siteSub = doc.select("#siteSub").text();
			System.out.println(siteSub);
	   }
}
