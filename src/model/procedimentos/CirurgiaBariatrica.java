package model.procedimentos;

import model.prontuarios.Prontuario;

public class CirurgiaBariatrica implements Procedimento{

	@Override
	public void realizaProcedimento(Prontuario prontuario) {
		String pesoPaciente = prontuario.getInfoPaciente(prontuario.getNome(), "peso");
		Double novoPeso = Double.parseDouble(pesoPaciente) * 0.90;
		
		prontuario.atualizaInfoPaciente("7600.00", "gastos");
		prontuario.atualizaInfoPaciente(novoPeso.toString(), "peso");
	}

}
