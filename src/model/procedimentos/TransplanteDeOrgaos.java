package model.procedimentos;

import java.util.HashMap;

import model.prontuarios.Prontuario;

public class TransplanteDeOrgaos extends Procedimento{

	private static final long serialVersionUID = -1968367212003155005L;
	
	private String orgaoTransplantado;

	@Override
	public void realizaProcedimento(Prontuario prontuario, double precoMedicamentos, HashMap<String, Object> param) {
		double gastos = prontuario.aplicaDesconto(12500.0 + precoMedicamentos);
		
		this.orgaoTransplantado = (String) param.get("orgao");
		setMedico((String) param.get("nomeMedico"));

		prontuario.adicionaGastos(gastos);
		prontuario.adicionaPontosFidelidade(160);
	}

	@Override
	public String toString() {
		return super.toString() + System.lineSeparator() + "....... Orgao transplantado: " + orgaoTransplantado;
	}

	@Override
	public String getTipoProcedimento() {
		return "Transplante de Orgaos";
	}
}
