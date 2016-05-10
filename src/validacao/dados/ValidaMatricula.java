package validacao.dados;

import java.util.regex.*;

public class ValidaMatricula {
	
	private static final String pattern = "^[1-3]\\d{5,7}$";
	
	public static boolean validar(String matricula){	
		return Pattern.matches(pattern, matricula);
	}
}
