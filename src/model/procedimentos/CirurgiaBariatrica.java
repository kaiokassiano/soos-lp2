package model.procedimentos;

import model.prontuarios.Prontuario;

public class CirurgiaBariatrica implements Procedimento{
	
	final String PONTOS_FIDELIDADE = "100";

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		String pesoPaciente = prontuario.getInfoPaciente(prontuario.getNome(), "peso");
		Double novoPeso = Double.parseDouble(pesoPaciente) * 0.90;
		
		Double gastosComDesconto = prontuario.aplicaDesconto() * 7600.0;
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.atualizaInfoPaciente(novoPeso.toString(), "peso");
		prontuario.atualizaInfoPaciente(PONTOS_FIDELIDADE, "pontos");
	}

}
