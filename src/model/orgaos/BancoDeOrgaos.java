package model.orgaos;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.OrgaoInexistenteException;
import exceptions.logica.TipoSanguineoInexistenteException;
import exceptions.logica.TipoSanguineoInvalidoException;
import factory.orgaos.OrgaoFactory;
import validacao.orgao.ValidaOrgao;

public class BancoDeOrgaos implements Serializable {

	private static final long serialVersionUID = 4504834495360884617L;

	private ArrayList<Orgao> bancoDeOrgaos;
	private OrgaoFactory orgaoFactory;
	
	public BancoDeOrgaos() {
		bancoDeOrgaos = new ArrayList<Orgao>();
		orgaoFactory = new OrgaoFactory();
	}

	public String buscaOrgPorNome(String nome) throws DadoInvalidoException, OrgaoInexistenteException {

		ArrayList<String> tiposSanguineos = new ArrayList<String>();

		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {
				tiposSanguineos.add(orgao.getTipoSanguineo());
			}
		}
		
		if (tiposSanguineos.isEmpty()) {
			throw new OrgaoInexistenteException("Orgao nao cadastrado.");
		}
		return String.join(",", tiposSanguineos);
	}

	public int totalOrgaosDisponiveis() {
		return bancoDeOrgaos.size();
	}

	public boolean buscaOrgao(String nome, String tipoSanguineo) throws TipoSanguineoInvalidoException, DadoInvalidoException {

		Orgao outroOrgao = orgaoFactory.criaOrgao(nome, tipoSanguineo);
		
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.equals(outroOrgao)) {
				return true;
			}
		}
		return false;
	}

	public void cadastraOrgao(String nome, String tipoSanguineo) throws TipoSanguineoInvalidoException, DadoInvalidoException {
		Orgao orgao = orgaoFactory.criaOrgao(nome, tipoSanguineo);
		bancoDeOrgaos.add(orgao);
	}
	
	public String buscaOrgPorSangue(String tipoSanguineo) throws TipoSanguineoInvalidoException, DadoInvalidoException, TipoSanguineoInexistenteException {
		
		ValidaOrgao.validaTipoSanguineo(tipoSanguineo);
		ArrayList<String> orgaosTipoSanguineo = new ArrayList<String>();
		
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				
				if (!orgaosTipoSanguineo.contains(orgao.getNome())) {
					orgaosTipoSanguineo.add(orgao.getNome());
				}
				
			}
		}
		
		if (orgaosTipoSanguineo.isEmpty()) {
			throw new TipoSanguineoInexistenteException("Nao ha orgaos cadastrados para esse tipo sanguineo.");
		}
		
		return String.join(",", orgaosTipoSanguineo);
	}
	
	public boolean retiraOrgao (String nome, String tipoSanguineo) throws TipoSanguineoInvalidoException, DadoInvalidoException, OrgaoInexistenteException {
		if (!buscaOrgao(nome, tipoSanguineo)) {
			throw new OrgaoInexistenteException("Orgao nao cadastrado.");
		}
		Orgao orgao = orgaoFactory.criaOrgao(nome, tipoSanguineo);
		bancoDeOrgaos.remove(orgao);
		return true;
	}
	
	public int qtdOrgaos(String nome) throws OrgaoInexistenteException, DadoInvalidoException {
		int quantidade = 0;
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {
				quantidade++;
			}
		}
		
		if (quantidade == 0) {
			throw new OrgaoInexistenteException("Orgao nao cadastrado.");
		}
		return quantidade;
	}
	
}
