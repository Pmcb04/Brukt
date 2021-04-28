package es.unex.pi.model;

import java.util.Map;

public class NameValidation {

	public NameValidation() {
	}
	
	public static boolean validateName (String name, Map<String,String> messages) {
		boolean error = false;
		if(name != null) {
		// Validate user name
			if(name.trim().isEmpty()) { // Trim removes spaces before and after the string
		    		messages.put("error", "Campo nombre vacio");
		    		error = true;
		    	}
		    else if(!name.trim().matches("[A-Za-zÃ¡Ã©Ã­Ã³ÃºÃ±Ã�Ã‰Ã�Ã“Ãš]{2,}([\\s][A-Za-zÃ¡Ã©Ã­Ã³ÃºÃ±Ã�Ã‰Ã�Ã“Ãš]{2,})*")) { //one or more words with a minimum length of 2 and separated by just one space
		    		messages.put("error", "Nombre invalido :" + name.trim());
		    		error = true;
		    }  
		}
		return error;

	}

	public static boolean validateName (String name) {
		boolean error = false;
		if(name != null) {
		// Validate user name
			if(name.trim().isEmpty()) { // Trim removes spaces before and after the string
		    		error = true;
		    	}
		    else if(!name.trim().matches("[A-Za-zÃ¡Ã©Ã­Ã³ÃºÃ±Ã�Ã‰Ã�Ã“Ãš]{2,}([\\s][A-Za-zÃ¡Ã©Ã­Ã³ÃºÃ±Ã�Ã‰Ã�Ã“Ãš]{2,})*")) { //one or more words with a minimum length of 2 and separated by just one space
		    		error = true;
		    }  
		}
		return error;
	}
	
}
