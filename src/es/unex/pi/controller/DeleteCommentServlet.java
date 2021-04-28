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

import es.unex.pi.dao.CommentDAO;
import es.unex.pi.dao.JDBCCommentDAOimpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.model.Product;
import es.unex.pi.model.User;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteCommentServlet.do")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("DeleteCommentServlet : Handling GET");

		
		try{
				request.setAttribute("title", "Borrar comentario | Brukt");
				long id =Long.parseLong(request.getParameter("idp"));
				
				Connection conn = (Connection) getServletContext().getAttribute("dbConn");
				
				ProductDAO productDao = new JDBCProductDAOImpl();
				productDao.setConnection(conn);
				Product product = productDao.get(id);
				
				request.setAttribute("name_product", product.getTitle());
				
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/CheckComment.jsp");
				view.forward(request,response);
		}
		catch (Exception e) {
				logger.info("DeleteCommentServlet : error deleted comment");
				response.sendRedirect("IndexServlet.do");
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("DeleteCommentServlet : Handling POST");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");

	
		CommentDAO commentDao = new JDBCCommentDAOimpl();
		commentDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		long id =Long.parseLong(request.getParameter("idp"));
		boolean favorite_user = Boolean.parseBoolean(request.getParameter("favoriteUser")); 		
		int userFavorites = Integer.parseInt(request.getParameter("favorites")); 

		commentDao.delete(user.getUsername(), id);
			
		response.sendRedirect("ProductDetailsServlet.do?id=" + id + "&favorites=" + userFavorites + "&favoriteUser=" + favorite_user + "&numEstrellas=Todas");
	}

}
