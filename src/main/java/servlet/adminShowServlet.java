package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberServlet
 */
//@WebServlet("/adminShow")
public class adminShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public adminShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		String uid = request.getParameter("uid");
		MemberDAO dao= new MemberDAO();
	
		MemberBean memberBean= dao.showMember(uid);
	
//		session.setAttribute("memberBean", memberBean);
//		response.sendRedirect("/project/member/show.jsp");
		PrintWriter out = response.getWriter();
		String html = """
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 창</title>
</head>
<body>
<button type="button" onclick="location.href='/project/manageMember'">뒤로</button>

<table border='1'>
	<thead>
		<tr>
			<th>아이디</th>
			<th>비번</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>삭제</th>
			<th>정지</th>
			<th>활성화</th>
		</tr>
	</thead>
	<tbody>
				""";
		
			html += "<tr>";
			html += "<td>" + memberBean.getUid() + "</td>";
			html += "<td>" + memberBean.getPwd() + "</td>";
			html += "<td>" + memberBean.getName() + "</td>";
			html += "<td>" + memberBean.getPhone() + "</td>";
			html += "<td>" + memberBean.getEmail() + "</td>";
			html += "<td><a href='deleteAdmin?userid=" + memberBean.getUid() + "'>삭제</a></td>";
			html += "<td><a href='stop?userid=" + memberBean.getUid() + "'>정지</a></td>";
			html += "<td><a href='act?userid=" + memberBean.getUid() + "'>활성화</a></td>";
			html += "</tr>";
		
		
	html += """
	</tbody>
</table>				
				"""; 
		out.println(html);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
