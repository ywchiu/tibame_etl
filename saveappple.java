package appledaily;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class saveappple {
	public static void main(String args[]) throws IOException, ParseException, SQLException{
		Document doc;
		doc = Jsoup.connect("http://www.appledaily.com.tw/realtimenews/article/politics/20160617/887880/%E3%80%90%E7%B2%BE%E5%BD%A9%E7%89%87%E3%80%91%E8%B3%B4%E6%B8%85%E5%BE%B7%E5%86%B7%E5%9B%9E%E9%BE%8D%E4%BB%8B%E4%BB%99%E3%80%80%E3%80%8C%E4%B8%8D%E6%98%AF%E5%A4%A7%E8%81%B2%E5%B0%B1%E6%9C%83%E8%B4%8F%E3%80%8D").get();
		String  title = doc.select("#h1").text();
		String  content = doc.select("#summary").text();
		String dt = doc.select(".gggs time").text();
		String clicked = doc.select(".clicked").text();
		
		String patternStr = "人氣\\((\\d+)\\)";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(clicked);
		Integer view_cnt = 0;
		boolean matchFound = matcher.find();
		if (matchFound) {
			String popularity = matcher.group(1);
		//System.out.println(matcher.group(1));
			view_cnt = Integer.parseInt(popularity);
			//System.out.println(Integer.parseInt(popularity));
		}

		//System.out.println(title + " " + dt + " " + clicked);
		
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日hh:mm");
		//formatter.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		//Date dt2 = formatter.parse(dt);
		//System.out.println(dt2);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy年MM月dd日HH:mm");
		DateTime dt2 = DateTime.parse(dt, fmt);
		
		
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
		pst.setString(1, title);
		pst.setString(2, content);
		pst.setTimestamp(3,  new Timestamp(dt2.toDate().getTime()));
		pst.setInt(4,  view_cnt);
		pst.setString(5,  "123");
		pst.execute();
		//System.out.println(dt );

	}
}
