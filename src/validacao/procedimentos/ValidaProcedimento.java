package validacao.procedimentos;

import exceptions.dado.DadoInvalidoException;

public class ValidaProcedimento {
	
	public static void validaProcedimento(String nomePaciente) throws DadoInvalidoException {
		if (nomePaciente == null || nomePaciente.trim().isEmpty()) {
			throw new DadoInvalidoException("Nome do paciente nao pode ser vazio.");
		}
	}
}
