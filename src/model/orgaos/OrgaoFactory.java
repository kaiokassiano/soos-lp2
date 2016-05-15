package model.orgaos;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.TipoSanguineoInvalidoException;
import validacao.orgao.ValidaOrgao;

public class OrgaoFactory {

	public Orgao criaOrgao(String nome, String tipoSanguineo) throws DadoInvalidoException, TipoSanguineoInvalidoException {
		ValidaOrgao.validaNomeOrgao(nome);
		ValidaOrgao.validaTipoSanguineo(tipoSanguineo);
		
		return new Orgao(nome, tipoSanguineo);
	}

}
