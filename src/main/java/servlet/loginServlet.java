package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

//@WebServlet ("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("id");
		String user_pwd = request.getParameter("password");
		MemberBean member = new MemberBean();
		member.setUid(user_id);
		member.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(member);
		boolean blockResult =dao.checkBlock(user_id);
		int admin=2;
		HttpSession session = request.getSession();
		
		
		if (result && blockResult) {
			session.setAttribute("isLogon", true);
			session.setAttribute("login.id", user_id);
			session.setAttribute("login.pwd", user_pwd);
			session.setAttribute("sessionId", user_id);
			admin=dao.getAdmin(user_id);
			session.setAttribute("admin", admin);
		} else {
			session.setAttribute("isLogon", false);
			session.setAttribute("error", "error");
		
		}
		RequestDispatcher rd = request.getRequestDispatcher("/loginResult.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
