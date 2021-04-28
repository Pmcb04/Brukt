package es.unex.pi.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.HashMap;
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

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.PasswordValidation;
import es.unex.pi.model.User;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUserServlet.do")
@MultipartConfig
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    private boolean checkRegister(String password_again, User user, UserDAO userDao, Map<String, String> messages) {
    	
    	boolean errores = false;
    	
    	if(password_again.equals("")) {
    		messages.put("password_again","El campo de validacion de la contraseña no puede estar vacio");
    		errores = true;
    	}
    	
    	if(user.getUsername().equals("") || userDao.exist(user.getUsername())) {
    		messages.put("username","Nombre de usuario invalido o ya registrado");
    		errores = true;
    	}
    	
    	if (user.getPassword().equals("") || !user.getPassword().equals(password_again) ) {
    		messages.put("password", "Las contraseñas no coinciden");
    		errores = true;
    	}
    	
    	errores = errores || PasswordValidation.validatePassword(user.getPassword(), messages);

    	
    	return errores;
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("RegisterUserServlet : Handling GET");
		request.setAttribute("title", " Registro | Brukt");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/RegisterUser.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("RegisterUserServlet : Handling POST");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		
		String password = request.getParameter("password");
		String password_again = request.getParameter("password_again");
		String username = request.getParameter("username");		
		String email = request.getParameter("email");
		String genero = request.getParameter("genero");
		
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole("User");
		if(genero.equals("Hombre") || genero.equals("Mujer"))
			user.setGenero(genero);
		
		
		String fileName = (user.getGenero().equals("Hombre")? "man.png" : "woman.png");
		InputStream fileContent;
		Part filePart = request.getPart("file");
		
		if(filePart != null) {
			
			fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			fileContent = filePart.getInputStream();
			
			
			if(fileContent.available() != 0) {
				String relativeWebPath = request.getServletContext().getRealPath("/images/user/");
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
		
		user.setImage(fileName);
		
		Map<String, String> messages = new HashMap<String, String>();

		if(!checkRegister(password_again, user, userDao ,messages)) {
			
			long register = userDao.add(user);
			
			if(register != -1) {
				logger.info("RegisterUserServlet : Usuario registrado");
				HttpSession session = request.getSession();
				user = userDao.get(username);
				session.setAttribute("user", user);
				response.sendRedirect("IndexServlet.do");
			}else {
				logger.info("RegisterUserServlet : Usuario no registrado");
				messages.put("register", "El registro no pudo llevarse a cabo");
				request.setAttribute("messages", messages);
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/RegisterUser.jsp");
				view.forward(request, response);
			}
			
		}
		else {
			
			logger.info("RegisterUserServlet : Usuario no ha podido registrarse");
			request.setAttribute("messages", messages);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/RegisterUser.jsp");
			view.forward(request, response);
		}
		
	}

}
