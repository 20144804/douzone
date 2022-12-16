package board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/searchBoard")
public class searchBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	/**
     * @see HttpServlet#HttpServlet()
     */
    public searchBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
