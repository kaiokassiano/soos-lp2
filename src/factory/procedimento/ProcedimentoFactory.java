package factory.procedimento;

import java.io.Serializable;

import exceptions.logica.ProcedimentoInvalidoException;
import model.procedimentos.CirurgiaBariatrica;
import model.procedimentos.ConsultaClinica;
import model.procedimentos.Procedimento;
import model.procedimentos.RedesignacaoSexual;
import model.procedimentos.TransplanteDeOrgaos;

/**
 * Factory de procedimentos
 */
public class ProcedimentoFactory implements Serializable {

	private static final long serialVersionUID = 5129586256606014264L;

	/**
	 * Cria um procedimento de acordo com o procedimento solicitado
	 * 
	 * @param procedimentoSolicitado
	 *            - nome do procedimento solicitado
	 * @return Instancia do procedimento criado
	 * @throws ProcedimentoInvalidoException
	 */
	public Procedimento criaProcedimento(String procedimentoSolicitado) throws ProcedimentoInvalidoException {
		Procedimento procedimento = null;

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

		return procedimento;
	}

}
