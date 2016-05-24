package validacao.dados;

import java.util.regex.Pattern;

/**
 * Classe que valida nomes dos usuarios
 */
public class ValidaNome {

	private static final String pattern = "^[\\w][\\w. ]{1,49}$";

	/**
	 * Valida nome de usuario
	 * 
	 * @param nome
	 *            - nome a validar
	 * @return
	 */
	public static boolean validar(String nome) {
		return Pattern.matches(pattern, nome);
	}

}
