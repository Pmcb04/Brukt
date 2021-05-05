package es.unex.pi.resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.unex.pi.dao.CategoryDAO;
import es.unex.pi.dao.CommentDAO;
import es.unex.pi.dao.JDBCCategoryDAOimpl;
import es.unex.pi.dao.JDBCCommentDAOimpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Category;
import es.unex.pi.model.Comment;
import es.unex.pi.model.PasswordValidation;
import es.unex.pi.model.User;
import es.unex.pi.resources.exceptions.CustomBadRequestException;
import es.unex.pi.resources.exceptions.CustomNotFoundException;

@Path("/comments")
public class CommentsResource {

	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Comment> getCommentsJSON(@Context HttpServletRequest request) {

		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
			
			CommentDAO commentDao = new JDBCCommentDAOimpl();
			commentDao.setConnection(conn);
		 
		  //3. If the user is a Manager, return all the users.
		  //   otherwise, return the users of the current user
			
            return commentDao.getAll();
		  
	  }


	  @GET
	  @Path("/product/{productid: [0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Comment> getCommentProductJSON(@PathParam("productid") long productid,
			  									  @Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);

			CommentDAO commentDao = new JDBCCommentDAOimpl();
			commentDao.setConnection(conn);
	
		  //3. If ((the user exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the user
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found

			if(productDao.exist(productid)) return commentDao.getAllByProduct(productid);
			else throw new CustomNotFoundException("Comments with id (" + productid + ") is not found");
	  }
	  
	  
	  
	  @GET
	  @Path("/product/{productid: [0-9]+}/user/{userusername: [A-Za-zñÑ0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public Comment getCommentJSON(@PathParam("productid") long productid,
	  								@PathParam("userusername") String userUsername,
			  						@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			CommentDAO commentDao = new JDBCCommentDAOimpl();
			commentDao.setConnection(conn);
	
		  //3. If ((the user exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the user
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			if(commentDao.exist(userUsername, productid)) return commentDao.get(userUsername, productid);
			else throw new CustomNotFoundException("Comment with id product (" + productid + ") and user username (" + userUsername + ") is not found");
	  }

      @GET
	  @Path("/product/{productid: [0-9]+}/rating/{ratingNumber: [0-5]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Comment> getCommentRatingProductJSON(@PathParam("productid") long productid,
	  											  @PathParam("ratingNumber") int rating,
			  									  @Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);

			CommentDAO commentDao = new JDBCCommentDAOimpl();
			commentDao.setConnection(conn);
	
		  //3. If ((the user exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the user
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found

			if(productDao.exist(productid)) return commentDao.getAllByRatingProduct(rating, productid);
			else throw new CustomNotFoundException("Comments with id (" + productid + ") and rating ("+ rating + ") is not found");
	  }

	  
	  @POST	  	  
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response post(Comment newComment, @Context HttpServletRequest request) throws Exception {	
		
		// Complete the code to implement this method.
		  
		//1. You must connect to the database by using an UserDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			CommentDAO commentDao = new JDBCCommentDAOimpl();
			commentDao.setConnection(conn);

			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);

		//2. You must obtain the user that has logged into the system

			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		
		
		Response res;


		if ( newComment ==  null  
			||!user.getUsername().equals(newComment.getUsername()) 
			||commentDao.exist(newComment.getUsername(), newComment.getIdp())
			||!productDao.exist(newComment.getIdp()))
			   				throw new CustomBadRequestException("Errors in parameters");

		//save comment in DB
		String id = commentDao.add(newComment);

		res = Response 								//return 201 and Location: /users/newid
			   .created(
				uriInfo.getAbsolutePathBuilder()
					   .path(id)
					   .build())
			   .contentLocation(					//Content Location: /user/newid
				uriInfo.getAbsolutePathBuilder()
				       .path(id)
				       .build())
				.build();
		
		return res; 
	  }
	  
	  
	   //POST that receives a new order via webform
	  @POST	  	 
	  @Consumes("application/x-www-form-urlencoded")
	  public Response post(MultivaluedMap<String, String> formParams,
			               @Context HttpServletRequest request) {

		
		// Complete the code to implement this method.
			
		//1. You must connect to the database by using an UserDAO object.
				
			Connection conn = (Connection) sc.getAttribute("dbConn");
					
			CommentDAO commentDao = new JDBCCommentDAOimpl();
			commentDao.setConnection(conn);

			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
			
		//2. You must obtain the user that has logged into the system

			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");

			Comment newComment = new Comment();
			newComment.setUsername(formParams.getFirst("username"));
			newComment.setIdp(Long.parseLong(formParams.getFirst("idp")));
			newComment.setDate(formParams.getFirst("date"));
			newComment.setDescription(formParams.getFirst("description"));
			newComment.setRating(Integer.parseInt(formParams.getFirst("rating")));
		
		
		Response res;


		if ( newComment ==  null  
			||!user.getUsername().equals(newComment.getUsername()) 
			||commentDao.exist(newComment.getUsername(), newComment.getIdp())
			||!productDao.exist(newComment.getIdp()))
							throw new CustomBadRequestException("Errors in parameters");

		//save comment in DB
		String id = commentDao.add(newComment);

		res = Response 								//return 201 and Location: /users/newid
			.created(
				uriInfo.getAbsolutePathBuilder()
					.path(id)
					.build())
			.contentLocation(					//Content Location: /user/newid
				uriInfo.getAbsolutePathBuilder()
					.path(id)
					.build())
				.build();
		
		return res; 
	}
	  

	@DELETE
	@Path("/product/{productid: [0-9]+}/user/{userusername: [A-Za-zñÑ0-9]+}")	  // Complete the path
	public Response deleteComment(@PathParam("productid") long productid,
							      @PathParam("userusername") String userUsername,
			  				      @Context HttpServletRequest request) {
		  
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDAO object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			CommentDAO commentDao = new JDBCCommentDAOimpl();
			commentDao.setConnection(conn);

			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");

		
		if (user != null && productDao.exist(productid) && (user.getUsername().equals(userUsername) || user.getRole().equals("Manager"))) {
				
				//3. Delete the category
				commentDao.delete(userUsername, productid);
				return Response.noContent().build(); //204 no content 
		}
		else throw new CustomBadRequestException("Error in user or id");		
			
	  }
	  
	  
} 
