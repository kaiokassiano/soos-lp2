package validacao.prontuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.DataInvalidaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.StringVaziaException;

/**
 * Classe que valida os prontuarios
 */
public class ValidacaoProntuarios {

	/**
	 * Valida um prontuario
	 * 
	 * @param nome
	 *            - nome do paciente
	 * @param dataNascimento
	 *            - data de nascimento do paciente de acordo com o formato
	 *            dd/MM/yyy
	 * @param peso
	 *            - peso do paciente
	 * @param tipoSanguineo
	 *            - tipo sanguineo do paciente
	 * @return Boolean se a validacao ocorreu
	 * @throws StringVaziaException
	 * @throws NumeroNegativoException
	 * @throws DataInvalidaException
	 * @throws DadoInvalidoException
	 */
	public static boolean validaDadosProntuario(String nome, String dataNascimento, double peso, String tipoSanguineo)
			throws StringVaziaException, NumeroNegativoException, DataInvalidaException, DadoInvalidoException {

		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo");
		}

		else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome do paciente nao pode ser vazio.");

		} else if (peso < 0.0) {
			throw new NumeroNegativoException("Peso do paciente nao pode ser negativo.");

		} else if (!validaTipoSanguineo(tipoSanguineo)) {
			throw new DadoInvalidoException("Tipo sanguineo invalido.");

		}
		try {
			parseData(dataNascimento);
		} catch (Exception e) {
			throw new DataInvalidaException(e.getMessage());

		}
		return true;
	}

	/**
	 * Valida apenas o tipo sanguineo do paciente
	 * 
	 * @param tipoSanguineo
	 *            - tipo sanguineo a validar
	 * @return Boolean se a validacao ocorreu
	 */
	private static boolean validaTipoSanguineo(String tipoSanguineo) {
		String[] tiposSanguineos = new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };
		for (int i = 0; i < tiposSanguineos.length; i++) {
			if (tipoSanguineo.toUpperCase().equals(tiposSanguineos[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Valida uma data de nascimento do paciente
	 * 
	 * @param dataNascimento
	 *            - data de nascimento a validar
	 * @return Data analisada
	 * @throws DataInvalidaException
	 */
	private static LocalDate parseData(String dataNascimento) throws DataInvalidaException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			return LocalDate.parse(dataNascimento, formato);
		} catch (Exception e) {
			throw new DataInvalidaException("Data invalida.");
		}
	}

}
