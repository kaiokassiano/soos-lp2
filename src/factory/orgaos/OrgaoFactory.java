package factory.orgaos;

import java.io.Serializable;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.TipoSanguineoInvalidoException;
import model.orgaos.Orgao;
import validacao.orgao.ValidaOrgao;

public class OrgaoFactory implements Serializable {

	private static final long serialVersionUID = 6153827751630715475L;

	public Orgao criaOrgao(String nome, String tipoSanguineo) throws DadoInvalidoException, TipoSanguineoInvalidoException {
		ValidaOrgao.validaNomeOrgao(nome);
		ValidaOrgao.validaTipoSanguineo(tipoSanguineo);
		
		return new Orgao(nome, tipoSanguineo);
	}

}
