package model.procedimentos;

import model.prontuarios.Prontuario;

public class RedesignacaoSexual implements Procedimento{

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		
		Double gastosComDesconto = prontuario.aplicaDesconto(9300.0);
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.mudaGeneroPaciente();
		prontuario.atualizaInfoPaciente("130", "pontos");
	}

}
