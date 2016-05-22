package model.procedimentos;

import java.time.LocalDate;
import java.util.HashMap;

import model.prontuarios.Prontuario;

public class RedesignacaoSexual extends Procedimento{

	private static final long serialVersionUID = 5812370610557995210L;

	@Override
	public void realizaProcedimento(Prontuario prontuario, HashMap<String, Object> param) {
		prontuario.mudaGeneroPaciente();
		
		Double gastosComDesconto = prontuario.aplicaDesconto(9300.0);
		
		super.setMedico((String) param.get("nomeMedico"));
		super.setDataProcedimento(LocalDate.now());
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.atualizaInfoPaciente("130", "pontos");
	}
}
