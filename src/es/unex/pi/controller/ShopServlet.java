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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("ShopServlet : Handling GET");
		
		
		String id_category = request.getParameter("id");
		logger.info("get parameter id ("+id_category+")");

		String order = request.getParameter("order");
		logger.info("get parameter order ("+order+")");
			
		String estado = request.getParameter("estado");
		logger.info("get parameter estado ("+estado+")");
		
		String moneda = request.getParameter("moneda");
		logger.info("get parameter moneda ("+moneda+")");
		
		String price = request.getParameter("price");
		logger.info("get parameter price ("+price+")");
		
		String search = request.getParameter("search");
		logger.info("get parameter search ("+search+")");

		String premium = request.getParameter("premium");
		logger.info("get parameter premium ("+premium+")");


		response.sendRedirect("public/Tienda.html#!/shop/" + id_category + "/" + order + "/" + estado + "/" + moneda + "/" + price + "/" + search + "/" + premium);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("ShopServlet : Handling POST");
		doGet(request, response);
	}

}
