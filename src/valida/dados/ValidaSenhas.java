package valida.dados;

import java.util.regex.*;

public class ValidaSenhas {
	
	//valida senha
	public boolean validar(String senha){
		String pattern = "^([a-zA-Z0-9]){8,12}$";
		
		return Pattern.matches(pattern, senha);
	}
}
