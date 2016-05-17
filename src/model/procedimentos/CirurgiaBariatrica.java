package model.procedimentos;

import model.prontuarios.Prontuario;

public class CirurgiaBariatrica implements Procedimento{

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		Object pesoPaciente = prontuario.getInfoPaciente(prontuario.getNome(), "peso");
		prontuario.atualizaInfoPaciente("7600.00", "gastos");
		prontuario.atualizaInfoPaciente((String) pesoPaciente, "peso");
	}

}
