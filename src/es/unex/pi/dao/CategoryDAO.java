package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.Category;


public interface CategoryDAO {

	/**
	 * Sets the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);

	/**
	 * Gets an category from the DB using id.
	 * 
	 * @param id
	 *            Category Identifier.
	 * 
	 * @return Category object with that id.
	 */
	public Category get(String id);
	
	/**
	 * Gets an category from the DB using name.
	 * 
	 * @param name
	 *            Category Identifier.
	 * 
	 * @return Category object with that name.
	 */
	public Category getByName(String name);

	
	/**
	 * Gets all the category from the database.
	 * 
	 * @return List of all the category from the database.
	 */
	public List<Category> getAll();

	/**
	 * Adds a category to the database.
	 * 
	 * @param category
	 *            Category object with the category details.
	 * 
	 * @return Category identifier or "fail" in case the operation failed.
	 */
	public String add(Category category);

	/**
	 * Updates an existing category in the database.
	 * 
	 * @param category
	 *            Category object with the new details of the existing category.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean save(Category category);

	/**
	 * Deletes an user from the database.
	 * 
	 * @param id
	 *            String identifier.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean delete(String id);
	
	/**
	 * Check if the category exist in the database
	 * 
	 * @param id
	 *            String id
	 * 
	 * @return True if the user exists False if the user not exits.
	 */
	public boolean exist(String id); 
}
