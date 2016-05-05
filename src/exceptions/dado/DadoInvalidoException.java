package exceptions.dado;

/**
 * Classe responsavel por lancar excessao de dados invalidos.
 */

public class DadoInvalidoException extends Exception {

	/**
	 * Mostra mensagem de excessao de dados invalidos.
	 * 
	 * @param message
	 */
	public DadoInvalidoException(String message) {
		super(message);
	}
}
