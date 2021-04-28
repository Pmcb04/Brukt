package es.unex.pi.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.unex.pi.util.DoubleClass;

/**
 * Servlet implementation class AboutServlet
 */
@WebServlet("/ContactServlet.do")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("contactServlet : Handling GET");
		request.setAttribute("title", "Contacto | Brukt");
		request.setAttribute("banner", new DoubleClass<String, String>("redPhone.jpg", "Contactanos"));
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/Contact.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("ContactServlet : Handling POST");
		doGet(request, response);
	}

}
