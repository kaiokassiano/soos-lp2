package model.procedimentos;

import java.time.LocalDate;
import java.util.HashMap;

import model.prontuarios.Prontuario;

public class TransplanteDeOrgaos extends Procedimento{

	private static final long serialVersionUID = -1968367212003155005L;
	
	private String orgaoTransplantado;

	@Override
	public void realizaProcedimento(Prontuario prontuario, HashMap<String, Object> param) {
		Double gastosComDesconto = prontuario.aplicaDesconto(12500.0);
		
		orgaoTransplantado = (String) param.get("orgao");
		super.setMedico((String) param.get("nomeMedico"));
		super.setDataProcedimento(LocalDate.now());
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.atualizaInfoPaciente("160", "pontos");
	}

	@Override
	public String toString() {
		return super.toString() + "\n....... Orgao transplantado: " + orgaoTransplantado;
	}
}
