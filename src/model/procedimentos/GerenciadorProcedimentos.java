package model.procedimentos;

import model.prontuarios.Prontuario;

public class GerenciadorProcedimentos {
	
	private Procedimento procedimento;

	public void realizaProcedimento(Prontuario prontuario, String procedimentoSolicitado) {
		
		switch (procedimentoSolicitado) {
			case "Consulta clinica":
				procedimento = new ConsultaClinica();
				break;
			case "Transplante de orgaos":
				procedimento = new TransplanteDeOrgaos();
				break;
			case "Redesignacao sexual":
				procedimento = new RedesignacaoSexual();
				break;
			case "Cirurgia bariatrica":
				procedimento = new CirurgiaBariatrica();
				break;
		}
		procedimento.realizaProcedimento(prontuario);
		
	}
	
}
