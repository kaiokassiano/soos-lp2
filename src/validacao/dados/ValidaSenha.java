package validacao.dados;

import java.util.regex.*;

public class ValidaSenha {
	
	private static final String pattern = "^[\\w\\d]{8,12}$";
	
	public static boolean validar(String senha){
		return Pattern.matches(pattern, senha);
	}
}
