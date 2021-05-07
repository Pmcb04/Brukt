package es.unex.pi.resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
import es.unex.pi.dao.FavoriteDAO;
import es.unex.pi.dao.JDBCCategoryDAOimpl;
import es.unex.pi.dao.JDBCCommentDAOimpl;
import es.unex.pi.dao.JDBCFavoriteDAOImpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.model.Category;
import es.unex.pi.model.Comment;
import es.unex.pi.model.Favorite;
import es.unex.pi.model.Product;
import es.unex.pi.model.User;
import es.unex.pi.resources.exceptions.CustomBadRequestException;
import es.unex.pi.resources.exceptions.CustomNotFoundException;

@Path("/categories")
public class CategoriesResource {

	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Category> getCategoriesJSON(@Context HttpServletRequest request) {

		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
			
			CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
			categoryDao.setConnection(conn);
		 
		  //3. If the user is a Manager, return all the users.
		  //   otherwise, return the users of the current user
			
            return categoryDao.getAll();
		  
	  }
	  
	  @GET
	  @Path("/id/{categoryid: [A-Za-zÑñ0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public Category getCategoryIdJSON(@PathParam("categoryid") String categoryid,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
			categoryDao.setConnection(conn);
	
		  //3. If ((the user exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the user
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			if(categoryDao.exist(categoryid)) return categoryDao.get(categoryid);
			else throw new CustomNotFoundException("Category with id (" + categoryid + ") is not found");
	  }

      @GET
	  @Path("/name/{categoryname: [A-Za-zÑñ0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public Category getCategoryNameJSON(@PathParam("categoryname") String categoryName,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
			categoryDao.setConnection(conn);
	
		  //3. If ((the user exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the user
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
            Category category_name = categoryDao.getByName(categoryName);

			if(category_name != null) return category_name;
			else throw new CustomNotFoundException("Category with name (" + categoryName + ") is not found");
	  }
	  
	  
	 
	  
	  @POST	  	  
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response post(Category newCategory, @Context HttpServletRequest request) throws Exception {	
		
		// Complete the code to implement this method.
		  
		//1. You must connect to the database by using an UserDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
			categoryDao.setConnection(conn);

		//2. You must obtain the user that has logged into the system

			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		
		
		Response res;


		if (!user.getRole().equals("Manager") || newCategory == null || categoryDao.exist(newCategory.getId()))
			    throw new CustomBadRequestException("Errors in parameters ");

		//save category in DB
		String id = categoryDao.add(newCategory);

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
        
       		CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
        	categoryDao.setConnection(conn);

      
         	Response res;

		//2. You must obtain the user that has logged into the system

			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");


        	Category newCategory = new Category();

        	newCategory.setId(formParams.getFirst("id"));
         	newCategory.setName(formParams.getFirst("name"));
         	newCategory.setImage(formParams.getFirst("image"));


        if (!user.getRole().equals("Manager") || newCategory == null || categoryDao.exist(newCategory.getId()))
              throw new CustomBadRequestException("Errors in parameters ");

         //save category in DB
        String id = categoryDao.add(newCategory);

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
	  
	  
	  
	  @PUT
	  @Path("/{categoryid: [A-Za-zÑñ0-9]+}") // Complete the path
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response put(Category categoryUpdate,
							@PathParam("categoryid") String categoryId,
							@Context HttpServletRequest request) throws Exception{

		
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDao object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
			categoryDao.setConnection(conn);

		  //2. You must obtain the user that has logged into the system
			
          	HttpSession session =  request.getSession();
          	User user = (User) session.getAttribute("user");

		 	Response response = null;
					
		  //We check that the user exists
		  if (user != null && user.getRole().equals("Manager") && categoryDao.exist(categoryId)){
					if (!categoryDao.get(categoryId).getId().equals(categoryId)) throw new CustomBadRequestException("Error in id");
					else {
						
						// 3. If the user is valid
						//       update the user
						//    otherwise
						//       throw a CustomBadRequestException   
					
							categoryDao.save(categoryUpdate);
							response = Response.noContent().build(); // 204 no content
						
					}
				}
		  else throw new WebApplicationException(Response.Status.NOT_FOUND); // 404 not found			
		  
		  return response;
		}
	  
	  

	@DELETE
	@Path("/{categoryid: [A-Za-zÑñ0-9]+}") // Complete the path
	public Response deleteUser(@PathParam("categoryid") String categoryId,
			  					  @Context HttpServletRequest request) {
		  
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an UserDAO object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
			categoryDao.setConnection(conn);
			
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
			
			FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
			favoriteDao.setConnection(conn);
			
			CommentDAO commentDao = new JDBCCommentDAOimpl();
			commentDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		
		
		if (user != null && user.getRole().equals("Manager") && categoryDao.exist(categoryId)) {
				
				//3. Delete the category

				List<Product> products = new ArrayList<Product>();
				List<Favorite> favorites = new ArrayList<Favorite>();
				List<Comment> comments = new ArrayList<Comment>();
				
				// Obtenemos todos los productos de la categoria
				products = productDao.getAllByCategory(categoryId);
				
				for (Product product : products) {
					
					// Borramos todos los favoritos de los productos que tiene la categoria
					favorites = favoriteDao.getAllFavoritesByProduct(product.getId());
					for (Favorite favorite : favorites) {
						favoriteDao.delete(favorite);
					}
					
					// Borramos todos los comentarios de este producto
					comments = commentDao.getAllByProduct(product.getId());
					for (Comment comment : comments) {
						commentDao.delete(comment.getUsername(), comment.getIdp());
					}
					
					// Borramos el producto de la categoria
					productDao.delete(product.getId());
					
				}
				
				 // Borramos la categoria
				categoryDao.delete(categoryId);
				return Response.noContent().build(); //204 no content 
		}
		else throw new CustomBadRequestException("Error in user or id");		
			
	  }
	  
	  
} 
