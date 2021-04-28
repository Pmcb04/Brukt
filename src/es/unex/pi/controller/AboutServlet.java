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
@WebServlet("/AboutServlet.do")
public class AboutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("AboutServlet : Handling GET");
		request.setAttribute("title", "Sobre Nosotros | Brukt");
		
		request.setAttribute("banner", new DoubleClass<String, String>("nosotros.jpg", "Sobre Nosotros"));
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/About.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("AboutServlet : Handling POST");
		doGet(request, response);
	}

}
