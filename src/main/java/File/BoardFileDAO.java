package File;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.BoardVO;

public class BoardFileDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;


	public BoardFileDAO() {
		try {

			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/pro05DB");
			conn = dataFactory.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	public List<BoardFileVO> selectAllArticles() {
//		List<BoardFileVO>  list = new ArrayList();
//		try {
//			conn = dataFactory.getConnection();
//			String query = "SELECT * from "
//					+ " from boardfile"
//					+ " where 'number' = ?";
//			System.out.println(query);
//
//			pstmt = conn.prepareStatement(query);
//			pstmt(1, number);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				BoardFileVO boardFile =new BoardFileVO(rs.getInt("f_id"),
//						rs.getInt("num")
//						, rs.getString("writeId"),
//						
//						
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				String id = rs.getString("id");
//				Date writeDate = rs.getDate("writeDate");
//				String category =rs.getString("category");
//				BoardVO article = new BoardVO();
//				
//			
//				article.setNum(num);
//				article.setTitle(title);
//				article.setContent(content);
//				article.setId(id);
//				article.setWriteDate(writeDate);
//				article.setCategory(category);
//				articlesList.add(article);
//			}
//			rs.close();
//			pstmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return articlesList;
//	}

}
