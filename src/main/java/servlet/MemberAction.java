package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberAction {

	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("id");
		String user_pwd = request.getParameter("password");
		MemberBean member = new MemberBean();
		member.setUid(user_id);
		member.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(member);
		boolean blockResult = dao.checkBlock(user_id);
		int admin = 2;
		HttpSession session = request.getSession();

		if (result && blockResult) {
			session.setAttribute("isLogon", true);
			session.setAttribute("login.id", user_id);
			session.setAttribute("login.pwd", user_pwd);
			session.setAttribute("sessionId", user_id);
			admin = dao.getAdmin(user_id);
			session.setAttribute("admin", admin);
		} else {
			session.setAttribute("isLogon", false);
			session.setAttribute("error", "error");

		}

		return "/jsp/member/loginResult.jsp";

	}

	public String loginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/main.jsp";
	}

	public JSONObject dupUidCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonResult = new JSONObject();
			String uid = request.getParameter("uid");
			MemberDAO memberDAO = new MemberDAO();
			MemberBean memberBean = memberDAO.viewMember(uid);

			if (memberBean == null) {
				System.out.println("null");
				jsonResult.put("status", true);
				jsonResult.put("message", "?????? ???????????? ?????????????????????");
			} else {
				System.out.println("not null");
				jsonResult.put("status", false);
				jsonResult.put("message", "?????? ???????????? ?????? ??????????????????");
			}
		
		return jsonResult;
	}

	public JSONObject join(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonResult = new JSONObject();
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String jsonStr = in.readLine();
		System.out.println("jsonStr = " + jsonStr);

		JSONObject jsonMember = new JSONObject(jsonStr);

		String uid = (String) jsonMember.get("uid");
		String pwd = (String) jsonMember.get("pwd");
		String name = (String) jsonMember.get("name1");

		String phone = (String) jsonMember.get("phone1") + (String) jsonMember.get("phone2")
				+ (String) jsonMember.get("phone3");
		String email = (String) jsonMember.get("email1") + "@" + (String) jsonMember.get("email2");

		MemberDAO memberDAO = new MemberDAO();
		try {
			memberDAO.addMember(new MemberBean(uid, pwd, name, phone, email));
			jsonResult.put("status", true);
			jsonResult.put("url", "/project/member/loginForm.do");
			jsonResult.put("message", "??????????????????");

		} catch (SQLException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("url", "/project/member/join.do");
			jsonResult.put("message", "??????????????????");
			
		} finally {
			return jsonResult;
		}

	}

	public String joinForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/addMember.jsp";
	}

	public String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();

		return "/jsp/member/main.jsp";
	}

	public JSONObject findId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

		String jsonStr = in.readLine();
		System.out.println("jsonStr = " + jsonStr);
		MemberDAO memberDAO = new MemberDAO();
		JSONObject jsonMember = new JSONObject(jsonStr);

		String id = null;
		String pwd = (String) jsonMember.getString("pwd");
		String name = (String) jsonMember.get("name1");
		String email1 = (String) jsonMember.get("email1");
		String email2 = (String) jsonMember.get("email2");
		String email = email1 + "@" + email2;

		JSONObject jsonResult = new JSONObject();

		memberDAO = new MemberDAO();
		try {
			id = memberDAO.findId(name, pwd, email);

			if (id != null) {
				jsonResult.put("status", true);
				jsonResult.put("message", "???????????? [" + id + "] ?????????");
				jsonResult.put("url", "/project/member/loginForm.do");
			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "???????????? ????????? ???????????? ?????? ??? ????????????. ");
				jsonResult.put("url", "/project/member/findIdForm.do");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

//		PrintWriter out = response.getWriter();
//		out.println(jsonResult.toString());
		return jsonResult;

	}

	public String findIdForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/findId.jsp";
	}

	public JSONObject findPwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MemberSevlet============ : " + request.getRequestURI());
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

		String jsonStr = in.readLine();
		System.out.println("jsonStr = " + jsonStr);
		MemberDAO memberDAO = new MemberDAO();
		JSONObject jsonMember = new JSONObject(jsonStr);

		String pwd = null;
		String id = (String) jsonMember.getString("uid");
		String name = (String) jsonMember.get("name1");
		String email1 = (String) jsonMember.get("email1");
		String email2 = (String) jsonMember.get("email2");
		String email = email1 + "@" + email2;

		JSONObject jsonResult = new JSONObject();

		memberDAO = new MemberDAO();
		try {
			pwd = memberDAO.findPwd(name, id, email);

			if (pwd != null) {
				jsonResult.put("status", true);
				jsonResult.put("message", "??????????????? [" + pwd + "] ?????????");
				jsonResult.put("url", "/project/member/loginForm.do");
			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "???????????? ????????? ??????????????? ?????? ??? ????????????. ");
				jsonResult.put("url", "/project/member/findPwdForm.do");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		PrintWriter out = response.getWriter();
//		out.println(jsonResult.toString());
		return jsonResult;
	}

	public String findPwdForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/findPwd.jsp";
	}

	public String show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionId");
		MemberDAO dao = new MemberDAO();

		MemberBean memberBean = dao.showMember(sessionId);

		session.setAttribute("memberBean", memberBean);

		return "/jsp/member/show.jsp";
	}
	
	public JSONObject updateMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

		String jsonStr = in.readLine();
		System.out.println("jsonStr = " + jsonStr);
		MemberDAO memberDAO = new MemberDAO();
		JSONObject jsonMember = new JSONObject(jsonStr);

		String sessionid = (String) session.getAttribute("sessionId");
		String pwd = jsonMember.getString("pwd");
		String name = jsonMember.getString("name1");
		String phone1 = jsonMember.getString("phone1");
		String phone2 = jsonMember.getString("phone2");
		String phone3 = jsonMember.getString("phone3");
		String phone = phone1 + phone2 + phone3;
		String email1 = jsonMember.getString("email1");
		String email2 = jsonMember.getString("email2");
		String email = email1 + "@" + email2;

		JSONObject jsonResult = new JSONObject();

		memberDAO = new MemberDAO();
		try {
			MemberBean memberBean = memberDAO.showMember(sessionid);

			if (!"".equals(pwd)) {
				memberBean.setPwd(pwd);
			}
			if (!"".equals(name)) {
				memberBean.setName(name);
			}
			if (!"".equals(phone)) {
				memberBean.setPhone(phone);
			}
			if (!"".equals(email1) && !"".equals(email2)) {
				memberBean.setEmail(email);
			}
			memberDAO.updateMember(memberBean, sessionid);
			jsonResult.put("status", true);
			jsonResult.put("url", "/project/member/loginForm.do");
			jsonResult.put("message", "????????????");
			session.invalidate();

		} catch (SQLException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "?????? ?????? ??????????????????  ?????? ???????????? ????????? ????????? ");
		}

//		PrintWriter out = response.getWriter();
//		out.println(jsonResult.toString());
		
		return jsonResult;
	}
	

	public String updateMemberForm(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		return "/jsp/member/updateMember.jsp";
	}
	
	public JSONObject delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		JSONObject jsonIn = new JSONObject(request.getReader().readLine());
		String user_id = jsonIn.getString("userid");
		MemberDAO dao = new MemberDAO();
		int result = dao.deleteMemeber(user_id);
		JSONObject jsonResult = new JSONObject();
		

		if (result !=0) {
			HttpSession session = request.getSession();
			session.invalidate();
			jsonResult.put("url", "/project/member/loginForm.do");
			jsonResult.put("message", "?????? ?????? ??????");
			jsonResult.put("status", true);
		} else {
			jsonResult.put("message", "?????? ?????? ??????");
			jsonResult.put("status", false);
		}
		return jsonResult;
	}
	
	public String deleteForm(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		return "/jsp/member/withDraw.jsp";
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		List<MemberBean> list = dao.memberList();
		
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		
		return "/jsp/member/listMembers.jsp";
		
	}
	
	public String deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String user_id = request.getParameter("userid");

		MemberDAO dao = new MemberDAO();
		int result = dao.deleteMemeber(user_id);
		
		return "/jsp/member/listMembers.jsp";
	}
	
	public String act(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String user_id = request.getParameter("userid");

		MemberDAO dao = new MemberDAO();
		boolean result = dao.actMemeber(user_id);
		return "/jsp/member/listMembers.jsp";

	}
	
	public String stop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String user_id = request.getParameter("userid");
	
		MemberDAO dao = new MemberDAO();
		boolean result = dao.stopMemeber(user_id);
		return "/jsp/member/listMembers.jsp";

	}
	
	public String adminSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HERE~~~~~~~~~~~~~~~");
		String user_id = request.getParameter("searchUser");
		MemberDAO dao =new MemberDAO();
		List<MemberBean> list = new ArrayList<MemberBean>();
		list= dao.adminSearch(user_id);
		System.out.println(list);
		
		HttpSession session =request.getSession();
		session.setAttribute("list", list);
		return "/jsp/member/listMembers.jsp";
		

	}
	
}
