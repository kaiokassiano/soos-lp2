package model.procedimentos;

import java.util.HashMap;

import model.prontuarios.Prontuario;

public class RedesignacaoSexual extends Procedimento{

	private static final long serialVersionUID = 5812370610557995210L;

	@Override
	public void realizaProcedimento(Prontuario prontuario, HashMap<String, Object> param) {
		prontuario.mudaGeneroPaciente();
		
		Double gastosComDesconto = prontuario.aplicaDesconto(9300.0);
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.atualizaInfoPaciente("130", "pontos");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
