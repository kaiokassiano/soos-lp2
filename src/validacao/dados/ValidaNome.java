package validacao.dados;

import java.util.regex.*;

public class ValidaNome {

	private static final String pattern = "^[\\w][\\w. ]{1,49}$";
	
	public static boolean validar(String nome){
		return Pattern.matches(pattern, nome);
	}

}
