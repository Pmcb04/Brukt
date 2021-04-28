package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.unex.pi.dao.CategoryDAO;
import es.unex.pi.dao.CommentDAO;
import es.unex.pi.dao.FavoriteDAO;
import es.unex.pi.dao.JDBCCategoryDAOimpl;
import es.unex.pi.dao.JDBCCommentDAOimpl;
import es.unex.pi.dao.JDBCFavoriteDAOImpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.model.Category;
import es.unex.pi.model.Product;
import es.unex.pi.model.User;
import es.unex.pi.util.DoubleClass;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/ShopServlet.do")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("ShopServlet : Handling GET");
		
		
		String id_category = request.getParameter("id");
		logger.info("get parameter id ("+id_category+")");
		
		String name_category = request.getParameter("name");
		logger.info("get parameter name ("+name_category+")");
		
		String moneda = request.getParameter("moneda");
		logger.info("get parameter moneda ("+moneda+")");
		
		String search = request.getParameter("search");
		logger.info("get parameter search ("+search+")");
		
		String busqueda = request.getParameter("busqueda");
		logger.info("get parameter busqueda ("+busqueda+")");
		
		String estado_products = request.getParameter("estado");
		logger.info("get parameter estado ("+estado_products+")");
	
		String price = request.getParameter("price");
		logger.info("get parameter price ("+price+")");
		
		String order = request.getParameter("order");
		logger.info("get parameter order ("+order+")");
		
		String comments = request.getParameter("comments");
		logger.info("get parameter comments ("+comments+")");

		List<Product> products = new ArrayList <Product>();
		List<Product> products_filter = new ArrayList <Product>();
		List<User> usersFavorites = new ArrayList<User>();
		List<DoubleClass<Product, List<User>>> productsUserList = new ArrayList<DoubleClass<Product, List<User>>>();
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		ProductDAO productDao = new JDBCProductDAOImpl();
		productDao.setConnection(conn);
		
		List<Category> categories = new ArrayList <Category>();
		CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
		categoryDao.setConnection(conn);
		categories = (List<Category>)categoryDao.getAll();
		
		Collections.sort(productsUserList, (x, y) -> x.getFirst().getDescription().compareToIgnoreCase(y.getFirst().getDescription()));				
		
		FavoriteDAO favoritesDao = new JDBCFavoriteDAOImpl();
		favoritesDao.setConnection(conn);
		
		CommentDAO commentDao = new JDBCCommentDAOimpl();
		commentDao.setConnection(conn);
		
		// filtramos por busqueda
		if(search.equals("yes")) {
			
			products = (List<Product>)productDao.getAllBySearchAll(busqueda);
			request.setAttribute("name_category", "Busqueda personalizada : " + busqueda);

		}else {
			products = (List<Product>)productDao.getAll();
			request.setAttribute("name_category", name_category);
		}

		
		// filtramos por categoria 
		if(!id_category.equals("Todas")) {
			
			for (Product product : products) 
				if(product.getCategory().equals(id_category)) 
					products_filter.add(product);
			
			
			products = new ArrayList<Product>();
			products.addAll(products_filter);
			products_filter = new ArrayList<Product>();
		}
		
		
		// Filtramos por estado
		if(!estado_products.equals("All")) {
			for (Product product : products) 
				if(estado_products.equals("En Venta") && product.getSoldout() == 0 || estado_products.equals("Vendidos") && product.getSoldout() == 1) 
					products_filter.add(product);
			
			products = new ArrayList<Product>();
			products.addAll(products_filter);
			products_filter = new ArrayList<Product>();
		}
		
		
		// Filtramos por moneda
		if(!moneda.equals("All")) {
			for (Product product : products) {				
				if(moneda.equals("euro") && product.getCurrency().equals("&euro;") ||
						moneda.equals("dolar") && product.getCurrency().equals("$") ||
						moneda.equals("pound") && product.getCurrency().equals("&pound;") ||
						moneda.equals("yen") && product.getCurrency().equals("&yen;") ) {
					
					products_filter.add(product);
					
				}
			}
			
			products = new ArrayList<Product>();
			products.addAll(products_filter);
			products_filter = new ArrayList<Product>();
			
		}
		
		
		// Filtramos por precio
		if(!price.equals("All")) {
			for (Product product : products) 
				if(price.equals("1") && (product.getPrice() > 0 && product.getPrice() <= 50) || 
						price.equals("2") && (product.getPrice() > 50 && product.getPrice() <= 100) ||
						price.equals("3") && (product.getPrice() > 100 && product.getPrice() <= 500) ||
						price.equals("4") && product.getPrice() > 500) 
					
					
					products_filter.add(product);
			
			products = new ArrayList<Product>();
			products.addAll(products_filter);
			products_filter = new ArrayList<Product>();
			
		}
		
		
		// Para cada producto cogemos la lista de usuarios que ha dado favorito
		for (Product product : products) {
			usersFavorites = favoritesDao.getAllByProduct(product.getId());
			productsUserList.add(new DoubleClass<Product, List<User>>(product,usersFavorites));
		}
		
	    // Ordernamos los elementos
		if(order.equals("asc")) 
			Collections.sort(productsUserList, (x, y) -> x.getFirst().getTitle().compareToIgnoreCase(y.getFirst().getTitle()));				
		else if(order.equals("desc"))
			Collections.sort(productsUserList, (x, y) -> y.getFirst().getTitle().compareToIgnoreCase(x.getFirst().getTitle()));	
		else if(order.equals("ascF"))
			Collections.sort(productsUserList, (x, y) ->  Integer.compare(x.getSecond().size(),y.getSecond().size()));
		else if(order.equals("descF"))
			Collections.sort(productsUserList, (x, y) ->  Integer.compare(y.getSecond().size(),x.getSecond().size()));
		else if(order.equals("ascC"))
			Collections.sort(productsUserList, (x, y) ->  Integer.compare(commentDao.getAllByProduct(x.getFirst().getId()).size(), commentDao.getAllByProduct(y.getFirst().getId()).size()));
		else if(order.equals("descC"))
			Collections.sort(productsUserList, (x, y) ->  Integer.compare(commentDao.getAllByProduct(y.getFirst().getId()).size(), commentDao.getAllByProduct(x.getFirst().getId()).size()));

	
		// ponemos los atributos para poder acceder desde Shop.jsp 
		request.setAttribute("categories", categories);
		request.setAttribute("products", productsUserList);
		request.setAttribute("id", id_category);
		request.setAttribute("name", name_category);
		request.setAttribute("moneda", moneda);
		request.setAttribute("search", search);
		request.setAttribute("busqueda", busqueda);
		request.setAttribute("estado", estado_products);
		request.setAttribute("price", price);
		request.setAttribute("order", order);
		
		request.setAttribute("banner", new DoubleClass<String, String>("cart.jpg", "Tienda"));
		request.setAttribute("title", "Tienda | Brukt");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/Shop.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("ShopServlet : Handling POST");
		doGet(request, response);
	}

}
