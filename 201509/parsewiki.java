package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class parsewiki {
	   public static void main(String[] args) throws IOException{
		   Document doc;
			doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
			String title = doc.select("table").text();
			//String title = doc.select("table").html();
			System.out.println(title);	   
	   }
}
