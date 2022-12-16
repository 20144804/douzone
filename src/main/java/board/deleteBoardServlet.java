package board;

import java.io.IOException;

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
@WebServlet("/deleteBoard")
public class deleteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	/**
     * @see HttpServlet#HttpServlet()
     */
    public deleteBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String seId=(String) session.getAttribute("sessionId");
		
		BoardDAO dao =new BoardDAO();
		int result =0;
		String boardId="";
		
		boardId = dao.checkUpdateId(num);
		
		
		if(seId.equals(boardId)) {
			result=dao.deleteBoard(num);
		}
		else {
			  RequestDispatcher dispatch = request.getRequestDispatcher("/boardShow");
		      dispatch.forward(request, response);
		}
		
		
		if(result==1) {
			  RequestDispatcher dispatch = request.getRequestDispatcher("/boardShow");
		      dispatch.forward(request, response);

		}
		else {
			  RequestDispatcher dispatch = request.getRequestDispatcher("/boardShow");
		      dispatch.forward(request, response);

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
