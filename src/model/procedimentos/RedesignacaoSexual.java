package model.procedimentos;

import model.prontuarios.Prontuario;

public class RedesignacaoSexual implements Procedimento{

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		prontuario.atualizaInfoPaciente("9300.00", "gastos");
		prontuario.atualizaInfoPaciente("", "genero");
	}

	

}
