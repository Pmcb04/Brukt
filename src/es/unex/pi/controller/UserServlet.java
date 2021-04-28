package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import es.unex.pi.dao.CommentDAO;
import es.unex.pi.dao.FavoriteDAO;
import es.unex.pi.dao.JDBCCommentDAOimpl;
import es.unex.pi.dao.JDBCFavoriteDAOImpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Comment;
import es.unex.pi.model.Favorite;
import es.unex.pi.model.PasswordValidation;
import es.unex.pi.model.Product;
import es.unex.pi.model.User;
import es.unex.pi.util.DoubleClass;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user/UserServlet.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    private void updatePhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
      // TODO : Completar metodo	
    }
    
    private void updateData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		logger.info("UserServlet : Handling POST : updateData");
		HttpSession session = request.getSession();
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		
		String password = request.getParameter("password");
		String username = request.getParameter("username");		
		String email = request.getParameter("email");
		String genero = request.getParameter("genero");
		
		User user = (User) session.getAttribute("user");
		
		if(!username.equals(""))
			user.setUsername(username);
		
		if(!email.equals(""))
			user.setEmail(email);
		
		if(!password.equals(""))
			user.setPassword(password);
		
		if(genero.equals("Hombre"))
			user.setImage("man.png");
		else if(genero.equals("Mujer"))
			user.setImage("woman.png");

		
		Map<String, String> messages = new HashMap<String, String>();

		if(username.equals(user.getUsername()) || (PasswordValidation.validatePassword(user.getPassword(), messages) == false && !userDao.exist(username))) {
			
			boolean save = userDao.save(user);
			
			if(save) {
				logger.info("EditUserServlet : Usuario guardado");
				session.setAttribute("user", user);
				response.sendRedirect("UserServlet.do");
			}else {
				logger.info("EditUserServlet : Usuario no guardado");
				messages.put("register", "El guardado no pudo llevarse a cabo");
				request.setAttribute("messages", messages);
				response.sendRedirect("UserServlet.do");
			}
			
		}
		else {

			logger.info("EditUserServlet : Usuario no ha podido guardarse");
			messages.put("errorRegistro", "El nombre de usuario ya esta registrado");
			request.setAttribute("messages", messages);
			response.sendRedirect("UserServlet.do");
		}
		
	
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		logger.info("UserServlet : Handling POST : deleteUser");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");

		
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		ProductDAO productDao = new JDBCProductDAOImpl();
		productDao.setConnection(conn);
		
		FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
		favoriteDao.setConnection(conn);
		
		CommentDAO commentDao = new JDBCCommentDAOimpl();
		commentDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		logger.info("Confirmed user to delete with session id: "+session.getId());
		User user = (User) session.getAttribute("user");
		
		
		if (user!=null) {
			
			List<Product> products = new ArrayList<Product>();
			List<Favorite> favorites = new ArrayList<Favorite>();
			List<Comment> comments = new ArrayList<Comment>();
			
			// Todos los productos del usuario
			products = productDao.getAllByUser(user.getId());
			
			for (Product product : products) {
				
				// Borramos todos los favoritos de los productos que tiene el usuario
				favorites = favoriteDao.getAllFavoritesByProduct(product.getId());
				for (Favorite favorite : favorites) {
					favoriteDao.delete(favorite);
				}
				
				// Borramos todos los comentarios de este producto
				comments = commentDao.getAllByProduct(product.getId());
				for (Comment comment : comments) {
					commentDao.delete(comment.getUsername(), comment.getIdp());
				}
				
				// Borramos el producto del usuario
				productDao.delete(product.getId());
				
			}
			
			// Borramos todos los favoritos que ha dado el usuario
			favorites = favoriteDao.getAllFavoritesByUser(user.getId());
			for (Favorite favorite : favorites) {
				favoriteDao.delete(favorite);
			}
			
			// Borramos a el usuario
			userDao.delete(user.getId());
			session.removeAttribute("user");
			user=null;
		}
			
		response.sendRedirect(request.getContextPath() + "/IndexServlet.do");

    }
    
    private void closeSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("UserServlet : Handling POST : closeSession");
		
		HttpSession session = request.getSession();
		if(session != null)
			session.invalidate();
		response.sendRedirect(request.getContextPath() + "/IndexServlet.do");
		
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("userServlet : Handling GET");
		request.setAttribute("title", "Usuario | Brukt");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		List<Product> products_user = new ArrayList <Product>();
		List<Product> favorites_user = new ArrayList <Product>();
		List<User> usersFavorites = new ArrayList<User>();
		List<DoubleClass<Product, List<User>>> favoritesUserList = new ArrayList<DoubleClass<Product, List<User>>>();
		List<DoubleClass<Product, List<User>>> productsUserList = new ArrayList<DoubleClass<Product, List<User>>>();
		
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		ProductDAO productDao = new JDBCProductDAOImpl();
		productDao.setConnection(conn);
		
		FavoriteDAO favoritesDao = new JDBCFavoriteDAOImpl();
		favoritesDao.setConnection(conn);

		products_user = productDao.getAllByUser(user.getId());
		favorites_user = favoritesDao.getAllByUser(user.getId());
		
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
		
		for (Product product : favorites_user) {
			usersFavorites = favoritesDao.getAllByProduct(product.getId());
			favoritesUserList.add(new DoubleClass<Product, List<User>>(product,usersFavorites));
		}
		
		request.setAttribute("products_user", productsUserList);
		request.setAttribute("favorites_user", favoritesUserList);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/user/user.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("UserServlet : Handling POST");
			
		switch (request.getParameter("action")) {
		
		case "close":
			closeSession(request, response);
			break;
		case "data":
			updateData(request, response);
			break;
		case "delete":
			deleteUser(request, response);
			break;
		case "photo":
			updatePhoto(request, response);
			break;

		default:
			break;
		}		
		
	}
}
