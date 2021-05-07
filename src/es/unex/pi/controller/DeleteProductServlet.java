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


import es.unex.pi.dao.CommentDAO;
import es.unex.pi.dao.FavoriteDAO;
import es.unex.pi.dao.JDBCCommentDAOimpl;
import es.unex.pi.dao.JDBCFavoriteDAOImpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.model.Favorite;
import es.unex.pi.model.Product;
import es.unex.pi.model.Comment;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteProductServlet.do")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("DeleteProductServlet : Handling GET");

		
		try{
			
				Connection conn = (Connection) getServletContext().getAttribute("dbConn");
				ProductDAO productDao = new JDBCProductDAOImpl();
				productDao.setConnection(conn);
			
				request.setAttribute("title", "Borrar Producto | Brukt");
				request.setAttribute("product", productDao.get(Long.parseLong(request.getParameter("id"))));
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/CheckProduct.jsp");
				view.forward(request,response);
		}
		catch (Exception e) {
				logger.info("DeleteProductServlet : error deleted product");
				response.sendRedirect("UserServlet.do");
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("DeleteProductServlet : Handling POST");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		ProductDAO productDao = new JDBCProductDAOImpl();
		productDao.setConnection(conn);
		
		FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
		favoriteDao.setConnection(conn);
		
		CommentDAO commentDao = new JDBCCommentDAOimpl();
		commentDao.setConnection(conn);
		
		String id = request.getParameter("id");
		Product product = productDao.get(Long.parseLong(id));
		
		if (product!=null) {
			
			List<Favorite> favorites = new ArrayList<Favorite>();
			List<Comment> comments = new ArrayList<Comment>();
			
				
			// Borramos todos los favoritos de este producto
			favorites = favoriteDao.getAllFavoritesByProduct(product.getId());
			for (Favorite favorite : favorites) {
				favoriteDao.delete(favorite);
			}
			
			// Borramos todos los comentarios de este producto
			comments = commentDao.getAllByProduct(product.getId());
			for (Comment comment : comments) {
				commentDao.delete(comment.getUsername(), comment.getIdp());
			}
					
			// Borramos el producto
			productDao.delete(product.getId());
		}
			
		response.sendRedirect("UserServlet.do");
	}

}
