package model.procedimentos;

import model.prontuarios.Prontuario;

public class TransplanteDeOrgaos implements Procedimento{

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		prontuario.atualizaInfoPaciente("12500.00", "gastos");
	}

}
