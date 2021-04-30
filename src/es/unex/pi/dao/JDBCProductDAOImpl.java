package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Product;

public class JDBCProductDAOImpl implements ProductDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCProductDAOImpl.class.getName());
	
	@Override
	public Product get(long id) {
		if (conn == null) return null;
		
		Product product = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE id ="+id);			 
			if (!rs.next()) return null; 
			product  = new Product();	 
			product.setId(rs.getInt("id"));
			product.setTitle(rs.getString("title"));
			product.setDescription(rs.getString("description"));
			product.setCategory(rs.getString("category"));
			product.setStock(rs.getInt("stock"));
			product.setCurrency(rs.getString("currency"));
			product.setPrice(rs.getFloat("price"));
			product.setIdu(rs.getInt("idu"));
			product.setSoldout(rs.getInt("soldout"));
			product.setImage(rs.getString("image"));
			product.setRapido(rs.getString("rapido"));
			
			logger.info("fetching products: "+product.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	public List<Product> getAll() {

		if (conn == null) return null;
		
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM product");
			while ( rs.next() ) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setCurrency(rs.getString("currency"));
				product.setPrice(rs.getFloat("price"));
				product.setIdu(rs.getInt("idu"));
				product.setSoldout(rs.getInt("soldout"));
				product.setImage(rs.getString("image"));
				product.setRapido(rs.getString("rapido"));
				
				
				products.add(product);
				logger.info("fetching products: "+product.toString());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}
	
	public List<Product> getAllBySearchTitle(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE UPPER(title) LIKE '%" + search + "%'");

			while (rs.next()) {
				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setCurrency(rs.getString("currency"));
				product.setPrice(rs.getFloat("price"));
				product.setIdu(rs.getInt("idu"));
				product.setSoldout(rs.getInt("soldout"));
				product.setImage(rs.getString("image"));
				product.setRapido(rs.getString("rapido"));
				
				products.add(product);
				
				logger.info("fetching products by text in the title: "+product.toString());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}
	
	public List<Product> getAllBySearchDescription(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE UPPER(description) LIKE '%" + search + "%'");

			while (rs.next()) {
				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setCurrency(rs.getString("currency"));
				product.setPrice(rs.getFloat("price"));
				product.setIdu(rs.getInt("idu"));
				product.setSoldout(rs.getInt("soldout"));
				product.setImage(rs.getString("image"));
				product.setRapido(rs.getString("rapido"));
				
				products.add(product);
				
				logger.info("fetching products by text in the description: "+product.toString());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}
	
	public List<Product> getAllBySearchAll(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE UPPER(title) LIKE '%" + search + "%' OR UPPER(description) LIKE '%" + search + "%'");

			while (rs.next()) {
				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setCurrency(rs.getString("currency"));
				product.setPrice(rs.getFloat("price"));
				product.setIdu(rs.getInt("idu"));
				product.setSoldout(rs.getInt("soldout"));
				product.setImage(rs.getString("image"));
				product.setRapido(rs.getString("rapido"));
				
				products.add(product);
				
				logger.info("fetching products by text either in the title or in the description: "+product.toString());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	
	public List<Product> getAllByUser(long idu) {
		
		if (conn == null)
			return null;

		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE idu = "+ idu);

			while (rs.next()) {
				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setCurrency(rs.getString("currency"));
				product.setPrice(rs.getFloat("price"));
				product.setIdu(rs.getInt("idu"));
				product.setSoldout(rs.getInt("soldout"));
				product.setImage(rs.getString("image"));
				product.setRapido(rs.getString("rapido"));
				
				products.add(product);
				
				logger.info("fetching products by text either in the title or in the description: "+product.toString());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	public List<Product> getAllByUserSale(long idu) {
		
		if (conn == null)
			return null;

		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE idu = "+ idu+ " and soldout = '0'");

			while (rs.next()) {
				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setCurrency(rs.getString("currency"));
				product.setPrice(rs.getFloat("price"));
				product.setIdu(rs.getInt("idu"));
				product.setSoldout(rs.getInt("soldout"));
				product.setImage(rs.getString("image"));
				product.setRapido(rs.getString("rapido"));
				
				products.add(product);
				
				logger.info("fetching products by text either in the title or in the description: "+product.toString());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	public List<Product> getAllByUserSold(long idu) {
		
		if (conn == null)
			return null;

		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE idu = "+ idu + " and soldout = '1'");

			while (rs.next()) {
				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setCurrency(rs.getString("currency"));
				product.setPrice(rs.getFloat("price"));
				product.setIdu(rs.getInt("idu"));
				product.setSoldout(rs.getInt("soldout"));
				product.setImage(rs.getString("image"));
				product.setRapido(rs.getString("rapido"));
				
				products.add(product);
				
				logger.info("fetching products by text either in the title or in the description: "+product.toString());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}
	
	
	@Override
	public List<Product> getAllByCategoryPrice(String idCategory, float price) {
		if (conn == null)
			return null;

		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product  WHERE UPPER(category) LIKE '%" + idCategory + "%' OR UPPER(price) LIKE '%" + price + "%'");

			while (rs.next()) {
				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setCurrency(rs.getString("currency"));
				product.setPrice(rs.getFloat("price"));
				product.setIdu(rs.getInt("idu"));
				product.setSoldout(rs.getInt("soldout"));
				product.setImage(rs.getString("image"));
				product.setRapido(rs.getString("rapido"));
				
				products.add(product);
				
				logger.info("fetching products by text in the description: "+product.toString());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	
	
	
	@Override
	public long add(Product product) {
		long id=-1;
		long lastid=-1;
		if (conn != null){

			Statement stmt;
			
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='product'");			 
				if (!rs.next()) return -1; 
				lastid=rs.getInt("seq");
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO product (title,description,category,stock,currency,price,idu,soldout,image,rapido) VALUES('"
									+product.getTitle()+"','"+product.getDescription()+"','"+product.getCategory()+"','" + product.getStock() +"','" + product.getCurrency() + "'," 
									+ product.getPrice() + "," + product.getIdu() +","+ product.getSoldout() +",'"+ product.getImage() +"','"+ product.getRapido() +"')");
				
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='product'");			 
				if (!rs.next()) return -1; 
				id=rs.getInt("seq");
				if (id<=lastid) return -1;
											
				logger.info("CREATING Product("+id+"): "+product.getTitle()+" "+product.getDescription());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return id;
	}

	@Override
	public boolean save(Product product) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("UPDATE product SET title='"+product.getTitle()
				+"', description='"+product.getDescription()
				+"', category='"+product.getCategory()
				+"', stock='"+product.getStock()
				+"', currency='"+product.getCurrency()
				+"', image='"+product.getImage()
				+"', rapido='"+product.getRapido()
				+"', price="+product.getPrice()
				+", idu="+product.getIdu()
				+", soldout="+product.getSoldout()
				+" WHERE id = "+product.getId());
				logger.info("updating Product: "+product.getId()+" "+product.getTitle()+" "+product.getDescription());
						
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;

	}

	@Override
	public boolean delete(long id) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM product WHERE id ="+id);
				logger.info("deleting Product: "+id);
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
		// Auto-generated method stub
		this.conn = conn;
	}



	
}
