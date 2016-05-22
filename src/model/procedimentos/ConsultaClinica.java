package model.procedimentos;

import java.time.LocalDate;
import java.util.HashMap;

import model.prontuarios.Prontuario;

public class ConsultaClinica extends Procedimento{

	private static final long serialVersionUID = 8517961562576475774L;

	@Override
	public void realizaProcedimento(Prontuario prontuario, HashMap<String, Object> param) {
		Double valorAdicionado = prontuario.aplicaDesconto(350.0);
		
		super.setMedico((String) param.get("nomeMedico"));
		super.setDataProcedimento(LocalDate.now());
		
		prontuario.atualizaInfoPaciente(valorAdicionado.toString(), "gastos");
		prontuario.atualizaInfoPaciente("50", "pontos");
	}
}
