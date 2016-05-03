package factory.medicamentos;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import model.farmacia.Medicamento;

public class MedicamentoFactory {

	public Medicamento criaMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws DadoInvalidoException, LogicaException {

		Medicamento medicamento = new Medicamento(nome, tipo, preco, quantidade, categorias);

		return medicamento;

	}

}
