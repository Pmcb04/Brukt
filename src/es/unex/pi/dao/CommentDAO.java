package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.Comment;


public interface CommentDAO {

	/**
	 * Sets the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);

	/**
	 * Gets an Comment from the DB using username.
	 * 
	 * @param id
	 *            User username.
	 * 
	 * @return Category object with that username.
	 */
	public Comment get(String username, long idp);
	
	
	/**
	 * Gets all category from the DB using name.
	 * 
	 * @param rating
	 *            rating comments.
	 * 
	 * @return List Category object with that name.
	 */
	public List<Comment> getAllByRatingProduct(int rating, long idp);
	
	/**
	 * Gets all comment from the DB using idp.
	 * 
	 * @param idp
	 *            Product Identifier.
	 * 
	 * @return List Category object with that name.
	 */
	public List<Comment> getAllByProduct(long idp);

	
	/**
	 * Gets all the category from the database.
	 * 
	 * @return List of all the category from the database.
	 */
	public List<Comment> getAll();

	/**
	 * Adds a category to the database.
	 * 
	 * @param category
	 *            Category object with the category details.
	 * 
	 * @return Category identifier or "fail" in case the operation failed.
	 */
	public String add(Comment comment);

	/**
	 * Deletes an user from the database.
	 * 
	 * @param id
	 *            String username.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean delete(String username, long idp);
	
	/**
	 * Check if the comment exist in the database
	 * 
	 * @param username
	 *            String username
	 * 
	 * @return True if the comment exists False if the comment not exits.
	 */
	public boolean exist(String username, long idp); 
}
