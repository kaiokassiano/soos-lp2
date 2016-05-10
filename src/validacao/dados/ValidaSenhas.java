package validacao.dados;

import java.util.regex.*;

public class ValidaSenhas {
	
	private static final String pattern = "^([a-zA-Z0-9]){8,12}$";
	
	public static boolean validar(String senha){
		return Pattern.matches(pattern, senha);
	}
}
