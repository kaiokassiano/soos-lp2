package model.procedimentos;

import java.util.HashMap;

import model.prontuarios.Prontuario;

public class ConsultaClinica extends Procedimento{

	private static final long serialVersionUID = 8517961562576475774L;

	@Override
	public void realizaProcedimento(Prontuario prontuario, HashMap<String, Object> param) {
		Double valorAdicionado = prontuario.aplicaDesconto(350.0);
		
		prontuario.atualizaInfoPaciente(valorAdicionado.toString(), "gastos");
		prontuario.atualizaInfoPaciente("50", "pontos");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
