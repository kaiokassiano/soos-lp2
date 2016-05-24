package validacao.orgao;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.StringVaziaException;
import exceptions.logica.TipoSanguineoInvalidoException;

/**
 * Classe que valida os orgaos
 */
public class ValidaOrgao {

	/**
	 * Valida o nome do orgao
	 * 
	 * @param nome
	 *            - nome a validar
	 * @throws DadoInvalidoException
	 */
	public static void validaNomeOrgao(String nome) throws DadoInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new DadoInvalidoException("Nome do orgao nao pode ser vazio.");
		}
	}

	/**
	 * Valida o tipo sanguineo do orgao
	 * 
	 * @param tipoSanguineo
	 *            - tipo sanguineo a validar
	 * @throws StringVaziaException
	 * @throws TipoSanguineoInvalidoException
	 */
	public static void validaTipoSanguineo(String tipoSanguineo)
			throws StringVaziaException, TipoSanguineoInvalidoException {
		if (tipoSanguineo == null || tipoSanguineo.trim().isEmpty()) {
			throw new StringVaziaException("Tipo sanguineo nao pode ser vazio.");
		}

		String tiposSanguineos = "A+A-B+B-AB+AB-O+O-";

		if (!tiposSanguineos.contains(tipoSanguineo)) {
			throw new TipoSanguineoInvalidoException("Tipo sanguineo invalido.");
		}
	}

}
