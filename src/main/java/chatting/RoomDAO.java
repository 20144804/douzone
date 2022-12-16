package chatting;

import java.io.PrintWriter;
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

import board.BoardVO;


public class RoomDAO {
	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;
	
	public RoomDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/pro05DB");
//			conn = dataFactory.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> selecRoomNameList() {
		List<String> list = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT roomName from rooms ORDER BY seq ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String roomName = rs.getString("roomName");
				list.add(roomName);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int makeRoom(String roomName) {
		// TODO Auto-generated method stub
		int num=0;
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(true);
			String query = "insert into rooms(roomname)"
					+" values(?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, roomName);
		
			num = pstmt.executeUpdate();
			
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	public List<RoomVO> showName() {
		List list = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select roomName from rooms";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String roomName = rs.getString("roomName");
				RoomVO vo = new RoomVO();
		
				vo.setRoomName(roomName);
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

	
	
}
