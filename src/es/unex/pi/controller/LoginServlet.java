package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;

/**
 * Servlet implementation class LoginServlet
 */


// Complete the urlpattern for this servlet
@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        //  Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		logger.info("LoginServlet : Handling GET");
		
		request.setAttribute("title", "Login | Brukt");

		//complete the code here

		//If there is not a session, the Login.jsp must be involved, otherwise, the orders must be listed by using the ListOrderServlet.do
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		
		if(user != null) {
			response.sendRedirect("user/UserServlet.do");			
		}
		else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/Login.jsp");
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("LoginServlet : Handling POST");
		
		// complete the code here

		//You must check that the user exists in the system and that the password is right. 
		
		// If there is any error, user must be redirected again to Login.jsp
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = userDao.get(username);
		
		if((user != null) && (user.getPassword().equals(password))) {
			logger.info("LoginServlet : Usuario correcto");
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("IndexServlet.do");
		}
		else {
	
			logger.info("LoginServlet : Usuario incorrecto");
			request.setAttribute("messages", "Worng username or password!!");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/Login.jsp");
			view.forward(request, response);
		}

	}

}
