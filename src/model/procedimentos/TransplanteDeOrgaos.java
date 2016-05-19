package model.procedimentos;

import model.prontuarios.Prontuario;

public class TransplanteDeOrgaos implements Procedimento{
	
	final String PONTOS_FIDELIDADE = "160";
	final double VALOR_PROCEDIMENTO = 12500.0;

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		
		Double gastosComDesconto = prontuario.aplicaDesconto() * VALOR_PROCEDIMENTO;
		
		//Integer pontosComBonus = prontuario.adicionaPontosBonus() * 
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.atualizaInfoPaciente(PONTOS_FIDELIDADE, "pontos");
	}

}
