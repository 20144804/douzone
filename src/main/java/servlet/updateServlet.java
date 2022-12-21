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
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberServlet
 */
/* @WebServlet("/update/*") */
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

		String jsonStr = in.readLine();
		System.out.println("jsonStr = " + jsonStr);
		MemberDAO memberDAO = new MemberDAO();
		JSONObject jsonMember = new JSONObject(jsonStr);

		String sessionid = (String) session.getAttribute("sessionId");
		String pwd = jsonMember.getString("pwd");
		String name = jsonMember.getString("name1");
		String phone1 = jsonMember.getString("phone1");
		String phone2 = jsonMember.getString("phone2");
		String phone3 = jsonMember.getString("phone3");
		String phone = phone1 + phone2 + phone3;
		String email1 = jsonMember.getString("email1");
		String email2 = jsonMember.getString("email2");
		String email = email1 + "@" + email2;

		JSONObject jsonResult = new JSONObject();

		memberDAO = new MemberDAO();
		try {
			MemberBean memberBean = memberDAO.showMember(sessionid);

			if (!"".equals(pwd)) {
				memberBean.setPwd(pwd);
			}
			if (!"".equals(name)) {
				memberBean.setName(name);
			}
			if (!"".equals(phone)) {
				memberBean.setPhone(phone);
			}
			if (!"".equals(email1) && !"".equals(email2)) {
				memberBean.setEmail(email);
			}
			memberDAO.updateMember(memberBean, sessionid);
			jsonResult.put("status", true);
			jsonResult.put("url", "main.jsp");
			jsonResult.put("message", "변경성공");
			session.invalidate();

		} catch (SQLException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "변경 실패 해당아이디는  현재 사용중인 아이디 입니다 ");
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
