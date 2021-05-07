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
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditProductServlet.do")
@MultipartConfig
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductServlet() {
        super();
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
		logger.info("EditProductServlet : Handling GET");
		
		request.setAttribute("title", "Editar Producto | Brukt");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		ProductDAO productDao = new JDBCProductDAOImpl();
		productDao.setConnection(conn);
		
		List<Category> categories = new ArrayList <Category>();
		CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
		categoryDao.setConnection(conn);
		categories = (List<Category>)categoryDao.getAll();
		
		request.setAttribute("categories", categories);

		request.setAttribute("product", productDao.get(Long.parseLong(request.getParameter("id"))));
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/EditProduct.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("EditProductServlet : Handling POST");
		HttpSession session = request.getSession();
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		List<Category> categories = new ArrayList <Category>();
		
		ProductDAO productDao = new JDBCProductDAOImpl();
		productDao.setConnection(conn);
		
		CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
		categoryDao.setConnection(conn);
		categories = (List<Category>)categoryDao.getAll();
		
		long id = Long.parseLong(request.getParameter("id"));
		User user = (User) session.getAttribute("user"); // Variable para user despues en el jsp
		
		
		String name = request.getParameter("name");
		System.out.println(name);
		String description = request.getParameter("description");
		System.out.println(description);
		String numeroS = request.getParameter("numero");
		System.out.println(numeroS);
		int numero = (!numeroS.equals(""))? Integer.parseInt(request.getParameter("numero")):0;
		String precioS = request.getParameter("precio");
		System.out.println(precioS);
		float precio = (!precioS.equals(""))? Float.parseFloat(precioS.replace(",", ".")):0;
		String moneda = request.getParameter("moneda");		
		System.out.println(moneda);
		String categoria = request.getParameter("categoria");	
		System.out.println(categoria);
		String rapido = request.getParameter("rapido");
		System.out.println(rapido);
		String estado = request.getParameter("estado");
		System.out.println(estado);
		
		String fileName = "no_image.jpg";
		InputStream fileContent;
		Part filePart = request.getPart("file");
		
		if(filePart != null) {
			
			fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			fileContent = filePart.getInputStream();
			
			
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
				
			}
		}
		
	
		
		for (Category category : categories) {
			if(category.getName().equals(categoria)) categoria = category.getId();
		}
		
		Product product = productDao.get(id);
		Map<String, String> messages = new HashMap<String, String>();
		
		if(product.getIdu() == user.getId()) {
			
			System.out.println("aqui");
			
			if(!name.equals(""))
				product.setTitle(name);
			if(!description.equals(""))
				product.setDescription(description);
			if(!numeroS.equals(""))
				product.setStock(numero);
			if(!precioS.equals(""))
				product.setPrice(precio);
			if(!moneda.equals(""))
				product.setCurrency(characterCurrency(moneda));
			if(!categoria.equals(""))
				product.setCategory(categoria);
			if(estado.equals("En venta"))
				product.setSoldout(0);
			if(estado.equals("Vendido"))
				product.setSoldout(1);
			if(rapido != null && rapido.equals("No"))
				product.setRapido("no");
			if(rapido != null && rapido.equals("Si"))
				product.setRapido("yes");
			product.setImage(fileName);
		
	
			if(!ProductValidation.validateProduct(product ,messages)) {
				
				boolean save = productDao.save(product);
				
				if(save) {
					logger.info("EditProductServlet : Producto guardado");
					response.sendRedirect("UserServlet.do");
				}else {
					logger.info("EditProductServlet : Producto no guardado");
					messages.put("register", "El guardado no pudo llevarse a cabo");
					request.setAttribute("messages", messages);
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/EditProduct.jsp");
					view.forward(request, response);
				}
				
			}
			else {
		
				logger.info("EditProductServlet : Producto no ha podido guardarse");
				request.setAttribute("messages", messages);
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/EditProduct.jsp");
				view.forward(request, response);
			}
		
		}else {
			logger.info("EditProductServlet : Producto no ha podido guardarse");
			messages.put("propiedad", "Producto no es de tu propiedad, no se puede editar");
			request.setAttribute("messages", messages);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/EditProduct.jsp");
			view.forward(request, response);
		}
	}

}
