package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/join/*")
public class joinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	/**
     * @see HttpServlet#HttpServlet()
     */
    public joinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getRequestURI().equals("/project/join/dupUidCheck")) {
			String uid = request.getParameter("uid");
			
			MemberDAO memberDAO = new MemberDAO();
			MemberBean memberBean = memberDAO.viewMember(uid);
			JSONObject jsonResult = new JSONObject();
//			System.out.println(memberBean.getUid());
			
			if (memberBean == null) {
				jsonResult.put("status", true);
				jsonResult.put("message", "해당 아이디는 사용가능합니다");
			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "해당 아이디는 사용 불가능합니다");
			}
			PrintWriter out = response.getWriter();
			out.println(jsonResult.toString());
		} else if (request.getRequestURI().equals("/project/join/insert")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String jsonStr = in.readLine();
			System.out.println("jsonStr = " + jsonStr);
			
			JSONObject jsonMember = new JSONObject(jsonStr);
		  	
			String uid = (String)jsonMember.get("uid");
			String pwd = (String)jsonMember.get("pwd");
			String name = (String)jsonMember.get("name1");
		 	
			String phone = (String)jsonMember.get("phone1")+(String)jsonMember.get("phone2")+(String)jsonMember.get("phone3");
			String email = (String)jsonMember.get("email1")+"@"+(String)jsonMember.get("email2");
			
			
			JSONObject jsonResult = new JSONObject();
			
			MemberDAO memberDAO = new MemberDAO();
			try {
				memberDAO.addMember(new MemberBean(uid, pwd, name, phone, email));
				jsonResult.put("status", true);
				jsonResult.put("url", "/project/main.jsp");
				jsonResult.put("message", "회원가입성공");
		
			} catch (SQLException e) {
				e.printStackTrace();
				jsonResult.put("status", false);
				jsonResult.put("message", "회원가입실패");
			}
			
			PrintWriter out = response.getWriter();
			out.println(jsonResult.toString());
		}
		

	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
