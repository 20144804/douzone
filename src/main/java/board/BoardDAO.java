package board;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;


	public BoardDAO() {
		try {

			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/pro05DB");
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List selectAllArticles() {
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT num,title,content,id,writeDate,category "
					+ " from board"
					+ " ORDER BY num";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
			
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				String category =rs.getString("category");
				BoardVO article = new BoardVO();
				
			
				article.setNum(num);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				article.setCategory(category);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}

	public int searchMaxNum() {
		// TODO Auto-generated method stub
		int num=0;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT max(num) as num from Board";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("num");
				num+=1;
			}
			
			int number=0;
		
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	public void addBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(true);
			String query = "insert into Board(num, id, title, category, content) "
					+" values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getNum());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getCategory());
			pstmt.setString(5, vo.getContent());
			int num = pstmt.executeUpdate();
			
			
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	   public int deleteBoard(int num1) {
		   int result=0;
		      try {
		         Connection con = dataFactory.getConnection();
		         String query = "delete from board where num=?";
		         System.out.println("prepareStatememt: " + query);
		         pstmt = con.prepareStatement(query);
		         pstmt.setInt(1, num1);
		         result= pstmt.executeUpdate();
		         
		        
		         pstmt.close();
		         con.close();
		         conn.close();
		         return result;
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		      return result;
		   }
	
	public BoardVO viewBoard(int boardNO) {
		BoardVO board = null;
		BoardDAO dao=new BoardDAO();
		board = dao.selectBoard(boardNO);
		return board;
	}
	
	public BoardVO selectBoard(int boardNO) {
		// TODO Auto-generated method stub
		BoardVO board=new BoardVO();
		try{
			conn = dataFactory.getConnection();
			String query ="select num,title,content,  writeDate, id, category"
				                     +" from board" 
				                     +" where num=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNO);
			ResultSet rs =pstmt.executeQuery();
			rs.next();
			
			int num =rs.getInt("num");
			String title = rs.getString("title");
			String content =rs.getString("content");
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
			String category = rs.getString("category");
			
			board.setNum(num);
			board.setTitle(title);
			board.setContent(content);
			board.setId(id);
			board.setWriteDate(writeDate);
			board.setCategory(category);
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();	
		}
			return board;
		}
	
	public String checkUpdateId(int num) {
		String checkId="";
		try {
			conn = dataFactory.getConnection();
			String query = "select id from board where num = ?";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
					checkId=rs.getString("id");
			}
			
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkId;
	}

	
	
	public int updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		int num=0;
		try {
		conn = dataFactory.getConnection();
		conn.setAutoCommit(true);
		String query = "update Board set title=?, category=?, content=? where num = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getCategory());
		pstmt.setString(3, vo.getContent());
		pstmt.setInt(4, vo.getNum());
		num = pstmt.executeUpdate();
		
		
		pstmt.close();
		conn.commit();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return num;
	}
	
	public List search(String sql, String searchContent) {
		List list = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,searchContent);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				String category =rs.getString("category");
				BoardVO vo = new BoardVO();
		
				vo.setNum(num);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id);
				vo.setWriteDate(writeDate);
				vo.setCategory(category);
				list.add(vo);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public List search2(String sql, String searchContent) {
		List list = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchContent+"%");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				String category =rs.getString("category");
				BoardVO vo = new BoardVO();
		
				vo.setNum(num);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id);
				vo.setWriteDate(writeDate);
				vo.setCategory(category);
				list.add(vo);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
//
//	public List<BoardVO> search(String sql) {
//		List list = new ArrayList();
//		try {
//			conn = dataFactory.getConnection();
//			System.out.println(sql);
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				int num = rs.getInt("num");
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				String id = rs.getString("id");
//				Date writeDate = rs.getDate("writeDate");
//				String category =rs.getString("category");
//				BoardVO vo = new BoardVO();
//		
//				vo.setNum(num);
//				vo.setTitle(title);
//				vo.setContent(content);
//				vo.setId(id);
//				vo.setWriteDate(writeDate);
//				vo.setCategory(category);
//				list.add(vo);
//			}
//			
//			rs.close();
//			pstmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	
	
	
	
	
}
