package board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardAction {
	BoardVO boardVO;
	BoardDAO boardDao = new BoardDAO();
	BoardFileVO boardFileVo;
	BoardFileDAO boardFileDao;
	
	
	private void boardShow(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		try {
			List<BoardVO> articlesList = new ArrayList<BoardVO>();
			if (action == null) {
				articlesList = boardDao.selectAllArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board_/listArticles.jsp";
			} 

			else {
				nextPage = "/board_/listArticles.jsp";
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		
		try {
			boardVO=boardDao.viewBoard(num);
			request.setAttribute("board",boardVO);
			nextPage = "/board_/viewBoard.jsp";
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
