package model.procedimentos;

import java.time.LocalDate;
import java.util.HashMap;

import model.prontuarios.Prontuario;

public class CirurgiaBariatrica extends Procedimento{

	private static final long serialVersionUID = -7903208135181946295L;

	@Override
	public void realizaProcedimento(Prontuario prontuario, HashMap<String, Object> param) {
		String pesoPaciente = prontuario.getInfoPaciente("peso");
		Double novoPeso = Double.parseDouble(pesoPaciente) * 0.90;
		
		Double gastosComDesconto = prontuario.aplicaDesconto(7600.0);
		
		super.setMedico((String) param.get("nomeMedico"));
		super.setDataProcedimento(LocalDate.now());
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.atualizaInfoPaciente(novoPeso.toString(), "peso");
		prontuario.atualizaInfoPaciente("100", "pontos");
	}

	@Override
	public String getTipoProcedimento() {
		return "Cirurgia Bariatrica";
	}
}
