package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//import join1.MemberBean;



public class BoardFileDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public BoardFileDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/pro05DB");
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//게시물에 속한 첨부파일 목록 
	public List<BoardFileVO> list(int number) {
		List<BoardFileVO> list = new ArrayList<>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from boardfile where num = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, number);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
//				String real_path[]=rs.getString("real_name").split("\\\\");
				BoardFileVO boardFile = new BoardFileVO(
						rs.getInt("f_id"),	
						rs.getInt("num"),	
						rs.getString("org_name"),
//						real_path[real_path.length-1],
						rs.getString("real_name"),
						rs.getString("content_type"),
						rs.getLong("length"));
				System.out.println(boardFile);
				list.add(boardFile);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
//게시물 등록
	public void insertBoardFile(BoardFileVO boardFile) throws SQLException {
		conn = dataFactory.getConnection();
		String query = "insert into boardfile (num, org_name, real_name, content_type, length) VALUES(?,?,?,?,?)";
		System.out.println("prepareStatememt: " + query);
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, boardFile.getNum());
		pstmt.setString(2, boardFile.getOrg_name());
		pstmt.setString(3, boardFile.getReal_name());
		pstmt.setString(4, boardFile.getContent_type());
		pstmt.setLong(5, boardFile.getLength());
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.commit();
	}
	
	public void close() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}

//	public BoardFileVO getFile(int num) {
//		BoardFileVO boardFile=null;
//		// TODO Auto-generated method stub
//		try {
//			// connDB();
//			String query = "select * from boardfile where `num` = ?";
//			System.out.println("prepareStatememt: " + query);
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1,  num);
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				boardFile = new BoardFileVO(
//						rs.getInt("f_id"),	
//						rs.getInt("num"),	
//						rs.getString("org_name"),
//						rs.getString("real_name"),
//						rs.getString("content_type"),
//						rs.getLong("length"));
//				System.out.println(boardFile);
//		
//			}
//			rs.close();
//			pstmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return boardFile;
//	}

	

}