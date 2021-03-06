package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.User;


public interface UserDAO {

	/**
	 * Sets the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);

	/**
	 * Gets an user from the DB using idu.
	 * 
	 * @param id
	 *            User Identifier.
	 * 
	 * @return User object with that id.
	 */
	public User get(long id);

	/**
	 * Gets an user from the DB using username.
	 * 
	 * @param username
	 *            Username of the user.
	 * 
	 * @return User object with that username.
	 */
	public User get(String username);


	/**
	 * Gets an user from the DB using username with password.
	 * 
	 * @param username
	 *            Username of the user.
	 * 
	 * @return User object with that username.
	 */
	public User getPassword(String username);


	/**
	 * Gets all the users from the database.
	 * 
	 * @return List of all the users from the database.
	 */
	public List<User> getAll();


	/**
	 * Gets an user from the DB using username.
	 * 
	 * @param password
	 *            password that we check of the user.
	 * @param username
	 *            username user that we check of the password.
	 * 
	 * @return User object with that username.
	 */
	public boolean checkPassword(String password, String username);

	/**
	 * Adds an user to the database.
	 * 
	 * @param user
	 *            User object with the user details.
	 * 
	 * @return User identifier or -1 in case the operation failed.
	 */
	public long add(User user);

	/**
	 * Updates an existing user in the database.
	 * 
	 * @param user
	 *            User object with the new details of the existing user.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean save(User user);

	/**
	 * Deletes an user from the database.
	 * 
	 * @param idu
	 *            User identifier.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean delete(long idu);
	
	/**
	 * Check if the user have account in the database
	 * 
	 * @param username
	 *            String username
	 * 
	 * @return True if the user exists False if the user not exits.
	 */
	public boolean exist(String username); 
}
