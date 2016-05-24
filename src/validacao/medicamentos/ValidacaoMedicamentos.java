package validacao.medicamentos;

import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.ObjetoInexistenteException;
import exceptions.logica.StringVaziaException;
import model.farmacia.Medicamento;

/**
 * Classe que valida um medicamento
 */
public class ValidacaoMedicamentos {

	/**
	 * Valida parametros do medicamento
	 * 
	 * @param nome
	 *            - nome do medicamento
	 * @param tipo
	 *            - tipo do medicamento
	 * @param preco
	 *            - preco do medicamento
	 * @param quantidade
	 *            - unidades do medicamento
	 * @return Boolean se a validacao ocorreu
	 * @throws NullStringException
	 * @throws LogicaException
	 */
	public static boolean validaDadosMedicamento(String nome, String tipo, double preco, int quantidade)
			throws NullStringException, LogicaException {
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo");

		} else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome do medicamento nao pode ser vazio.");

		} else if (tipo == null) {
			throw new NullStringException("Nome nao pode ser nulo");

		} else if (tipo.trim().isEmpty()) {
			throw new StringVaziaException("Tipo do medicamento nao pode ser vazio.");

		} else if (!tipo.equals("generico") && !tipo.equals("referencia")) {
			throw new LogicaException("Tipo de medicamento invalido");

		} else if (preco < 0.0) {
			throw new NumeroNegativoException("Preco do medicamento nao pode ser negativo.");

		} else if (quantidade < 0) {
			throw new NumeroNegativoException("Quantidade do medicamento nao pode ser negativo.");
		}

		return true;
	}

	/**
	 * Valida categorias do medicamento
	 * 
	 * @param categorias
	 *            - categorias do medicamento
	 * @return Boolean se a validacao ocorreu
	 * @throws NullStringException
	 */
	public static boolean validaCategoriasMedicamento(String[] categorias) throws NullStringException {

		for (int i = 0; i < categorias.length; i++) {
			if (categorias[i] == null) {
				throw new NullStringException("Categoria invalida");
			}
		}

		return true;
	}

	/**
	 * Valida a existencia de um medicamento
	 * 
	 * @param medicamento
	 *            - medicamento a validar
	 * @return Boolean se a validacao ocorreu
	 * @throws ObjetoInexistenteException
	 */
	public static boolean validaObjetoMedicamento(Medicamento medicamento) throws ObjetoInexistenteException {
		if (medicamento == null) {
			throw new ObjetoInexistenteException("Medicamento nao cadastrado.");
		}
		return true;
	}

	/**
	 * Valida apenas o nome de um medicamento
	 * 
	 * @param nomeMedicamento
	 *            - nome a validar
	 * @return Boolean se a validacao ocorreu
	 * @throws NullStringException
	 */
	public static boolean validaNomeMedicamento(String nomeMedicamento) throws NullStringException {
		if (nomeMedicamento == null || nomeMedicamento.trim().isEmpty()) {
			throw new NullStringException("Nome do medicamento nao pode ser vazio.");
		}
		return true;
	}

}
