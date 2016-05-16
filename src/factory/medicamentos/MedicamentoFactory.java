package factory.medicamentos;

import java.io.Serializable;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import model.farmacia.Medicamento;

public class MedicamentoFactory implements Serializable {

	private static final long serialVersionUID = 7213019545854411262L;

	public Medicamento criaMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws DadoInvalidoException, LogicaException {

		Medicamento medicamento = new Medicamento(nome, tipo, preco, quantidade, categorias);

		return medicamento;

	}
}
