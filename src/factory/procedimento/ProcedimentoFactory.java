package factory.procedimento;

import java.io.Serializable;

import exceptions.logica.ProcedimentoInvalidoException;
import model.procedimentos.CirurgiaBariatrica;
import model.procedimentos.ConsultaClinica;
import model.procedimentos.Procedimento;
import model.procedimentos.RedesignacaoSexual;
import model.procedimentos.TransplanteDeOrgaos;

public class ProcedimentoFactory implements Serializable{

	private static final long serialVersionUID = 5129586256606014264L;

	public Procedimento criaProcedimento(String procedimentoSolicitado) throws ProcedimentoInvalidoException {

		switch (procedimentoSolicitado) {
		case "Consulta clinica":
			return new ConsultaClinica();
		case "Transplante de orgaos":
			return new TransplanteDeOrgaos();
		case "Redesignacao sexual":
			return new RedesignacaoSexual();
		case "Cirurgia bariatrica":
			return new CirurgiaBariatrica();
		}
		
		return null;
		
	}

}
