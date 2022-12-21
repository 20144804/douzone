/*
 * package board;
 * 
 * import java.io.File; import java.io.FileInputStream; import
 * java.io.IOException; import java.io.OutputStream; import java.util.ArrayList;
 * import java.util.List;
 * 
 * import jakarta.servlet.RequestDispatcher; import
 * jakarta.servlet.ServletException; import
 * jakarta.servlet.annotation.WebServlet; import
 * jakarta.servlet.http.HttpServlet; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 * @WebServlet("/download") //@WebServlet("/board/detail") public class
 * downloadFileServlet extends HttpServlet { private static final long
 * serialVersionUID = 1L; BoardVO boardVO; BoardDAO dao = new BoardDAO();
 * List<BoardFileVO> boardFileVoList=new ArrayList<BoardFileVO>(); BoardFileDAO
 * boardFileDao=new BoardFileDAO();
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { String nextPage = "";
 * request.setCharacterEncoding("utf-8");
 * response.setContentType("text/html; charset=utf-8"); String org_name =
 * (String) request.getParameter("org_name"); int f_id
 * =Integer.parseInt(request.getParameter("f_id"));
 * boardFileVoList=(List<BoardFileVO>)request.getParameter("boardFileVoList");
 * try { OutputStream out = response.getOutputStream(); FileInputStream in =
 * null; if(!boardFileVoList.isEmpty()) { for(BoardFileVO vo : boardFileVoList)
 * { String org_name=vo.org_name; int f_id=vo.f_id;
 * 
 * String path = "C:\\download"+"\\" + f_id + "\\" + org_name; File imageFile =
 * new File(path);
 * 
 * response.setHeader("Cache-Control", "no-cache");
 * response.addHeader("Content-disposition", "attachment;fileName=" + org_name);
 * in = new FileInputStream(imageFile); byte[] buffer = new byte[1024 * 8];
 * while (true) { int count = in.read(buffer); if (count == -1) break;
 * out.write(buffer, 0, count); } } in.close(); out.close(); }
 * 
 * nextPage = "/board_/viewBoard.jsp"; RequestDispatcher dispatch =
 * request.getRequestDispatcher(nextPage); dispatch.forward(request, response);
 * } catch (Exception e) { e.printStackTrace(); }
 * 
 * }
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { doGet(request, response); }
 * 
 * }
 */