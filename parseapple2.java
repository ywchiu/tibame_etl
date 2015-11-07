package appledaily;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parseapple2 {
	   public static void main(String[] args) throws IOException{
		    Document doc;
		    String domain = "http://www.appledaily.com.tw";
			doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/").get();


			Elements rtddt = doc.select(".rtddt");
			for(Element li: rtddt){
				String title = li.select("h1").text();
				String category = li.select("h2").text();
				String time = li.select("time").text();
				String link = domain + li.select("a").attr("href");
				
				String patternStr = "(.+)\\((\\d+)\\)";
				Pattern pattern = Pattern.compile(patternStr);
				Matcher matcher = pattern.matcher(title);
				boolean matchFound = matcher.find();
				if (matchFound) {
					System.out.println(matcher.group(1) + " " +category + " " 
				+ time + " " + link + " " + matcher.group(2));
				}
				
			}
	   }
}
