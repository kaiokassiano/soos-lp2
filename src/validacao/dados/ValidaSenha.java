package validacao.dados;

import java.util.regex.Pattern;

/**
 * Classe que valida senhas dos usuarios
 */
public class ValidaSenha {

	private static final String pattern = "^[\\w\\d]{8,12}$";

	/**
	 * Valida senha de usuario
	 * 
	 * @param senha
	 *            - senha a validar
	 * @return
	 */
	public static boolean validar(String senha) {
		return Pattern.matches(pattern, senha);
	}
}
