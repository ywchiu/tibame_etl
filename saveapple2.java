package appledaily;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class saveapple2 {
	public static void main(String args[]) throws IOException, SQLException {

		// Build connection to database
		Connection con = null;
		PreparedStatement pst = null;
		String insertdbSQL = "insert into news_main(title, content, time, view_cnt, category) " + "values(?,?,?, ?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/appledaily", "root", "test");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pst = (PreparedStatement) con.prepareStatement(insertdbSQL);

		// Crawl data from apple daily
		Document doc;
		String domain = "http://www.appledaily.com.tw";

		for (int i = 1; i < 51; i++) {
			doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/" + i).get();

			Elements rtddt = doc.select(".rtddt a");
			for (Element li : rtddt) {
				String category = li.select("h2").text();
				String link = domain + li.select("a").attr("href");
				saveDetail(link, category, pst);
			}
		}

		con.close();

	}

	public static void saveDetail(String link, String category, PreparedStatement pst)
			throws IOException, SQLException {
		Integer view_cnt = 0;
		Document doc;
		doc = Jsoup.connect(link).get();
		String title = doc.select("#h1").text();
		String content = doc.select("#summary").text();
		String dts = doc.select(".gggs time").text();
		String clicked = doc.select(".clicked").text();

		String patternStr = "人氣\\((\\d+)\\)";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(clicked);

		boolean matchFound = matcher.find();
		if (matchFound) {
			String popularity = matcher.group(1);
			view_cnt = Integer.parseInt(popularity);
		}

		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy年MM月dd日HH:mm");
		DateTime dt = DateTime.parse(dts, fmt);
		// System.out.println(title + " " + category + " " + dts + " " +
		// view_cnt);

		// Save record into database table
		pst.setString(1, title);
		pst.setString(2, content);
		pst.setTimestamp(3, new Timestamp(dt.toDate().getTime()));
		pst.setInt(4, view_cnt);
		pst.setString(5, category);
		pst.execute();

	}
}
