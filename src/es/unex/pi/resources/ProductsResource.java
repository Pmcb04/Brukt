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
import es.unex.pi.dao.FavoriteDAO;
import es.unex.pi.dao.JDBCCategoryDAOimpl;
import es.unex.pi.dao.JDBCCommentDAOimpl;
import es.unex.pi.dao.JDBCFavoriteDAOImpl;
import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Favorite;
import es.unex.pi.model.Product;
import es.unex.pi.model.ProductValidation;
import es.unex.pi.model.User;
import es.unex.pi.model.Comment;
import es.unex.pi.resources.exceptions.CustomBadRequestException;
import es.unex.pi.resources.exceptions.CustomNotFoundException;

@Path("/products")
public class ProductsResource {

	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Product> getProductsJSON(@Context HttpServletRequest request) {

		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
			
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		  
		  //3. If the user is a Manager, return all the products.
		  //   otherwise, return the products of the current user
		  // TODO : ¿tiene sentido?
			
		 return productDao.getAllByUser(user.getId());
		  
	  }
	  
	  @GET
	  @Path("/product/{productid: [0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public Product getProductJSON(@PathParam("productid") long productid,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		  
		  //3. If ((the product exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the product
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			if(productDao.get(productid) != null) return productDao.get(productid);
			else throw new CustomNotFoundException("Product (" + productid + ") is not found");
	  }

	  @GET
	  @Path("/search/title/{searchTitle: [A-Za-zÑñ0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Product> getProductSearchTitleJSON(@PathParam("searchTitle") String search,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		
		  //3. If ((the product exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the product
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			return productDao.getAllBySearchTitle(search);
	  }

	  @GET
	  @Path("/search/description/{searchDesc: [A-Za-zÑñ0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Product> getProductSearchDescriptionJSON(@PathParam("searchDesc") String search,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		
		  //3. If ((the product exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the product
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			return productDao.getAllBySearchDescription(search);
	  }

	  @GET
	  @Path("/search/all/{searchAll: [A-Za-zÑñ0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Product> getProductSearchAllJSON(@PathParam("searchAll") String search,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		
		  //3. If ((the product exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the product
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			return productDao.getAllBySearchAll(search);
	  }

	  @GET
	  @Path("/user/{userid: [0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Product> getProductsUserJSON(@PathParam("userid") long userId,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);

			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
		
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		  
		  //3. If ((the product exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the product
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
		
			if(userDao.exist(userDao.get(userId).getUsername())) return productDao.getAllByUser(userId);
			else throw new CustomNotFoundException("Products with id user  (" + userId + ") is not found");

	  }

	  @GET
	  @Path("/user/{userid: [0-9]+}/sold/{soldid: [0-1]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Product> getProductSoldJSON(@PathParam("userid") long userId,
	  										   @PathParam("soldid") int soldId,
			  								   @Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);

			UserDAO userDao = new JDBCUserDAOImpl();
			userDao.setConnection(conn);
		
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
			
		  
		  //3. If ((the product exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the product
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
		
			if(userDao.exist(userDao.get(userId).getUsername()) && soldId == 0) return productDao.getAllByUserSale(userId);
			else if(user != null && userDao.exist(userDao.get(userId).getUsername()) && soldId > 0) return productDao.getAllByUserSold(userId);
			else throw new CustomNotFoundException("Products with id user  (" + userId + ") is not found");

	  }



		@GET
		@Path("/category/{categoryid: [A-Za-zÑñ0-9]+}/price/{pricenumb: [0-9]+?[.]?[0-9]+?}")	  // Complete the path
		@Produces(MediaType.APPLICATION_JSON)
		public List<Product> getProductCategoryPriceJSON(@PathParam("categoryid") String categoryId,
														@PathParam("pricenumb") float priceNumb,
														@Context HttpServletRequest request) {

			//Complete the code to implement this method.
			
			//1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);

			CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
			categoryDao.setConnection(conn);
			
			
			//3. If ((the product exists) && ((it belongs to the current user) || (user is a manager)))
			//       return the product
			//   otherwise 
			//       throw a CustomNotFoundException with the id of the order not found
			
			if(categoryDao.exist(categoryId)) return productDao.getAllByCategoryPrice(categoryId, priceNumb);
			else throw new CustomNotFoundException("Category (" + categoryId + ") with price (" + priceNumb + ") is not found");
		}
		


	  @GET
	  @Path("/category/{categoryid: [A-Za-zÑñ0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Product> getProductCategoryJSON(@PathParam("categoryid") String categoryId,
			  					@Context HttpServletRequest request) {

		  //Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);

			CategoryDAO categoryDao = new JDBCCategoryDAOimpl();
			categoryDao.setConnection(conn);
			
			System.out.println(categoryId);

		  
		  //3. If ((the product exists) && ((it belongs to the current user) || (user is a manager)))
		  //       return the product
		  //   otherwise 
		  //       throw a CustomNotFoundException with the id of the order not found
			
			if(categoryDao.exist(categoryId) && !categoryId.equals("Todas")) return productDao.getAllByCategory(categoryId);
			else if(categoryDao.exist(categoryId) && categoryId.equals("Todas")) return productDao.getAll();
			else throw new CustomNotFoundException("Category (" + categoryId + ") is not found");
	  }


	  @POST	  	  
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response post(Product newProduct, @Context HttpServletRequest request) throws Exception {	
		
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
			
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		
		
		Response res;
		
		Map<String, String> messages = new HashMap<String, String>();

		if (ProductValidation.validateProduct(newProduct, messages) // si hay errores
			||user.getId() != newProduct.getIdu())
			    throw new CustomBadRequestException("Errors in parameters " + messages.toString());


		//save product in DB
		long id = productDao.add(newProduct);

		res = Response 								//return 201 and Location: /products/newid
			   .created(
				uriInfo.getAbsolutePathBuilder()
					   .path(Long.toString(id))
					   .build())
			   .contentLocation(					//Content Location: /products/newid
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
		  
		  //1. You must connect to the database by using an ProductDAO object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		  
		Response res;
		
        Product newProduct = new Product();

        newProduct.setTitle(formParams.getFirst("title"));
        newProduct.setDescription(formParams.getFirst("description"));
        newProduct.setCategory(formParams.getFirst("category"));
        newProduct.setStock(Integer.parseInt(formParams.getFirst("stock")));
        newProduct.setCurrency(formParams.getFirst("currency"));
        newProduct.setPrice(Float.parseFloat(formParams.getFirst("price")));
        newProduct.setImage(formParams.getFirst("image"));
        newProduct.setRapido(formParams.getFirst("rapido"));
        newProduct.setPremium(formParams.getFirst("premium"));
		newProduct.setIdu(Long.parseLong(formParams.getFirst("idu")));


		Map<String, String> messages = new HashMap<String, String>();
		if (ProductValidation.validateProduct(newProduct, messages) // hay errores
			|| user.getId() != newProduct.getIdu())
			    throw new CustomBadRequestException("Errors in parameters : " + messages.toString());
		
		//save order in DB
		long id = productDao.add(newProduct);

		res = Response //return 201 and Location: /products/newid
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
	  @Path("/{productid: [0-9]+}") // Comlete the path
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(Product productUpdate,
							@PathParam("productid") long productid,
							@Context HttpServletRequest request) throws Exception{

		
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
			
		  Response response = null;
					
		  //We check that the product exists
		  Product product = productDao.get(productUpdate.getId());
		  if ((product != null) &&(user.getId() == product.getIdu())){
					if (product.getId()!=productid) throw new CustomBadRequestException("Error in id");
					else {
						
						// 3. If the product is valid
						//       update the product
						//    otherwise
						//       throw a CustomBadRequestException   
						
						Map <String, String> messages = new HashMap<String, String>();
						if(!ProductValidation.validateProduct(productUpdate, messages)){ // no hay errores
							productDao.save(productUpdate);
							response = Response.noContent().build(); // 204 no content
						}
						else throw new CustomBadRequestException("Errors in parameters : " + messages.toString());
						
					}
				}
		  else throw new WebApplicationException(Response.Status.NOT_FOUND); // 404 not found			
		  
		  return response;
		}
	  
	  

	@DELETE
	  @Path("/{productid: [0-9]+}") // Complete the path	  
	  public Response deleteProduct(@PathParam("productid") long productid,
			  					  @Context HttpServletRequest request) {
		  
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);

			FavoriteDAO favoriteDao = new JDBCFavoriteDAOImpl();
			favoriteDao.setConnection(conn);
			
			CommentDAO commentDao = new JDBCCommentDAOimpl();
			commentDao.setConnection(conn);
			
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		
		Product product = productDao.get(productid);
		
		if ((product != null)&&(user.getId() == product.getIdu() || user.getRole().equals("Manager"))){
				
				//3. Delete the product		
					
					List<Favorite> favorites = new ArrayList<Favorite>();
					List<Comment> comments = new ArrayList<Comment>();
					
						
					// Borramos todos los favoritos de este producto
					favorites = favoriteDao.getAllFavoritesByProduct(product.getId());
					for (Favorite favorite : favorites) {
						favoriteDao.delete(favorite);
					}
					
					// Borramos todos los comentarios de este producto
					comments = commentDao.getAllByProduct(product.getId());
					for (Comment comment : comments) {
						commentDao.delete(comment.getUsername(), comment.getIdp());
					}
							
					// Borramos el producto
					productDao.delete(product.getId());
					return Response.noContent().build(); //204 no content 
		}
		else throw new CustomBadRequestException("Error in user or id");		
			
	  }
	  
	  
} 
