package es.unex.pi.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.unex.pi.dao.FavoriteDAO;
import es.unex.pi.dao.JDBCFavoriteDAOImpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Favorite;
import es.unex.pi.model.Product;
import es.unex.pi.model.User;
import es.unex.pi.resources.exceptions.CustomBadRequestException;
import es.unex.pi.resources.exceptions.CustomNotFoundException;

@Path("/favorites")
public class FavoritesResource {

	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Favorite> getFavoritesJSON(@Context HttpServletRequest request) {

		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
			
			FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
			favoriteDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		  
		  //3. If the user is a Manager, return all the users.
		  //   otherwise, return the users of the current user
			
			if(user.getRole().equals("Manager")) return favoriteDao.getAll();
			else return favoriteDao.getAllFavoritesByUser(user.getId());
		  
	  }
	  
	  @GET
	  @Path("/user/{userid: [0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Product> getUserFavoritesJSON(@PathParam("userid") long userid,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
			favoriteDao.setConnection(conn);

			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
		
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		  
		  //3. If ((the user exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the user
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			if(userDao.exist(userDao.get(userid).getUsername())) return favoriteDao.getAllByUser(userid);
			else throw new CustomNotFoundException("Favorites userid (" + userid + ") is not found");
	  }


	  @GET
	  @Path("/product/{productid: [0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<User> getProductFavoritesJSON(@PathParam("productid") long productid,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
			favoriteDao.setConnection(conn);

			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		  
		  //3. If ((the user exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the user
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			if(productDao.exist(productid)) return favoriteDao.getAllByProduct(productid);
			else throw new CustomNotFoundException("Favorite with productid (" + productid + ") is not found");
	  }
	  
	 
	  
	  @POST	  	  
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response post(Favorite newFavorite, @Context HttpServletRequest request) throws Exception {	
		
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an FavoriteDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);

			FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
			favoriteDao.setConnection(conn);

			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);

					
		  //2. You must obtain the user that has logged into the system
			
		  HttpSession session =  request.getSession();
		  User user = (User) session.getAttribute("user");

		
		
		Response res;

		Product productFav = productDao.get(newFavorite.getIdp());

		System.out.println("new Favorite idu " + newFavorite.getIdu());
		System.out.println("user idu " + user.getId());
		System.out.println("new Favorite idp " + newFavorite.getIdp());

		if (user == null || productFav == null || user.getId() == productFav.getIdu()
            || favoriteDao.exist(newFavorite.getIdu(), newFavorite.getIdp()) )
			    throw new CustomBadRequestException("Errors in parameters ");


		//save user in DB
		long id = favoriteDao.add(newFavorite);

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
		  
		  //1. You must connect to the database by using an FavoriteDAO object.
			
		  Connection conn = (Connection) sc.getAttribute("dbConn");
				
		  UserDAO userDao = new JDBCUserDAOImpl();
		  userDao.setConnection(conn);

		  FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
		  favoriteDao.setConnection(conn);

		  ProductDAO productDao = new JDBCProductDAOImpl();
		  productDao.setConnection(conn);

				  
		//2. You must obtain the user that has logged into the system
		  
		HttpSession session =  request.getSession();
		User user = (User) session.getAttribute("user");

		Favorite newFavorite = new Favorite();
		newFavorite.setIdu(Long.parseLong(formParams.getFirst("idu")));
		newFavorite.setIdp(Long.parseLong(formParams.getFirst("idp")));
	  
	  
	  Response res;

	  User userFav = userDao.get(newFavorite.getIdu());
	  Product productFav = productDao.get(newFavorite.getIdp());

	  if (favoriteDao.exist(newFavorite.getIdu(), newFavorite.getIdp()) ||userFav == null || productFav == null
		  || user.getId() != newFavorite.getIdu())
			  throw new CustomBadRequestException("Errors in parameters ");


	  //save user in DB
	  long id = favoriteDao.add(newFavorite);

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
	  

	@DELETE
	  @Path("/user/{userid: [0-9]+}/product/{productid: [0-9]+}") // Complete the path	  
	  public Response deleteOrder(@PathParam("userid") long userid,
	  							  @PathParam("productid") long productid,
			  					  @Context HttpServletRequest request) {
		  
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDAO object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
			favoriteDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		
		
		if ((user != null) && (user.getId() == userid) && favoriteDao.exist(userid, productid)){
				
				//3. Delete the favorite
				Favorite fav = new Favorite();
				fav.setIdp(productid);
				fav.setIdu(userid);
				favoriteDao.delete(fav);
			
				return Response.noContent().build(); //204 no content 
		}
		else throw new CustomBadRequestException("Error in user or id");		
			
	  }
	  
	  
} 
