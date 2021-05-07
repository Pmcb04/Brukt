package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.unex.pi.dao.FavoriteDAO;
import es.unex.pi.dao.JDBCFavoriteDAOImpl;
import es.unex.pi.model.Favorite;

/**
 * Servlet implementation class DeleteFavorite
 */
@WebServlet("/DeleteFavoriteServlet.do")
public class DeleteFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFavoriteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Addfavorite : Handling GET");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
		favoriteDao.setConnection(conn);
		
		String idp = request.getParameter("idp"); 
		String idu = request.getParameter("idu"); 
		String numEstrellas = request.getParameter("numEstrellas");

		
		if(idu.equals(""))
			response.sendRedirect("LoginServlet.do");
		else {
			long id_product = Long.parseLong(idp); 
			long id_user = Long.parseLong(idu); 
			
			int userFavorites = Integer.parseInt(request.getParameter("favorites")); 
			
			Favorite favorite = new Favorite();
			favorite.setIdp(id_product);
			favorite.setIdu(id_user);
			
			favoriteDao.delete(favorite);

			request.setAttribute("id", id_product);
			request.setAttribute("favorite_user", "false");
			response.sendRedirect("ProductDetailsServlet.do?id=" + id_product + "&favoriteUser=false" + "&favorites=" + (userFavorites-1) + "&numEstrellas=" + numEstrellas);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
