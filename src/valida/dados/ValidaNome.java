package valida.dados;

import java.util.regex.*;

public class ValidaNome {
	
	//valida nome
	public boolean validar(String nome){
		String pattern = "^([a-z A-z]){1,50}$";
		
		return Pattern.matches(pattern, nome);
	}

}
