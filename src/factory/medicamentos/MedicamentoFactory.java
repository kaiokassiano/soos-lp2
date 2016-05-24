package factory.medicamentos;

import java.io.Serializable;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import model.farmacia.Medicamento;

/**
 * Factory de medicamentos
 */
public class MedicamentoFactory implements Serializable {

	private static final long serialVersionUID = 7213019545854411262L;

	/**
	 * Cria um medicamento de acordo com as informacoes recebidas
	 * 
	 * @param nome
	 *            - nome do medicamento
	 * @param tipo
	 *            - tipo do medicamento
	 * @param preco
	 *            - preco do medicamento
	 * @param quantidade
	 *            - unidades do medicamento
	 * @param categorias
	 *            - categorias do medicamento
	 * @return Instancia do medicamento criado
	 * @throws DadoInvalidoException
	 * @throws LogicaException
	 */
	public Medicamento criaMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws DadoInvalidoException, LogicaException {

		Medicamento medicamento = new Medicamento(nome, tipo, preco, quantidade, categorias);

		return medicamento;

	}
}
