package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.Favorite;
import es.unex.pi.model.Product;
import es.unex.pi.model.User;

public interface FavoriteDAO {


	/**
	 * Set the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);
	

	/**
	 * Gets all the product list of user that is favorites from the database.
	 * 
	 * @param int idu
	 * 		User identifier
	 * 
	 * @return List of all Product the idu favorites product from the database.
	 */
	public List<Product> getAllByUser(long idu);
	
	
	/**
	 * Gets all the user list of product that is favorites from the database.
	 * 
	 * @param int idp
	 * 		User identifier
	 * 
	 * @return List of all User of product that is favorites from the database.
	 */
	public List<User> getAllByProduct(long idp);
	
	
	/**
	 * Gets all the product list of user that is favorites from the database.
	 * 
	 * @param int idu
	 * 		User identifier
	 * 
	 * @return List of all favorite the idu favorites product from the database.
	 */
	public List<Favorite> getAllFavoritesByUser(long idu);
	
	
	/**
	 * Gets all the user list of product that is favorites from the database.
	 * 
	 * @param int idp
	 * 		User identifier
	 * 
	 * @return List of all Favorite of product that is favorites from the database.
	 */
	public List<Favorite> getAllFavoritesByProduct(long idp);
	
	
	/**
	 * Adds a favorite to the database.
	 * 
	 * @param favorite favorite
	 * 			  favorite favorite
	 * 
	 * @return Favorite identifier or -1 in case the operation failed.
	 */
	
	public long add(Favorite favorite);


	/**
	 * Deletes a product from the database.
	 * 
	 * @param favorite favorite
	 * 			  favorite favorite
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean delete(Favorite favorite);
	
	
}
