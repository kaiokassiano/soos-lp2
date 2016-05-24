package model.procedimentos;

import java.util.HashMap;

import model.prontuarios.Prontuario;

public class RedesignacaoSexual extends Procedimento{

	private static final long serialVersionUID = 5812370610557995210L;

	@Override
	public void realizaProcedimento(Prontuario prontuario, double precoMedicamentos, HashMap<String, Object> param) {
		prontuario.mudaGeneroPaciente();
		double gastos = prontuario.aplicaDesconto(9300.0);
		
		setMedico((String) param.get("nomeMedico"));
		
		prontuario.adicionaGastos(gastos + precoMedicamentos);
		prontuario.adicionaPontosFidelidade(130);
	}

	@Override
	public String getTipoProcedimento() {
		return "Redesignacao Sexual";
	}
}
