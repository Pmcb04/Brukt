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

import es.unex.pi.dao.FavoriteDAO;
import es.unex.pi.dao.JDBCFavoriteDAOImpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Product;
import es.unex.pi.model.User;
import es.unex.pi.util.DoubleClass;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserPublicServlet.do")
public class UserPublicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPublicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("UserPublicServlet : Handling GET");
		request.setAttribute("title", "Usuario | Brukt");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user_session = (User) session.getAttribute("user");
		User user = userDao.get(request.getParameter("username_user"));
		
		if(user_session != null) {
			
			if( user_session.getUsername().equals(user.getUsername())) response.sendRedirect("UserServlet.do");
			else {
				
				List<Product> products_user = new ArrayList <Product>();
				List<User> usersFavorites = new ArrayList<User>();
				List<DoubleClass<Product, List<User>>> productsUserList = new ArrayList<DoubleClass<Product, List<User>>>();
				
				ProductDAO productDao = new JDBCProductDAOImpl();
				productDao.setConnection(conn);
				
				FavoriteDAO favoritesDao = new JDBCFavoriteDAOImpl();
				favoritesDao.setConnection(conn);
				
				products_user = productDao.getAllByUser(user.getId());
				
				int user_venta = 0, user_vendido = 0;
				
				for (Product product : products_user) {
					if(product.getSoldout() == 1) user_vendido += 1;
					else user_venta+=1;
				}
				
				
				request.setAttribute("user_venta", user_venta);
				request.setAttribute("user_vendido", user_vendido);
				
				
				for (Product product : products_user) {
					usersFavorites = favoritesDao.getAllByProduct(product.getId());
					productsUserList.add(new DoubleClass<Product, List<User>>(product,usersFavorites));
				}
				
				request.setAttribute("products_user", productsUserList);
				request.setAttribute("user", user);
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/UserPublicProfile.jsp");
				view.forward(request,response);
				
			}
			
		}
		else
			response.sendRedirect("LoginServlet.do");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("UserPublicServlet : Handling POST");
		doGet(request, response);
		
	}

}
