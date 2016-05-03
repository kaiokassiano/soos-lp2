package validacao.medicamentos;

import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.StringVaziaException;

public class ValidacaoMedicamentos {

	public static boolean validaDadosMedicamento(String nome, String tipo, double preco, int quantidade)
			throws NullStringException, LogicaException {
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo");
		
		} else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Erro no cadastro de medicamento. Nome do medicamento nao pode ser vazio.");
		
		} else if (tipo == null) {
			throw new NullStringException("Nome nao pode ser nulo");
		
		} else if (tipo.trim().isEmpty()) {
			throw new StringVaziaException("Erro no cadastro de medicamento. Tipo do medicamento nao pode ser vazio.");
		
		} else if (!tipo.equals("generico") && tipo.equals("referencia")) {
			throw new LogicaException("Erro no cadastro de medicamento. Tipo de medicamento invalido");
		}
		
		else if (preco < 0.0) {
			throw new NumeroNegativoException(
					"Erro no cadastro de medicamento. Preco do medicamento nao pode ser negativo.");
		
		} else if (quantidade < 0) {
			throw new NumeroNegativoException(
					"Erro no cadastro de medicamento. Quantidade do medicamento nao pode ser negativo.");
		}

		return true;
	}

	public static boolean validaCategoriasMedicamento(String[] categorias) throws NullStringException {

		for (int i = 0; i < categorias.length; i++) {
			if (categorias[i] == null) {
				throw new NullStringException("Categoria invalida");
			}
		}

		return true;
	}

}
