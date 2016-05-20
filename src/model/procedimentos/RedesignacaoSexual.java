package model.procedimentos;

import model.prontuarios.Prontuario;

public class RedesignacaoSexual implements Procedimento{

	private static final long serialVersionUID = 5812370610557995210L;

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		
		Double gastosComDesconto = prontuario.aplicaDesconto(9300.0);
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.mudaGeneroPaciente();
		prontuario.atualizaInfoPaciente("130", "pontos");
	}

}
