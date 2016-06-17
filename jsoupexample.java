package appledaily;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class jsoupexample {
	public static void main(String args[]) throws IOException{
		String html = "<html> <body> <h1 id=\"title\">Hello World</h1> <a href=\"#\" class=\"link\">This is link1</a> <a href=\"# link2\" class=\"link\">This is link2</a> </body> </html>";
		Document doc = Jsoup.parse(html);
		String h1 = doc.select("h1").text();
		String a = doc.select("a").text();
		String title = doc.select("#title").text(); //id => #
		String link = doc.select(".link").text(); // class => .
		System.out.println(link);

	}
}
