package appledaily;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class saveapple {
	public static void main(String[] args) throws IOException, SQLException {
		Document doc;
		String appleStr = "appledaily";
		String domain = "http://www.appledaily.com.tw";
		Pattern pattern2 = Pattern.compile(appleStr);
		
		
		Connection con = null;
		PreparedStatement pst = null;
		String insertdbSQL = "insert into news_main(title, content, time, view_cnt, category) "
				+ "values(?,?,?, ?,?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/appledaily", "root", "test");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pst = (PreparedStatement) con.prepareStatement(insertdbSQL);
		

		doc = Jsoup.connect(
				"http://www.appledaily.com.tw/realtimenews/section/new/").get();

		Elements rtddt = doc.select(".rtddt");
		for (Element li : rtddt) {
			
			String category = li.select("h2").text();
			String link = li.select("a").attr("href");

			Matcher matcher2 = pattern2.matcher(link);
			boolean matchFound2 = matcher2.find();
			if (!matchFound2) {
				link = domain + link;
			}

			readarticle(link, pst, category);
		}
		con.close();
	}

	public static void readarticle(String link, PreparedStatement pst, String category) throws IOException, SQLException {
		Integer view_cnt = 0;
		String patternStr = "(.+)\\((\\d+)\\)";
		Pattern pattern = Pattern.compile(patternStr);
		
		Document detail = Jsoup.connect(link).get();
		String time  = detail.select(".gggs time").text();
		String summary = detail.select("#summary").text();
		String title = detail.select("#h1").text();
		String clicked = detail.select(".clicked").text();
		
		Matcher matcher = pattern.matcher(clicked);
		boolean matchFound = matcher.find();
		if (matchFound) {
			view_cnt = Integer.parseInt(matcher.group(2));
		}
		
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy¶~MM§Îdd§ÈHH:mm");
		DateTime dt = DateTime.parse(time, fmt);
		System.out.println(link);
		pst.setString(1, title);
		pst.setString(2, summary);
		pst.setTimestamp(3, new Timestamp(dt.toDate().getTime()));
		pst.setInt(4, view_cnt);
		pst.setString(5, category);
		pst.execute();

	}
}