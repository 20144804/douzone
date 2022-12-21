package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;

/**
 * Servlet implementation class MemberServlet
 */
//@WebServlet("/deleteAdmin")
public class deleteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	/**
     * @see HttpServlet#HttpServlet()
     */
    public deleteAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String user_id = request.getParameter("userid");


		System.out.println(user_id+"-----------------------dle---------------------");
		MemberDAO dao = new MemberDAO();
		int result = dao.deleteMemeber(user_id);
		

		if (result !=0) {
			HttpSession session = request.getSession();
//			session.setAttribute("isLogon", true);
////			session.setAttribute("login.id", user_id);
//			session.setAttribute("login.pwd", user_pwd);
//			session.setAttribute("sessionId", user_id);
		
			
//			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
//			rd.forward(request, response);

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/loginMain.jsp");
			rd.forward(request, response);
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
