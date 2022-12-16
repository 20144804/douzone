package servlet;
/*
 * package servlet;
 * 
 * import jakarta.servlet.RequestDispatcher; import
 * jakarta.servlet.ServletException; import
 * jakarta.servlet.annotation.WebServlet; import
 * jakarta.servlet.http.HttpServlet; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse; import java.io.IOException;
 * 
 *//**
	 * Servlet implementation class MemberServlet
	 */
/*
 * //@WebServlet("/member/*") public class MemberServlet extends HttpServlet {
 * private static final long serialVersionUID = 1L;
 * 
 *//**
	 * @see HttpServlet#HttpServlet()
	 */
/*
 * public MemberServlet() { super(); // TODO Auto-generated constructor stub }
 * 
 *//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
/*
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * System.out.println("MemberSevlet============ : " + request.getRequestURI());
 * if (request.getRequestURI().equals("/project/member/view")) { String id =
 * request.getParameter("id"); MemberDAO memberDAO = new MemberDAO(); MemberBean
 * memberBean = memberDAO.viewMember(id); request.setAttribute("memberBean",
 * memberBean); RequestDispatcher dispatch =
 * request.getRequestDispatcher("/jsp/member/view.jsp");
 * dispatch.forward(request, response); } else if
 * (request.getRequestURI().equals("/project/member/list")) { MemberDAO
 * memberDAO = new MemberDAO(); request.setAttribute("listMembers",
 * memberDAO.listMembers());
 * 
 * RequestDispatcher dispatch =
 * request.getRequestDispatcher("/jsp/member/list.jsp");
 * dispatch.forward(request, response); }
 * 
 * 
 * }
 * 
 *//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
		 * protected void doPost(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException { // TODO Auto-generated
		 * method stub doGet(request, response); }
		 * 
		 * }
		 */
