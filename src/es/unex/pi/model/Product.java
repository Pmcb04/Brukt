package es.unex.pi.model;


public class Product {

	private long id;
	private String title;
	private String description;
	private String category;
	private int stock;
	private String currency;
	private float price;
	private long idu;
	private String image;
	private int soldout; //1 "true" //0 "false"
	private String rapido;
	
	public int getSoldout() {
		return soldout;
	}
	public void setSoldout(int soldout) {
		this.soldout = soldout;
	}
	

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public long getIdu() {
		return idu;
	}
	public void setIdu(long idu) {
		this.idu = idu;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRapido() {
		return rapido;
	}
	public void setRapido(String rapido) {
		this.rapido = rapido;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", stock=" + stock + ", currency=" + currency + ", price=" + price + ", idu=" + idu + ", image="
				+ image + ", soldout=" + soldout + ", rapido=" + rapido + "]";
	}

	
	

	
	
	
	
	
}
