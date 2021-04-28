package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Favorite;
import es.unex.pi.model.Product;
import es.unex.pi.model.User;

public class JDBCFavoriteDAOImpl implements FavoriteDAO{

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCProductDAOImpl.class.getName());
	
	@Override
	public void setConnection(Connection conn) {
		//  Auto-generated method stub
		this.conn = conn;
		
	}

	@Override
	public List<Product> getAllByUser(long idu) {
		
		if (conn == null)
			return null;

		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement stmt = conn.createStatement();
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
			ResultSet rs = stmt.executeQuery("SELECT * FROM favorites WHERE idu = "+ idu);

			while (rs.next()) {
				Product product = productDao.get(rs.getInt("idp"));
				products.add(product);
				
				logger.info("fetching product: "+product.toString());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public List<User> getAllByProduct(long idp) {
		
		if (conn == null)
			return null;

		ArrayList<User> users = new ArrayList<User>();
		try {
			Statement stmt = conn.createStatement();
			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
			ResultSet rs = stmt.executeQuery("SELECT * FROM favorites WHERE idp="+ idp);
			
			while (rs.next()) {
				User user = userDao.get(rs.getInt("idu"));
				users.add(user);
				logger.info("fetching product: "+user.toString());				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
		
	}
	
	
	@Override
	public List<Favorite> getAllFavoritesByUser(long idu) {
		
		if (conn == null)
			return null;

		ArrayList<Favorite> products = new ArrayList<Favorite>();
		try {
			Statement stmt = conn.createStatement();
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
			ResultSet rs = stmt.executeQuery("SELECT * FROM favorites WHERE idu = "+ idu);

			while (rs.next()) {
				Product product = productDao.get(rs.getInt("idp"));
				Favorite favorite = new Favorite();
				favorite.setIdp(product.getId());
				favorite.setIdu(idu);
				products.add(favorite);
				
				logger.info("fetching product: "+product.toString());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public List<Favorite> getAllFavoritesByProduct(long idp) {
		
		if (conn == null)
			return null;

		ArrayList<Favorite> users = new ArrayList<Favorite>();
		try {
			Statement stmt = conn.createStatement();
			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
			ResultSet rs = stmt.executeQuery("SELECT * FROM favorites WHERE idp="+ idp);
			
			while (rs.next()) {
				User user = userDao.get(rs.getInt("idu"));
				Favorite favorite = new Favorite();
				favorite.setIdp(idp);
				favorite.setIdu(user.getId());
				users.add(favorite);
				logger.info("fetching product: "+user.toString());				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}

	

	@Override
	public long add(Favorite favorite) {
		long id=-1;
		long lastidu=-1;
		if (conn != null){

			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='favorites'");	
				if (!rs.next()) return -1; 
				lastidu=rs.getInt("seq");
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO favorites (idp,idu) VALUES('"
									+favorite.getIdp()+"','"
									+favorite.getIdu()+"')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='favorites'");		
				if (!rs.next()) return -1; 
				id=rs.getInt("seq");
				if (id<=lastidu) return -1;
											
				logger.info("CREATING Favorite("+id+"): " + favorite.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return id;
	}

	@Override
	public boolean delete(Favorite favorite) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM favorites WHERE idp="+favorite.getIdp() + " and " + " idu=" + favorite.getIdu());
				logger.info("deleting favorite: "+ favorite.toString());
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}



}
