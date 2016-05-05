package exceptions.dado;

/**
 * Classe responsavel por lancar excessao de dados null.
 * 
 * @author Lucas Cordeiro
 * @version 1.0
 */

public class NullStringException extends DadoInvalidoException {

	/**
	 * Mostra mensagem de excessao de dados null.
	 * 
	 * @param message
	 */
	public NullStringException(String message) {
		super(message);
	}
}
