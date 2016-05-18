package validacao.procedimentos;

import java.util.ArrayList;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.ProcedimentoInvalidoException;

public class ValidaProcedimento {
	
	public static void validaNomePaciente(String nomePaciente) throws DadoInvalidoException {
		if (nomePaciente == null || nomePaciente.trim().isEmpty()) {
			throw new DadoInvalidoException("ID do paciente nao pode ser vazio.");
		}
	}
	
	public static boolean validaProcedimentoSolicitado(String procedimento) throws ProcedimentoInvalidoException {
		
		ArrayList<String> arrayProcedimentos = new ArrayList<String>();
		
		arrayProcedimentos.add("Consulta clinica");
		arrayProcedimentos.add("Transplante de orgaos");
		arrayProcedimentos.add("Redesignacao sexual");
		arrayProcedimentos.add("Cirurgia bariatrica");
		
		for (String nomeProcedimento : arrayProcedimentos) {
			if (procedimento.contains(nomeProcedimento)) {
				return true;
			}
		}
		
		throw new ProcedimentoInvalidoException("Erro na realizacao de procedimentos. Procedimento invalido.");
	}
	
}
