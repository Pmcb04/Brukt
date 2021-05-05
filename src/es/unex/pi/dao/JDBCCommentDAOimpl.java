package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Comment;

public class JDBCCommentDAOimpl implements CommentDAO{

	
	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCProductDAOImpl.class.getName());
	
	@Override
	public Comment get(String username, long idp) {
	if (conn == null) return null;
		
		Comment comment = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM comments WHERE username='"+ username + "' and idp=" + idp);			 
			if (!rs.next()) return null; 
			comment = new Comment();	 
			comment.setIdp(Integer.parseInt(rs.getString("idp")));
			comment.setUsername(rs.getString("username"));
			comment.setDate(rs.getString("date"));
			comment.setDescription(rs.getString("description"));
			comment.setRating(Integer.parseInt(rs.getString("rating")));
			logger.info("fetching Comment by username: "+username+" -> " + comment.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}	


	@Override
	public List<Comment> getAllByRatingProduct(int rating, long idp) {
		if (conn == null)
			return null;

		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM comments WHERE rating = "+ rating + " and idp=" + idp);

			while (rs.next()) {
				Comment comment = new Comment();
				comment.setIdp(Integer.parseInt(rs.getString("idp")));
				comment.setUsername(rs.getString("username"));
				comment.setDate(rs.getString("date"));
				comment.setDescription(rs.getString("description"));
				comment.setRating(Integer.parseInt(rs.getString("rating")));
				
				comments.add(comment);
				logger.info("fetching category: "+comment.toString());		
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return comments;
	}

	@Override
	public List<Comment> getAllByProduct(long idp) {
		if (conn == null)
			return null;

		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM comments WHERE idp ="+ idp);

			while (rs.next()) {
				Comment comment = new Comment();
				comment.setIdp(Integer.parseInt(rs.getString("idp")));
				comment.setUsername(rs.getString("username"));
				comment.setDate(rs.getString("date"));
				comment.setDescription(rs.getString("description"));
				comment.setRating(Integer.parseInt(rs.getString("rating")));
				
				comments.add(comment);
				logger.info("fetching category: "+comment.toString());		
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return comments;
	}

	@Override
	public List<Comment> getAll() {
		if (conn == null) return null;
		
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM comments");
			while ( rs.next() ) {
				Comment comment = new Comment();
				comment.setIdp(Integer.parseInt(rs.getString("idp")));
				comment.setUsername(rs.getString("username"));
				comment.setDate(rs.getString("date"));
				comment.setDescription(rs.getString("description"));
				comment.setRating(Integer.parseInt(rs.getString("rating")));
				
				comments.add(comment);
				logger.info("fetching category: "+comment.toString());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}

	@Override
	public String add(Comment comment) {
		String id="fail";
		String lastidu="fail";
		if (conn != null){

			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='comments'");	
				if (!rs.next()) return "fail"; 
				lastidu=rs.getString("seq");
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO comments (username,idp,date,description,rating) VALUES('"
									+comment.getUsername()+"','"
									+comment.getIdp()+"','"
									+comment.getDate()+"','"
									+comment.getDescription()+"','"
									+comment.getRating()+"')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='comments'");		
				if (!rs.next()) return "fail"; 
				id=rs.getString("seq");
											
				logger.info("CREATING " + comment.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return id;

	}

	@Override
	public boolean delete(String username, long idp) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM comments WHERE username = '"+username + "' and idp=" + idp);
				logger.info("deleting Comment: "+username);
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}

	@Override
	public boolean exist(String username, long idp) {
		return get(username, idp) != null;
	}
	
	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
		
	}


}
