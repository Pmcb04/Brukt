package es.unex.pi.model;

import java.util.Map;

public class PasswordValidation {

	public PasswordValidation() {
	}
	
	public static boolean validatePassword (String password, Map<String,String> messages) {
		boolean error = false;
		if(password != null) {
		// Validate user name
			if(password.trim().isEmpty()) { // Trim removes spaces before and after the string
		    		messages.put("error", "Campo contraseña vacio");
		    		error = true;
		    	}
			else {
		        boolean mayuscula = false, minuscula = false, numero = false, alfanumerico=false, caracterEspecial = false;
	        	mayuscula=password.matches(".*[A-Z].*");
	        	minuscula=password.matches(".*[a-z].*");
	        	numero=password.matches(".*[0-9].*"); 
	        	caracterEspecial=password.matches(".*[#$%&!@=].*");
	        	
	        	int i = 0;
	        	
	        	while(!alfanumerico && i < password.length()) {
	        		alfanumerico = Character.isLetterOrDigit(password.charAt(i));
	        		i++;
	        	}
	        	
	        	
	        	if(!mayuscula) {
	        		messages.put("mayusculas", "La contraseña debe contener al menos una mayuscula");
	        		error = true;
	        	}
	        	
	        	if(!minuscula) {
	        		messages.put("minuscula", "La contraseña debe contener al menos una minuscula");
	        		error = true;
	        	}
	        	
	        	if(!numero) {
	        		messages.put("numero", "La contraseña debe contener al menos un numero");
	        		error = true;
	        	}
	        	
	        	if(!alfanumerico) {
	        		messages.put("numero", "La contraseña debe contener al menos un digito alfanumerico");
	        		error = true;
	        	}
	        	
	        	if(!caracterEspecial) {
	        		messages.put("caracterEspecial", "La contraseña debe contener al menos un caracterEspecial [#$%&!@=]");
	        		error = true;
	        	}
	        	
	        	if(password.length() < 8) {
	        		messages.put("password_length", "La contraseña debe tener al menos 8 caracteres");
	        		error = true;
	        	}
			}
		}
		return error;

	}

	public static boolean validatePassword (String password) {
		boolean error = false;
		if(password != null) {
		// Validate user name
			if(password.trim().isEmpty()) { // Trim removes spaces before and after the string
		    		error = true;
		    	}
			else {
				
		
	        boolean mayuscula = false, minuscula = false, numero = false, alfanumerico=false, caracterEspecial=false;
        	mayuscula=password.matches("*.[A-Z].*");
        	minuscula=password.matches("*.[a-z].*");
        	numero=password.matches("*.[0-9].*"); 
        	caracterEspecial=password.matches(".*[#$%&!@=].*");
        	
        	
        	int i = 0;
        	
        	while(!alfanumerico && i < password.length()) {
        		alfanumerico = Character.isLetterOrDigit(password.charAt(i));
        		i++;
        	}
        	
        	if(!mayuscula) 
        		error = true;
        	
        	
        	if(!minuscula) 
        		error = true;
        	
        	
        	if(!numero) 
        		error = true;
        	
        	if(!alfanumerico) 
        		error = true;
        	
        	if(!caracterEspecial) 
        		error = true;

        	
        	if(password.length() < 8) 
        		error = true;
        	
			}
		}
		return error;
	}
	
}
