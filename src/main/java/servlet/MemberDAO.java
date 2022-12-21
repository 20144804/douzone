package servlet;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;
	
	public MemberDAO() {
		try {
			
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/pro05DB");
//			conn = dataFactory.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void addMember(MemberBean member) throws SQLException {
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(true);
			String query = "insert into memberDB";
			query += " (uid, pwd, name,phone, email)";
			query += " values(?,?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUid());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public MemberBean viewMember(String id) {
		MemberBean memberBean = null;
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "select * from memberdb where uid = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
		
			
			if (rs.next()) {
				memberBean = new MemberBean(
						rs.getString("uid"),	
						rs.getString("pwd"),	
						rs.getString("name"),	
						rs.getString("email"),	
						rs.getString("phone"),
						rs.getDate("createDate"),
						rs.getString("allow"));
				
			}
			rs.close();
			return memberBean;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {}
		
		}
		return memberBean;
	}

	public Object listMembers() throws SQLException {
		// TODO Auto-generated method stub
		conn = dataFactory.getConnection();
		conn.close();
		return null;
	}

	public boolean isExisted(MemberBean member) {
	      boolean result = false;
	      String userid = member.getUid();
	      String pwd = member.getPwd();
	      try {
	    	  conn = dataFactory.getConnection();
	         String query = "select case count(*) when 1 then 'true' else 'false' end as result from memberdb";
	         query += " where uid=? and pwd=?";
	         System.out.println(query);
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, userid);
	         pstmt.setString(2, pwd);
	         ResultSet rs = pstmt.executeQuery();
	         System.out.println(userid+" "+pwd);
	         rs.next(); //커서를 첫번째 레코드로 위치시킵니다.
	         result = Boolean.parseBoolean(rs.getString("result"));
	         System.out.println("result=" + result);
	        
	         rs.close();
	         pstmt.close();
	         conn.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return result;
	   }

	public MemberBean showMember(String id) {
		MemberBean memberBean = null;
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "select uid,pwd,name,email,phone from memberdb where uid = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
		
			if (rs.next()) {
				memberBean = new MemberBean(
						rs.getString("uid"),	
						rs.getString("pwd"),	
						rs.getString("name"),	
						rs.getString("phone"),	
						rs.getString("email"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return memberBean;
	}

	public int deleteMemeber(String user_id) {
		int result = 0;
	  
	      try {
	         conn = dataFactory.getConnection();
	         conn.setAutoCommit(true);
	         String query = "delete from memberdb where uid=?";
	         System.out.println(query);
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, user_id);
	     
	         result = pstmt.executeUpdate();
	         
	         pstmt.close();
	         conn.commit();
	         conn.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	     return result;
	}

	public void updateMember(MemberBean memberBean, String sessionId) throws SQLException {
		// TODO Auto-generated method stub
		
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(true);
			String query = "update memberDB set ";
			query += " pwd = ?, name = ?, phone = ?, email = ?";
			query += " where uid = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberBean.getPwd());
			pstmt.setString(2, memberBean.getName());
			pstmt.setString(3, memberBean.getPhone());
			pstmt.setString(4, memberBean.getEmail());
			pstmt.setString(5, sessionId);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String findId(String name, String pwd, String email) throws SQLException{
		// TODO Auto-generated method stub
		String findId=null;
		String query = "select uid from memberdb where pwd = ? and name=? and email=?";
		System.out.println("prepareStatememt: " + query);
	
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				findId=	rs.getString("uid");	
			}
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return findId;
	}

	public String findPwd(String name, String id, String email) throws SQLException{
		// TODO Auto-generated method stub
		String findPwd=null;
		String query = "select pwd from memberdb where uid = ? and name=? and email=?";
		System.out.println("prepareStatememt: " + query);
	
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				findPwd=rs.getString("pwd");	
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return findPwd;
	}
	
	public List<MemberBean> memberList() {
		List<MemberBean> list = new ArrayList<>();
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement("select * from memberdb");

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberBean member = new MemberBean(
						rs.getString("uid"),	
						rs.getString("PWD"),	
						rs.getString("NAME"),	
						rs.getString("PHONE"),
						rs.getString("email")
						);
				list.add(member);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public boolean stopMemeber(String user_id) {
		// TODO Auto-generated method stub
		boolean num=false;
		
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(true);
			String query = "update memberDB set ";
			query += "allow = ?";
			query += " where uid = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "n");
			pstmt.setString(2, user_id);
		
			num = pstmt.execute();
		
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return num;
	}
	
	public boolean actMemeber(String user_id) {
		// TODO Auto-generated method stub
		boolean num=false;
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(true);
			String query = "update memberDB set ";
			query += "allow = ?";
			query += " where uid = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "y");
			pstmt.setString(2, user_id);
		
			num = pstmt.execute();
		
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return num;
	}

	public int getAdmin(String user_id) {
		// TODO Auto-generated method stub
		String admin=null;
		String query = "select admin from memberdb where uid = ?";
		System.out.println("prepareStatememt: " + query);
	
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				admin=rs.getString("admin");	
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if(admin.equals("admin"))
			return 1;
		else
			return 2;
	}


	public boolean checkBlock(String user_id) {
		// TODO Auto-generated method stub
		String result=null;
		String query = "select allow from memberdb where uid = ?";
		System.out.println("prepareStatememt: " + query);

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				result=rs.getString("allow");
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if(result.equalsIgnoreCase("y"))
			return true;
		else
			return false;
		
		
		
	}


	public List<MemberBean> adminSearch(String user_id) {
List<MemberBean> list = new ArrayList<>();
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement("select * from memberdb where uid = ?");
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberBean member = new MemberBean(
						rs.getString("uid"),	
						rs.getString("PWD"),	
						rs.getString("NAME"),	
						rs.getString("PHONE"),
						rs.getString("email")
						);
				list.add(member);
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
