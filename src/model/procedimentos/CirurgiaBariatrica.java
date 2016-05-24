package model.procedimentos;

import java.util.HashMap;

import model.prontuarios.Prontuario;

public class CirurgiaBariatrica extends Procedimento{

	private static final long serialVersionUID = -7903208135181946295L;

	@Override
	public void realizaProcedimento(Prontuario prontuario, double precoMedicamentos, HashMap<String, Object> param) {
		double gastos = prontuario.aplicaDesconto(7600.0 + precoMedicamentos);
		
		setMedico((String) param.get("nomeMedico"));
		
		prontuario.adicionaGastos(gastos);
		prontuario.reduzPesoPaciente(0.10);
		prontuario.adicionaPontosFidelidade(100);
	}

	@Override
	public String getTipoProcedimento() {
		return "Cirurgia Bariatrica";
	}
}
