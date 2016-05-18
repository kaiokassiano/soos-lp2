package validacao.medicamentos;

import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.ObjetoInexistenteException;
import exceptions.logica.StringVaziaException;
import model.farmacia.Medicamento;

public class ValidacaoMedicamentos {

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
			throw new NumeroNegativoException(
					"Preco do medicamento nao pode ser negativo.");
		
		} else if (quantidade < 0) {
			throw new NumeroNegativoException(
					"Quantidade do medicamento nao pode ser negativo.");
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
	
	public static boolean validaObjetoMedicamento(Medicamento medicamento) throws ObjetoInexistenteException {
		if (medicamento == null) {
			throw new ObjetoInexistenteException("Medicamento nao cadastrado.");
		}
		return true;
	}
	
	public static boolean validaNomeMedicamento(String nomeMedicamento) throws NullStringException {
		if (nomeMedicamento == null || nomeMedicamento.trim().isEmpty()) {
			throw new NullStringException("Nome do medicamento nao pode ser vazio.");
		}
		return true;
	}

}
