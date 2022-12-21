package board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardAction {
	

	public String boardShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO boardDAO = new BoardDAO();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int pageNo = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		System.out.println("pageNo: " + pageNo);
		try {
			String text = request.getParameter("searchContent");
			System.out.println("text:"+text);
			List<BoardVO> articlesList = new ArrayList<BoardVO>();
//				articlesList = boardDAO.selectAllArticles();
				articlesList = boardDAO.selectBy((pageNo-1)*10, 10);
				int pageSize = 10;
				int totalPageNo = boardDAO.totalPageNo(text);
				int startPageNo = ((pageNo - 1) / pageSize) * pageSize + 1;
				int endPageNo = startPageNo + pageSize - 1;
				
				if (endPageNo > totalPageNo) endPageNo = totalPageNo;
				
				System.out.println("startPage:"+startPageNo);
				System.out.println("endPageNo:"+endPageNo);
				System.out.println("totalPageNo:"+ totalPageNo);
				
				request.setAttribute("articlesList", articlesList);
				request.setAttribute("totalPageNo", totalPageNo);
				request.setAttribute("startPageNo", startPageNo);
				request.setAttribute("endPageNo", endPageNo);
				request.setAttribute("currentPageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/jsp/board_/listArticles.jsp";
	}
	
		public String detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			BoardVO boardVO;
			BoardDAO dao = new BoardDAO();
			List<BoardFileVO> boardFileVoList=new ArrayList<BoardFileVO>();
			BoardFileDAO boardFileDao=new BoardFileDAO();
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			int num = Integer.parseInt(request.getParameter("num"));
			
			try {
				boardVO=dao.viewBoard(num);
				boardFileVoList=boardFileDao.list(num); 
				request.setAttribute("board",boardVO);
				request.setAttribute("boardFileVoList",boardFileVoList);
				System.out.println("실행");
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/jsp/board_/viewBoard.jsp";
			
		}
		
	public String searchBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		List<BoardVO> voList = new ArrayList<BoardVO>();
		
		
		String searchSelect =request.getParameter("searchSelect");
		String searchContent = request.getParameter("searchContent");
		String sql="";
		
		switch(searchSelect) {
		case "내용":
			sql += "select * from board where content Like ?";
			voList = dao.search2(sql,searchContent);
			break;
		case "작성자":
			sql += "select * from board where id = ? ";
			voList = dao.search(sql,searchContent);
			break;
		case "카테고리":
			sql += "select * from board where category = ? ";
			voList = dao.search(sql,searchContent);
			break;

		case "제목":
			sql += "select * from board where title Like ?";
			voList = dao.search2(sql,searchContent);
			break;
		}
		
		
		if(!(voList.isEmpty())) {
			System.out.println("성공");
			request.setAttribute("status", true);
			request.setAttribute("message", "검색성공");
			request.setAttribute("voList",voList);
			for(BoardVO vo:voList) {
				System.out.println(vo.getNum());
				System.out.println(vo.getId());
			}
		}
		else {
			System.out.println("실패");
			request.setAttribute("status", false);
			request.setAttribute("message", "검색실패");
			request.setAttribute("voList",null);
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/board_/searchResult.jsp");
		dispatch.forward(request, response);
		return "";
	}

}
