package validacao.dados;

import java.util.regex.Pattern;

/**
 * Classe que valida matriculas dos usuarios
 */
public class ValidaMatricula {

	private static final String pattern = "^\\d+$";

	/**
	 * Valida uma matricula
	 * 
	 * @param matricula
	 *            - matricula a validar
	 * @return
	 */
	public static boolean validar(String matricula) {
		return Pattern.matches(pattern, matricula);
	}
}
