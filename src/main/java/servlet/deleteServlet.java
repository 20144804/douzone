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
@WebServlet("/delete")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	/**
     * @see HttpServlet#HttpServlet()
     */
    public deleteServlet() {
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
		MemberDAO dao = new MemberDAO();
		int result = dao.deleteMemeber(user_id);
		
		if (result !=0) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("main.jsp");
			
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
