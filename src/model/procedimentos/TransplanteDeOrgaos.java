package model.procedimentos;

import model.prontuarios.Prontuario;

public class TransplanteDeOrgaos implements Procedimento{

	private static final long serialVersionUID = -1968367212003155005L;

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		
		Double gastosComDesconto = prontuario.aplicaDesconto(12500.0);
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.atualizaInfoPaciente("160", "pontos");
	}
}
