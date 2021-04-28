package es.unex.pi.model;

import java.util.Map;

public class ProductValidation {

	public ProductValidation() {
	}
	
	public static boolean validateProduct (Product product, Map<String,String> messages) {
		boolean error = false;

			if(product == null) {
				error = true;
			}
	        	
			if(product.getTitle().equals("")) {
				messages.put("name", "El nombre del producto no puede estar vacio");
				error = true;
			}
			
			if(product.getDescription().equals("")) {
				messages.put("description", "la descripcion del producto no puede estar vacio");
				error = true;
			}
			
			if(product.getStock() <= 0) {
				messages.put("stock", "El numero de producto vendidos no puede ser 0 o negativo");
				error = true;
			}
			
			if(product.getPrice() <= 0.0) {
				messages.put("price", "El precio del producto vendidos no puede ser 0 o negativo");
				error = true;
			}
			
			if(product.getCurrency().equals("")) {
				messages.put("currency", "La moneda no puede estar vacio");
				error = true;
			}
			
			if(product.getCategory().equals("")) {
				messages.put("name", "La categoria del producto no puede estar vacio");
				error = true;
			}
			
			
			if(!product.getRapido().equals("yes") && !product.getRapido().equals("no")) {
				messages.put("rapido", "Debe marcar si desea envio rápido o no");
				error = true;
			}
		
		
		return error;

	}

	public static boolean validateProduct (Product product) {
		boolean error = false;

		if(product == null) 
			error = true;
		
        	
		if(product.getTitle().equals("")) 
			error = true;
		
		
		if(product.getDescription().equals("")) 
			error = true;
		
		
		if(product.getStock() <= 0) 
			error = true;
		
		if(product.getPrice() <= 0) 
			error = true;
		
		if(product.getCurrency().equals("")) 
			error = true;
		
		
		if(product.getCategory().equals("")) 
			error = true;
		
		if(!product.getRapido().equals("yes") && !product.getRapido().equals("no")) 
			error = true;
		
	
	return error;
	}
	
}
