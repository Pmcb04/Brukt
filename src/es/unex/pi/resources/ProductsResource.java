package es.unex.pi.resources;

import java.sql.Connection;
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

import es.unex.pi.dao.JDBCProductDAOImpl;
import es.unex.pi.dao.ProductDAO;
import es.unex.pi.model.Product;
import es.unex.pi.model.ProductValidation;
import es.unex.pi.model.User;
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
	  public List<Product> getOrdersJSON(@Context HttpServletRequest request) {

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
			
			if(user.getRole().equals("Manager")) return productDao.getAll();
			else return productDao.getAllByUser(user.getId());
		  
	  }
	  
	  @GET
	  @Path("/{productid: [0-9]+}")	  // Complete the path
	  @Produces(MediaType.APPLICATION_JSON)
	  public Product getOrderJSON(@PathParam("productid") long productid,
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
			
			if(productDao.get(productid) != null && (user.getRole().equals("Manager") || user.getId() == productDao.get(productid).getIdu())) return productDao.get(productid);
			else throw new CustomNotFoundException("Product (" + productid + ") is not found");
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

		if (!ProductValidation.validateProduct(newProduct, messages)
			||user.getId() != newProduct.getIdu())
			    throw new CustomBadRequestException("Errors in parameters");


		//save order in DB
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
		
        Product product = new Product();

        product.setTitle(formParams.getFirst("title"));
        product.setDescription(formParams.getFirst("description"));
        product.setCategory(formParams.getFirst("category"));
        product.setStock(Integer.parseInt(formParams.getFirst("stock")));
        product.setCurrency(formParams.getFirst("currency"));
        product.setPrice(Integer.parseInt(formParams.getFirst("price")));
        product.setImage(formParams.getFirst("image"));
        product.setRapido(formParams.getFirst("rapido"));


		Map<String, String> messages = new HashMap<String, String>();
		if (ProductValidation.validateProduct(product, messages) // hay errores
			|| user.getId() != product.getIdu())
			    throw new CustomBadRequestException("Errors in parameters");
		
		//save order in DB
		long id = productDao.add(product);

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
						else throw new CustomBadRequestException("Errors in parameters");
						
					}
				}
		  else throw new WebApplicationException(Response.Status.NOT_FOUND); // 404 not found			
		  
		  return response;
		}
	  
	  

	@DELETE
	  @Path("/{productid: [0-9]+}") // Complete the path	  
	  public Response deleteOrder(@PathParam("productid") long productid,
			  					  @Context HttpServletRequest request) {
		  
		  // Complete the code to implement this method.
		  
		  //1. You must connect to the database by using an ProductDAO object.
		 
			Connection conn = (Connection) sc.getAttribute("dbConn");
				
			ProductDAO productDao = new JDBCProductDAOImpl();
			productDao.setConnection(conn);
		 
		  //2. You must obtain the user that has logged into the system
			
			HttpSession session =  request.getSession();
			User user = (User) session.getAttribute("user");
		
		Product product = productDao.get(productid);
		
		if ((product != null)&&(user.getId() == product.getIdu())){
				
				//3. Delete the product
				productDao.delete(productid);
			
				return Response.noContent().build(); //204 no content 
		}
		else throw new CustomBadRequestException("Error in user or id");		
			
	  }
	  
	  
} 
