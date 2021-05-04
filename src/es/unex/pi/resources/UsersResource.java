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

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.PasswordValidation;
import es.unex.pi.model.User;
import es.unex.pi.resources.exceptions.CustomBadRequestException;
import es.unex.pi.resources.exceptions.CustomNotFoundException;

@Path("/users")
public class UsersResource {

	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<User> getUsersJSON(@Context HttpServletRequest request) {

		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
			
			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		  
		  //3. If the user is a Manager, return all the users.
		  //   otherwise, return the users of the current user
			
			if(user.getRole().equals("Manager")) return userDao.getAll();
			else{
				List<User> onlyUser = new ArrayList<User>();
				onlyUser.add(user);
				return onlyUser;
			}
		  
	  }
	  
	  @GET
	  @Path("/{userid: [0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public User getUserJSON(@PathParam("userid") long userid,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
		
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		  
		  //3. If ((the user exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the user
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			if(userDao.get(userid) != null && (user.getRole().equals("Manager") || user.getId() == userDao.get(userid).getId())) return userDao.get(userid);
			else throw new CustomNotFoundException("User (" + userid + ") is not found");
	  }
	  
	 
	  
	  @POST	  	  
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response post(User newUser, @Context HttpServletRequest request) throws Exception {	
		
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
		
		
		Response res;
		
		Map<String, String> messages = new HashMap<String, String>();

		if (PasswordValidation.validatePassword(newUser.getPassword(), messages) // si hay errores
            || userDao.exist(newUser.getUsername())
            || newUser.getRole().equals("Manager"))
			    throw new CustomBadRequestException("Errors in parameters " + messages.toString());


		//save user in DB
		long id = userDao.add(newUser);

		res = Response 								//return 201 and Location: /users/newid
			   .created(
				uriInfo.getAbsolutePathBuilder()
					   .path(Long.toString(id))
					   .build())
			   .contentLocation(					//Content Location: /user/newid
				uriInfo.getAbsolutePathBuilder()
				       .path(Long.toString(id))
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
		  
		  //1. You must connect to the database by using an UserDao object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
		  
		Response res;
		
        User newUser = new User();

        newUser.setUsername(formParams.getFirst("username"));
        newUser.setPassword(formParams.getFirst("password"));
        newUser.setEmail(formParams.getFirst("username"));
        newUser.setRole(formParams.getFirst("role"));
        newUser.setImage(formParams.getFirst("image"));
        newUser.setGenero(formParams.getFirst("genero"));


		Map<String, String> messages = new HashMap<String, String>();
		if (PasswordValidation.validatePassword(newUser.getPassword(), messages) // hay errores
			|| userDao.exist(newUser.getUsername())
			|| newUser.getRole().equals("Manager"))
			    throw new CustomBadRequestException("Errors in parameters : " + messages.toString());
		
		//save order in DB
		long id = userDao.add(newUser);

		res = Response //return 201 and Location: /users/newid
				   .created(
					uriInfo.getAbsolutePathBuilder()
						   .path(Long.toString(id))
						   .build())
				   .contentLocation(
					uriInfo.getAbsolutePathBuilder()
					       .path(Long.toString(id))
					       .build())
					.build();
		 return res;  
	  }
	  
	  
	  
	  @PUT
	  @Path("/{userid: [0-9]+}") // Comlete the path
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(User userUpdate,
							@PathParam("userid") long userid,
							@Context HttpServletRequest request) throws Exception{

		
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);

		  //2. You must obtain the user that has logged into the system
			
          HttpSession session =  request.getSession();
          User user = (User) session.getAttribute("user");

		  Response response = null;
					
		  //We check that the user exists
		  User user_upd = userDao.get(userUpdate.getId());
		  if (user_upd != null && (user_upd.getId() == userid || user.getRole().equals("Manager"))
                && (!userUpdate.getRole().equals("Manager") && user.getRole().equals("User")) ){
					if (user_upd.getId()!=userid) throw new CustomBadRequestException("Error in id");
					else {
						
						// 3. If the user is valid
						//       update the user
						//    otherwise
						//       throw a CustomBadRequestException   
						
						Map <String, String> messages = new HashMap<String, String>();
						if(!PasswordValidation.validatePassword(userUpdate.getPassword(), messages)){ // no hay errores
							userDao.save(userUpdate);
							response = Response.noContent().build(); // 204 no content
						}
						else throw new CustomBadRequestException("Errors in parameters : " + messages.toString());
						
					}
				}
		  else throw new WebApplicationException(Response.Status.NOT_FOUND); // 404 not found			
		  
		  return response;
		}
	  
	  

	@DELETE
	  @Path("/{userid: [0-9]+}") // Complete the path	  
	  public Response deleteUser(@PathParam("userid") long userid,
			  					  @Context HttpServletRequest request) {
		  
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDAO object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		
		
		if ((user != null)&&(user.getId() == userid)){
				
				//3. Delete the user
				userDao.delete(userid);
			
				return Response.noContent().build(); //204 no content 
		}
		else throw new CustomBadRequestException("Error in user or id");		
			
	  }
	  
	  
} 
