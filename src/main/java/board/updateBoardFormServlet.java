package board;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/updateBoardForm")
public class updateBoardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardVO boardVO;
	BoardDAO dao = new BoardDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int num = Integer.parseInt(request.getParameter("num"));

		try {
			boardVO=dao.viewBoard(num);
			request.setAttribute("board",boardVO);
			nextPage = "/board_/updateBoardForm.jsp";
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
