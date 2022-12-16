package chatting;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/makeRoom")
public class makeRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "/chatting/makeRoomResult.jsp";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String roomName=request.getParameter("createRoom");
		System.out.println("roomName:"+roomName);
		RoomDAO dao = new RoomDAO();
		List<String> roomList = new ArrayList<String>();
		int num = 0;
		try {
			num = dao.makeRoom(roomName);
			roomList = dao.selecRoomNameList();
		
			request.setAttribute("roomList", roomList);

			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
