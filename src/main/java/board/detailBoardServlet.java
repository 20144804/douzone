package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@WebServlet("/detail")
//@WebServlet("/board/detail")
public class detailBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardVO boardVO;
	BoardDAO dao = new BoardDAO();
	List<BoardFileVO> boardFileVoList=new ArrayList<BoardFileVO>();
	BoardFileDAO boardFileDao=new BoardFileDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		try {
			boardVO=dao.viewBoard(num);
			boardFileVoList=boardFileDao.list(num); 
			request.setAttribute("board",boardVO);
			request.setAttribute("boardFileVoList",boardFileVoList);
			

		
			nextPage = "/board_/viewBoard.jsp";
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
