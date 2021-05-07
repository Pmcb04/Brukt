package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.pi.dao.CategoryDAO;
import es.unex.pi.dao.CommentDAO;
import es.unex.pi.dao.FavoriteDAO;
import es.unex.pi.dao.JDBCCategoryDAOimpl;
import es.unex.pi.dao.JDBCCommentDAOimpl;
import es.unex.pi.dao.JDBCFavoriteDAOImpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Comment;
import es.unex.pi.model.Product;
import es.unex.pi.model.User;
import es.unex.pi.util.DoubleClass;


/**
 * Servlet implementation class AboutServlet
 */
@WebServlet("/ProductDetailsServlet.do")
public class ProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
	private static final int PRODUCT_RELATED = 8;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("ProductDetailsServlet : Handling GET");
		request.setAttribute("title", "Detalles del producto | Brukt");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		List<User> usersFavorites = new ArrayList<User>();
		List<User> favoritesUsers = new ArrayList<User>();
		List<Comment> comments = new ArrayList<Comment>();
		List<Product> productsRelated = new ArrayList<Product>();
		List<Product> threeProductsRelated = new ArrayList<Product>();
		List<DoubleClass<Product, List<User>>> productsUserList = new ArrayList<DoubleClass<Product, List<User>>>();
		
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		ProductDAO productDao = new JDBCProductDAOImpl();
		productDao.setConnection(conn);
		
		CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
		categoryDao.setConnection(conn);
		
		CommentDAO commentDao = new JDBCCommentDAOimpl();
		commentDao.setConnection(conn);
		
		FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
		favoriteDao.setConnection(conn);
		
		
		String numEstrellas = (String)request.getParameter("numEstrellas");
		

		long id_product = Long.parseLong(request.getParameter("id")); 
		Product product = productDao.get(id_product);
		User user_product = userDao.get(product.getIdu());
		
		if(!numEstrellas.equals("Todas")) {
			if(numEstrellas.equals("Cero"))
				comments = commentDao.getAllByRatingProduct(0, id_product);
			if(numEstrellas.equals("Una"))
				comments = commentDao.getAllByRatingProduct(1, id_product);
			if(numEstrellas.equals("Dos"))
				comments = commentDao.getAllByRatingProduct(2, id_product);
			if(numEstrellas.equals("Tres"))
				comments = commentDao.getAllByRatingProduct(3, id_product);
			if(numEstrellas.equals("Cuatro"))
				comments = commentDao.getAllByRatingProduct(4, id_product);
			if(numEstrellas.equals("Cinco"))
				comments = commentDao.getAllByRatingProduct(5, id_product);
		}
		
		else
			comments = commentDao.getAllByProduct(product.getId());
		
				
		boolean favorite_user = Boolean.parseBoolean(request.getParameter("favoriteUser"));  // si el usuario ha dado favorito
		
		int userFavorites = Integer.parseInt(request.getParameter("favorites")); // numero de favoritos del articulo
		
		favoritesUsers = favoriteDao.getAllByProduct(id_product); // lista de usuarios que han dado favorito al articulo
		
		productsRelated = productDao.getAllByCategoryPrice(product.getCategory(), product.getPrice());
		
		if(productsRelated.size() > PRODUCT_RELATED) {
			Random random = new Random();
			
			int i = 0;
			
			while(i < PRODUCT_RELATED) {
				int indexProduct = random.nextInt(productsRelated.size());
				if(productsRelated.get(indexProduct).getId() != product.getId())threeProductsRelated.add(productsRelated.get(indexProduct));
				productsRelated.remove(indexProduct);
				i++;
				
			}
		}else {
			for (Product productRelateds : productsRelated) {
				if(productRelateds.getId() != product.getId())threeProductsRelated.add(productRelateds);
			}
		}
		
		// Para cada producto cogemos la lista de usuarios que ha dado favorito
		for (Product productRelated : threeProductsRelated) {
			usersFavorites = favoriteDao.getAllByProduct(productRelated.getId());
			productsUserList.add(new DoubleClass<Product, List<User>>(productRelated,usersFavorites));
		}
		
		
		request.setAttribute("product", product); 
		request.setAttribute("comments", comments); 
		request.setAttribute("favorites", userFavorites);
		request.setAttribute("favorite_user", favorite_user);
		request.setAttribute("favoritesUsers", favoritesUsers);
		request.setAttribute("productsRelated", productsUserList);
		request.setAttribute("user_product", user_product.getUsername());
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/Product-details.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("ProductDetailsServlet : Handling POST");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		ProductDAO productDao = new JDBCProductDAOImpl();
		productDao.setConnection(conn);
		
		CommentDAO commentDao = new JDBCCommentDAOimpl();
		commentDao.setConnection(conn);
		
	
		
		if(user != null) {
			
			long id_product = Long.parseLong(request.getParameter("id")); 
			Product product = productDao.get(id_product);
			
			
			if(commentDao.exist(user.getUsername(), product.getId())) {
				request.setAttribute("errorComment", "Usted ya ha realizado un comentario sobre este articulo");
			}
			else {
				Comment comment = new Comment();
				String comentario = request.getParameter("comentario");
				if(!comentario.equals("")) {
					comment.setDescription((String)request.getParameter("comentario"));
					comment.setIdp(product.getId());
					comment.setUsername(user.getUsername());
					String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
					comment.setDate(timeStamp);
					if(request.getParameter("estrellas") != null)
						comment.setRating(Integer.parseInt(request.getParameter("estrellas")));
					else
						comment.setRating(0);
					
					commentDao.add(comment);
				}else {
					request.setAttribute("errorComment", "El comentario no puede estar vacio");
				}
				
			}
			
			doGet(request, response);
			
		}
		else 
			response.sendRedirect("LoginServlet.do");
			


	}

}
