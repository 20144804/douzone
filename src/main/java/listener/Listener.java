/*
 * package Listener;
 * 
 * import javax.naming.Context; import javax.naming.InitialContext; import
 * javax.naming.NamingException; import javax.sql.DataSource;
 * 
 * import board.BoardDAO; import jakarta.servlet.ServletContextEvent; import
 * jakarta.servlet.ServletContextListener; import
 * jakarta.servlet.annotation.WebListener; import servlet.MemberDAO;
 * 
 * @WebListener public class Listener implements ServletContextListener {
 * 
 * @Override public void contextInitialized(ServletContextEvent
 * servletContextEvent) { try { Context ctx = new InitialContext(); Context
 * envContext = (Context) ctx.lookup("java:/comp/env"); DataSource dataFactory =
 * (DataSource) envContext.lookup("jdbc/pro05DB");
 * MemberDAO.setDataFactory(dataFactory); BoardDAO.setDataFactory(dataFactory);
 * BoardFileDAO.setDataFactory(dataFactory); } catch (NamingException e) { throw
 * new RuntimeException(e); } }
 * 
 * @Override public void contextDestroyed(ServletContextEvent
 * servletContextEvent) {
 * 
 * }
 * 
 * }
 */