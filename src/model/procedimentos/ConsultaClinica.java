package model.procedimentos;

import java.util.HashMap;

import model.prontuarios.Prontuario;

public class ConsultaClinica extends Procedimento{

	private static final long serialVersionUID = 8517961562576475774L;

	@Override
	public void realizaProcedimento(Prontuario prontuario, double precoMedicamentos, HashMap<String, Object> param) {
		double gastos = prontuario.aplicaDesconto(350.0);
		
		setMedico((String) param.get("nomeMedico"));
		
		prontuario.adicionaGastos(gastos + precoMedicamentos);
		prontuario.adicionaPontosFidelidade(50);
	}

	@Override
	public String getTipoProcedimento() {
		return "Consulta Clinica";
	}
}
