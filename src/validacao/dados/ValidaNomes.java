package validacao.dados;

import java.util.regex.*;

public class ValidaNomes {

	private static final String pattern = "^[a-z A-z]{1,50}$";
	
	public static boolean validar(String nome){
		return Pattern.matches(pattern, nome);
	}

}
