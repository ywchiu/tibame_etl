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

public class applesave {
	public static void main(String[] args) throws IOException, SQLException {		
		Connection con = null;
		PreparedStatement pst = null;
		String insertdbSQL = "insert into news_main(title, content, time, view_cnt, category) "
				+ "values(?,?,?, ?,?)";
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy¦~MM¤ëdd¤éHH:mm");

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/appledaily", "root", "test");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pst = (PreparedStatement) con.prepareStatement(insertdbSQL);
		
		Document doc;
		doc = Jsoup
				.connect(
						"http://www.appledaily.com.tw/realtimenews/article/local/20160311/813798/%E6%93%94%E5%BF%83%E8%A2%AB%E5%81%B7%E6%96%A4%E6%B8%9B%E5%85%A9%E3%80%80%E9%81%95%E5%81%9C%E7%8F%BE%E7%A7%A4%E5%85%A8%E6%B2%92%E6%94%B6")
				.get();
		String title = doc.select("#h1").text();
		String content = doc.select("div.articulum.trans").text();
		String time = doc.select("div.gggs > time").text();
		String popularity = doc.select(".clicked").text();
		int view_cnt = 0 ;

		String patternStr = "(.+)\\((\\d+)\\)";
		Pattern pattern = Pattern.compile(patternStr);

		Matcher matcher = pattern.matcher(popularity);
		boolean matchFound = matcher.find();
		if (matchFound) {
			view_cnt =  Integer.parseInt(matcher.group(2));
		}
		//System.out.println(view_cnt);
		DateTime dt = DateTime.parse(time, fmt);
		
		//System.out.println(link);
		pst.setString(1, title);
		pst.setString(2, content);
		pst.setTimestamp(3, new Timestamp(dt.toDate().getTime()));
		pst.setInt(4, view_cnt);
		pst.setString(5, "test");
		pst.execute();
		con.close();

	}
}
