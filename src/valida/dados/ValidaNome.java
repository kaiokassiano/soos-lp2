package valida.dados;

import java.util.regex.*;

public class ValidaNome {
	
	//valida nome
	public boolean validaNovoNome(String newNome){
		String pattern = "^([a-z A-z]){1,50}$";
		
		return Pattern.matches(pattern, newNome);
	}

}
