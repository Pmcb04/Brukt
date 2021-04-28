package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Category;

public class JDBCCategoryDAOimpl implements CategoryDAO{

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCProductDAOImpl.class.getName());
	
	@Override
	public Category get(String id) {
	if (conn == null) return null;
		
		Category category = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM categories WHERE id="+id);			 
			if (!rs.next()) return null; 
			category = new Category();	 
			category.setId(rs.getString("id"));
			category.setName(rs.getString("name"));
			category.setImage(rs.getString("image"));
			logger.info("fetching User by id: "+id+" -> " + category.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}
	
	@Override
	public Category getByName(String name) {
	if (conn == null) return null;
		
		Category category = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM categories WHERE name = "+name);		 
			if (!rs.next()) return null; 
			category = new Category();	 
			category.setId(rs.getString("id"));
			category.setName(rs.getString("name"));
			category.setImage(rs.getString("image"));
			logger.info("fetching Category by name: "+name+" -> " + category.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}
	
	
	

	public List<Category> getAll() {

		if (conn == null) return null;
		
		ArrayList<Category> categories = new ArrayList<Category>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM categories");
			while ( rs.next() ) {
				Category category = new Category();
				category.setId(rs.getString("id"));
				category.setName(rs.getString("name"));
				category.setImage(rs.getString("image"));
				
				categories.add(category);
				logger.info("fetching category: "+category.toString());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}
	


	@Override
	public String add(Category category) {
		String id="fail";
		String lastidu="fail";
		if (conn != null){

			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='categories'");	
				if (!rs.next()) return "fail"; 
				lastidu=rs.getString("seq");
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO users (id,name,image) VALUES('"
									+category.getId()+"','"
									+category.getName()+"','"
									+category.getImage()+"')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='categories'");		
				if (!rs.next()) return "fail"; 
				id=rs.getString("seq");
											
				logger.info("CREATING " + category.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return id;
	}


	@Override
	public boolean save(Category category) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("UPDATE users SET name='"+category.getName()
									+"', image='"+category.getImage()
									+"' WHERE id = "+category.getId());
				logger.info("updating " + category.toString() );
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return done;

	}


	@Override
	public boolean delete(String id) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM categories WHERE id ="+id);
				logger.info("deleting Category: "+id);
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}

	@Override
	public void setConnection(Connection conn) {
		// TODO Auto-generated method stub
		this.conn = conn;
	}

	@Override
	public boolean exist(String id) {
		return get(id) != null;
	}

}
