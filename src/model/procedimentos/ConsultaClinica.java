package model.procedimentos;

import model.prontuarios.Prontuario;

public class ConsultaClinica implements Procedimento{

	private static final long serialVersionUID = 8517961562576475774L;

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		Double valorAdicionado = prontuario.aplicaDesconto(350.0);
		
		prontuario.atualizaInfoPaciente(valorAdicionado.toString(), "gastos");
		prontuario.atualizaInfoPaciente("50", "pontos");
	}

}
