package model.procedimentos;

import java.util.HashMap;

import model.orgaos.Orgao;
import model.prontuarios.Prontuario;

public class TransplanteDeOrgaos extends Procedimento{

	private static final long serialVersionUID = -1968367212003155005L;
	
	private Orgao orgaoTransplantado;

	@Override
	public void realizaProcedimento(Prontuario prontuario, HashMap<String, Object> param) {
		Double gastosComDesconto = prontuario.aplicaDesconto(12500.0);
		
		orgaoTransplantado = (Orgao) param.get("orgao");
		
		prontuario.atualizaInfoPaciente(gastosComDesconto.toString(), "gastos");
		prontuario.atualizaInfoPaciente("160", "pontos");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
