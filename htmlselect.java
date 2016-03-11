package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class htmlselect {
	   public static void main(String[] args) throws IOException{
		    Document doc;
			doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
			//String html = doc.select("h1").text();
			//String html = doc.select("#firstHeading").text();//id=firstHeading
			//String html = doc.select(".firstHeading").text();//class=firstHeading
			
			String html = doc.select("#mp-topbanner > tbody > tr > td:nth-child(1) > table > tbody > tr > td > div:nth-child(1)").text();
			System.out.println(html);	   
	   }
}
