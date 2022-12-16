package board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class zzz
 */
@WebServlet("/write")
public class writeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public writeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("c:\\upload"));
		ServletFileUpload upload = new ServletFileUpload(factory);

		System.out.println(request.getRemoteAddr());

		HttpSession session = request.getSession();

		try {
			PrintWriter out = response.getWriter();
			Map<String, List<FileItem>> mapItems = upload.parseParameterMap(request);

			String id = (String) session.getAttribute("sessionId");
			String title = new String(mapItems.get("title").get(0).getString().getBytes("ISO-8859-1"), "UTF-8");
			String category = new String(mapItems.get("category").get(0).getString().getBytes("ISO-8859-1"), "UTF-8");
			String content = new String(mapItems.get("content").get(0).getString().getBytes("ISO-8859-1"), "UTF-8");

			JSONObject jsonResult = new JSONObject();
			BoardFileDAO boardFileDAO = new BoardFileDAO();
			BoardDAO dao = new BoardDAO();

			try {
				int maxNum = dao.searchMaxNum();

				BoardVO boardVo = new BoardVO(maxNum, id, title, category, content);
				dao.addBoard(boardVo);

//				for (FileItem fileItem : mapItems.get("uploadFile")) {
//					if (fileItem.getSize() == 0)
//						continue;
//					String real_name = "c:\\upload\\" + System.nanoTime();
//					fileItem.write(new File(real_name));
//
//					BoardFileVO boardFileVo = BoardFileVO.builder().num(maxNum).org_name(fileItem.getName())
//							.real_name(real_name).content_type(fileItem.getContentType()).length(fileItem.getSize())
//							.build();
//					System.out.println(boardFileVo);
//					boardFileDAO.insertBoardFile(boardFileVo);
//				}

				
				for (FileItem fileItem : mapItems.get("uploadFile")) {
					if (fileItem.getSize() == 0)
						continue;
					String real_name = "c:\\upload\\" + System.nanoTime();
					fileItem.write(new File(real_name));

					BoardFileVO boardFileVo = BoardFileVO.builder().num(maxNum).org_name(fileItem.getName())
							.real_name(real_name).content_type(fileItem.getContentType()).length(fileItem.getSize())
							.build();
					System.out.println(boardFileVo);
					boardFileDAO.insertBoardFile(boardFileVo);
				}

				
				
				
				boardFileDAO.close();

				jsonResult.put("status", true);
				jsonResult.put("message", "글쓰기를 성공했습니다.");
				jsonResult.put("url", "/project/boardShow");

			} catch (SQLException e) {
				jsonResult.put("status", false);
				jsonResult.put("message", "작성 중에 오류가 발생했습니다.");
				jsonResult.put("url", "/project/boardShow");
				e.printStackTrace();
			}

			out.println(jsonResult.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
