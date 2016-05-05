package valida.dados;

import java.util.regex.*;

public class ValidaMatricula {
	
	public boolean validar(String matricula){
		String pattern = "^[1-3]\\d{7}$";
		
		return Pattern.matches(pattern, matricula);
	}
}
