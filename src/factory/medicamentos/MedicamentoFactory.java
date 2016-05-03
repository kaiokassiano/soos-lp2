package factory.medicamentos;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.StringVaziaException;
import model.farmacia.Medicamento;

public class MedicamentoFactory {

	public Medicamento criaMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws StringVaziaException, NumeroNegativoException, DadoInvalidoException {
		Medicamento medicamento = null;

		if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome nao pode ser vazio");
		} else if (preco < 0.0) {
			throw new NumeroNegativoException("Preco nao pode ser menor que 0");
		} else if (tipo.trim().isEmpty()) {
			throw new StringVaziaException("Tipo precisa ser definido");
		}

		medicamento = new Medicamento(nome, tipo, preco, quantidade, categorias);

		if (tipo.equals("generico")) {
			medicamento.defineDesconto();
		}
		
		return medicamento;

	}

}
