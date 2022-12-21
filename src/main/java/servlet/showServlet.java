package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberServlet
 */
/* @WebServlet("/show/*") */
public class showServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	/**
     * @see HttpServlet#HttpServlet()
     */
    public showServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionId");
		MemberDAO dao= new MemberDAO();
		 
		MemberBean memberBean= dao.showMember(sessionId);
	
		session.setAttribute("memberBean", memberBean);
		response.sendRedirect("/project/member/show.jsp");
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
