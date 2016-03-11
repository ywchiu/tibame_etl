package appledaily;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.json.simple.parser.ParseException;
public class nlparse {
	public static void main(String[] args) throws IOException, SQLException, ParseException {
	 
		List<Term> parse = ToAnalysis.parse("让战士们过一个欢乐祥和的新春佳节。");
	    System.out.println(parse);
	}
}
