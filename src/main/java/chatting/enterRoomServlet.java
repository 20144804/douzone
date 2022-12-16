package chatting;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class enterRoomServlet
 */
@WebServlet("/enterRoom")
public class enterRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public enterRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String roomName = request.getParameter("roomName");
		request.setAttribute("roomName", roomName);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/chatting/websocket.jsp?roomName="+roomName);
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
