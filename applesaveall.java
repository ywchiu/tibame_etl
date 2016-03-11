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

public class applesaveall {
	public static void main(String[] args) throws IOException, SQLException {
		// Database Connection Configuration
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

		// Crawl appledaily news list
		Document doc;
		String domain = "http://www.appledaily.com.tw";
		for (int i = 1; i < 50; i++) {
			doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/section/new/" + Integer.toString(i)).get();

			Elements rtddt = doc.select(".rtddt a");
			for (Element li : rtddt) {
				String alink = li.select("a").attr("href");
				String category = li.select("h2").text();
				getDetail(domain + alink, category, pst);
			}
		}
		con.close();
	}

	public static void getDetail(String alink, String category, PreparedStatement pst)
			throws IOException, SQLException {
		// Crawl appledaily news detail
		int view_cnt = 0;
		Document doc;
		doc = Jsoup.connect(alink).get();

		String title = doc.select("#h1").text();
		String content = doc.select("div.articulum.trans").text();
		String time = doc.select("div.gggs > time").text();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy年MM月dd日HH:mm");
		DateTime dt = DateTime.parse(time, fmt);
		String popularity = doc.select(".clicked").text();

		String patternStr = "(.+)\\((\\d+)\\)";
		Pattern pattern = Pattern.compile(patternStr);

		Matcher matcher = pattern.matcher(popularity);
		boolean matchFound = matcher.find();
		if (matchFound) {
			view_cnt = Integer.parseInt(matcher.group(2));
		}

		try {
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setTimestamp(3, new Timestamp(dt.toDate().getTime()));
			pst.setInt(4, view_cnt);
			pst.setString(5, category);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
