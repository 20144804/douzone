/*
 * package board;
 * 
 * import java.io.IOException; import java.util.HashMap; import java.util.Map;
 * 
 * import com.oracle.wls.shaded.org.apache.bcel.classfile.Method;
 * 
 * import jakarta.servlet.Servlet; import jakarta.servlet.ServletConfig; import
 * jakarta.servlet.ServletException; import
 * jakarta.servlet.annotation.WebServlet; import
 * jakarta.servlet.http.HttpServlet; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 *//**
	 * Servlet implementation class BoardController
	 */
/*
 * @WebServlet("/board/*") public class BoardServlet extends HttpServlet {
 * //클래스명 맵 Map<String, Object> className2ObjectMap = new HashMap<>(); //객체 맵
 * Map<String, Object> objectMap = new HashMap<>(); //메서드 맵 Map<String, Method>
 * methodMap = new HashMap<>(); String contextPath;
 * 
 * 
 * public void init(ServletConfig config) throws ServletException {
 * super.init(config); // contextPath =
 * config.getServletContext().getContextPath(); String actionNames =
 * config.getInitParameter("actionNames"); actionNames = actionNames.trim();
 * 
 * try { for (String line : actionNames.split("\n")) { line = line.trim();
 * 
 * String [] actionInfo = line.split(":"); //클래스를 로딩한다 Class<?> cls =
 * Class.forName(actionInfo[1]);
 * 
 * //클래스명이 존재하는지 확인한다 if (!className2ObjectMap.containsKey(actionInfo[1])) {
 * //클래스를 이용하여 객체를 생성한다 Object object =
 * cls.getDeclaredConstructor().newInstance();
 * 
 * //생성된 객체를 클래스 명으로 해서 맵에 추가함 className2ObjectMap.put(actionInfo[1], object);
 * 
 * //생성된 객체를 URL 명으로 해서 맵에 추가함 objectMap.put(actionInfo[0], object); } else {
 * 
 * //클래스명으로 해서 생성된 객체를 URL 명으로 해서 맵에 추가함 objectMap.put(actionInfo[0],
 * className2ObjectMap.get(actionInfo[1])); } Method method =
 * cls.getMethod(actionInfo[2], HttpServletRequest.class,
 * HttpServletResponse.class); methodMap.put(actionInfo[0], method); } } catch
 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); } }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { doHandle(request, response);
 * }
 * 
 *//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
		 * protected void doPost(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException { doHandle(request, response);
		 * }
		 * 
		 * private void doHandle(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException { String path =
		 * request.getRequestURI(); path = path.substring(contextPath.length()); Object
		 * obj = objectMap.get(path); Method method = methodMap.get(path); if (obj !=
		 * null && method != null) { //action.execute(request, response); try {
		 * method.invoke(obj, request, response); } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * } }
		 * 
		 * }
		 */