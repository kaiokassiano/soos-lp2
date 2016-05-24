package factory.orgaos;

import java.io.Serializable;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import model.orgaos.Orgao;
import validacao.orgao.ValidaOrgao;

/**
 * Factory de orgaos
 */
public class OrgaoFactory implements Serializable {

	private static final long serialVersionUID = 6153827751630715475L;

	/**
	 * Cria um orgao de acordo com as informacoes recebidas
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao
	 * @return Instancia do orgao criado
	 * @throws DadoInvalidoException
	 * @throws LogicaException
	 */
	public Orgao criaOrgao(String nome, String tipoSanguineo) throws DadoInvalidoException, LogicaException {
		ValidaOrgao.validaNomeOrgao(nome);
		ValidaOrgao.validaTipoSanguineo(tipoSanguineo);

		Orgao orgao = new Orgao(nome, tipoSanguineo);

		return orgao;
	}

}
