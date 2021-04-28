package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.Product;



public interface ProductDAO {

	/**
	 * Set the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);
	
	/**
	 * Gets a product from the DB using id.
	 * 
	 * @param id
	 *            Product Identifier.
	 * 
	 * @return Product object with that id.
	 */
	public Product get(long id);

	/**
	 * Gets all the products from the database.
	 * 
	 * @return List of all the product from the database.
	 */
	public List<Product> getAll();
	
	/**
	 * Gets all the product from the database that contain a text in the title.
	 * 
	 * @param search
	 *            Search string .
	 * 
	 * @return List of all the product from the database that contain a text either in the title.
	 */	
	public List<Product> getAllBySearchTitle(String search);


	/**
	 * Gets all the product from the database that belong to a user.
	 * 
	 * @param idu
	 *            User identifier.
	 * 
	 * @return List of all the product from the database that belong to a user
	 */	
	public List<Product> getAllByUser(long idu);
	
	
	/**
	 * Gets all the product from the database that contain a text in the description.
	 * 
	 * @param search
	 *            Search string .
	 * 
	 * @return List of all the product from the database that contain a text in the description.
	 */	
	public List<Product> getAllBySearchDescription(String search);
	
	/**
	 * Gets all the product from the database that contain a text either in the title or in the description.
	 * 
	 * @param search
	 *            Search string .
	 * 
	 * @return List of all the product from the database that contain a text either in the title or in the description.
	 */	
	public List<Product> getAllBySearchAll(String search);
	
	/**
	 * Gets all the product from the database that contain a text either in the title or in the description.
	 * 
	 * @param search
	 *            Search string .
	 * 
	 * @return List of all the product from the database that contain a text either in the title or in the description.
	 */	
	public List<Product> getAllByCategoryPrice(String idCategory, float price);
	

	/**
	 * Adds a product to the database.
	 * 
	 * @param product
	 *            Product object with the product details.
	 * 
	 * @return Product identifier or -1 in case the operation failed.
	 */
	
	public long add(Product product);

	/**
	 * Updates an existing product in the database.
	 * 
	 * @param product
	 *            Product object with the new details of the existing product.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean save(Product product);

	/**
	 * Deletes a product from the database.
	 * 
	 * @param id
	 *            Product identifier.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean delete(long id);
}
