package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import es.unex.pi.dao.CategoryDAO;
import es.unex.pi.dao.JDBCCategoryDAOimpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.helper.DateTimeHelper;
import es.unex.pi.model.Category;
import es.unex.pi.model.User;

/**
 * Servlet implementation class index
 */
@WebServlet("/IndexServlet.do")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("IndexServlet : Handling GET");
		
		//Obtain the session and check if there is an attribute with the name "order" stored
		// If there is already an attribute with that name, remove that attribute from the session
		//(This is just for avoiding a potential problem if the user clicked on the back button of the browser before confirming the deletion of an order)
		
		HttpSession session = request.getSession();
		request.setAttribute("title", "Brukt | Tienda de segunda mano");

		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		User user = userDao.get("pmcb04");
		session.setAttribute("user", user);

		// uncomment this code for logging purpose
		logger.info("IndexServlet : Session id: "+session.getId());
		logger.info("IndexServlet : Session new? "+session.isNew());
		logger.info("IndexServlet : Session creation time: "+DateTimeHelper.time2Date(session.getCreationTime()));
		logger.info("IndexServlet : Session last accessed time: "+DateTimeHelper.time2Date(session.getLastAccessedTime()));
		logger.info("IndexServlet : Session max inactive time: "+DateTimeHelper.time2Date(session.getMaxInactiveInterval()));
		
		
		List<Category> categories = new ArrayList <Category>();
		CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
		categoryDao.setConnection(conn);
		categories = (List<Category>)categoryDao.getAll();
		
		request.setAttribute("categories", categories);
		
		
		// Dispatch the request to the EditOrder.jsp
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/Index.jsp");
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("IndexServlet : Handling POST");
		doGet(request, response);
	}

}
