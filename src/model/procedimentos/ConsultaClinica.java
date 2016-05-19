package model.procedimentos;

import model.prontuarios.Prontuario;

public class ConsultaClinica implements Procedimento{
	
	final String PONTOS_FIDELIDADE = "50";
	final double VALOR_PROCEDIMENTO = 350.00;
	

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		Double valorAdicionado = VALOR_PROCEDIMENTO * prontuario.getDesconto();
		prontuario.atualizaInfoPaciente(valorAdicionado.toString(), "gastos");
		prontuario.atualizaInfoPaciente(PONTOS_FIDELIDADE, "pontos");
	}

}
