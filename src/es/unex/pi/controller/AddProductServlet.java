package es.unex.pi.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import es.unex.pi.dao.CategoryDAO;
import es.unex.pi.dao.JDBCCategoryDAOimpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.model.Category;
import es.unex.pi.model.Product;
import es.unex.pi.model.ProductValidation;
import es.unex.pi.model.User;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet.do")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        //  Auto-generated constructor stub
    }

	private String characterCurrency(String moneda) {
		if(moneda.equals("Euro")) return "&euro;";
		if(moneda.equals("Dolar")) return "$";
		if(moneda.equals("Libra")) return "&pound;";
		if(moneda.equals("Yen")) return "&yen;";
		return null;
						
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("AddProductServlet : Handling GET");
		request.setAttribute("title", "Agregar Producto | Brukt");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");

		List<Category> categories = new ArrayList <Category>();
		CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
		categoryDao.setConnection(conn);
		categories = (List<Category>)categoryDao.getAll();
		
		request.setAttribute("categories", categories);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/AddProduct.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("AddProductServlet : Handling POST");
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		ProductDAO productDao = new JDBCProductDAOImpl();
		productDao.setConnection(conn);
		
		CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
		categoryDao.setConnection(conn);
		
		
		List<Category> categories = new ArrayList <Category>();

		categories = categoryDao.getAll();
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String numeroS = request.getParameter("numero");
		int numero = (!numeroS.equals(""))? Integer.parseInt(request.getParameter("numero")):0;
		String precioS = request.getParameter("precio");
		System.out.println(precioS);
		float precio = (!precioS.equals(""))? Float.parseFloat(precioS.replace(',', '.')):0;
		String moneda = request.getParameter("moneda");		
		String categoria = request.getParameter("categoria");		
		String rapido = request.getParameter("rapido");
		
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();
		
		if(fileContent.available() != 0) {
			String relativeWebPath = request.getServletContext().getRealPath("/images/product/");
			File imageContent = new File(relativeWebPath, fileName);
			imageContent.createNewFile();
			
			FileOutputStream imageFile = new FileOutputStream(relativeWebPath + fileName);
			
			int n=0,c;
			System.out.println("Copiando ...");
			while( (c = fileContent.read()) != -1){
				imageFile.write(c);
				n++;
			}
			fileContent.close();
			imageFile.close();
			System.out.println("Se han copiado "+n+" caracteres");
			
		}else {
			fileName = "no_image.jpg";
		}
	
		
		for (Category category : categories) {
			if(category.getName().equals(categoria)) categoria = category.getId();
		}

		Product product = new Product();
		product.setTitle(name);
		product.setDescription(description);
		product.setStock(numero);
		product.setPrice(precio);
		product.setCurrency(characterCurrency(moneda));
		product.setCategory(categoria);
		product.setIdu(user.getId());
		product.setImage(fileName);
		product.setSoldout(0);
		product.setRapido("no");
		if(rapido != null && rapido.equals("Si"))
			product.setRapido("yes");
		
		
		Map<String, String> messages = new HashMap<String, String>();

		if(!ProductValidation.validateProduct(product ,messages)) {
						
			long register = productDao.add(product);
			
			if(register != -1) {
				logger.info("AddProductServlet : producto registrado");
				response.sendRedirect("UserServlet.do");
			}else {
				logger.info("AddProductServlet : producto no registrado");
				messages.put("register", "El registro no pudo llevarse a cabo");
				request.setAttribute("messages", messages);
				request.setAttribute("categories", categories);
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/AddProduct.jsp");
				view.forward(request, response);
			}
			
		}
		else {
			logger.info("AddProductServlet : Producto no ha podido registrarse");
			request.setAttribute("messages", messages);
			request.setAttribute("categories", categories);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/AddProduct.jsp");
			view.forward(request, response);
		}

	}




		

}
