package valida.dados;

import java.util.regex.*;

public class ValidaMatricula {
	
	public boolean validaMatricula(String matricula){
		String pattern = "^([1-3]{1}([0-9]){7})$";
		
		return Pattern.matches(pattern, matricula);
	}
}
