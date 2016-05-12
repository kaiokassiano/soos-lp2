package validacao.dados;

import java.util.regex.*;

public class ValidaMatricula {
	
	private static final String pattern = "^\\d+$";
	
	public static boolean validar(String matricula){	
		return Pattern.matches(pattern, matricula);
	}
}
