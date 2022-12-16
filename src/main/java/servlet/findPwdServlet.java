package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/findPwd")
public class findPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public findPwdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("MemberSevlet============ : " + request.getRequestURI());
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

		String jsonStr = in.readLine();
		System.out.println("jsonStr = " + jsonStr);
		MemberDAO memberDAO = new MemberDAO();
		JSONObject jsonMember = new JSONObject(jsonStr);

		String pwd=null;
		String id = (String) jsonMember.getString("uid");
		String name = (String) jsonMember.get("name1");
		String email1 = (String) jsonMember.get("email1");
		String email2 = (String) jsonMember.get("email2");
		String email = email1 + "@" + email2;

		JSONObject jsonResult = new JSONObject();

		memberDAO = new MemberDAO();
		try {
			pwd = memberDAO.findPwd(name, id, email);
	
			jsonResult.put("status", true);
			jsonResult.put("message", "비밀번호는 [" + pwd + "] 입니다");
			jsonResult.put("url", "/member/findPwd.jsp");
			
		} catch (SQLException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "일치하지 않아서 아이디를 찾을 수 없습니다. ");
		}
		
		PrintWriter out = response.getWriter();
		out.println(jsonResult.toString());
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}