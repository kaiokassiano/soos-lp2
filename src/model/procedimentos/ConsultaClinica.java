package model.procedimentos;

import model.prontuarios.Prontuario;

public class ConsultaClinica implements Procedimento{

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		prontuario.atualizaInfoPaciente("350.00", "gastos");
	}

}
