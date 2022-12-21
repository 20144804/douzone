package board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class zzz
 */
//@WebServlet("/updateBoard")
public class updateBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		System.out.println("serlvet in");
		HttpSession session = request.getSession();
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String jsonStr = in.readLine();
		System.out.println("jsonStr = " + jsonStr);
		JSONObject jsonMember = new JSONObject(jsonStr);
		
		BoardDAO dao = new BoardDAO();
		
		String id = (String)session.getAttribute("sessionId");
		int num = jsonMember.getInt("num");
		String checkId=dao.checkUpdateId(num);
		int result=0;
		
		if(id.equals(checkId)) {
			
			String title = jsonMember.getString("title");
			String category = jsonMember.getString("category");
			String content = jsonMember.getString("content");
			
			BoardVO vo = new BoardVO(num, id, title,category, content);
			result=dao.updateBoard(vo);
		}
		JSONObject jsonResult = new JSONObject();
		
		
		if(result==1) {
			jsonResult.put("status", true);
			/* jsonResult.put("url", "project/detail"); */
			jsonResult.put("message", "변경성공");
		}
		else {
			jsonResult.put("status", false);
//			jsonResult.put("url", "/detail");
			jsonResult.put("message", "변경 실패 아이디 불일치 ");
		}
		PrintWriter out = response.getWriter();
		out.append(jsonResult.toString());
	}

}
