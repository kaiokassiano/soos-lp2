package model.orgaos;

import java.io.Serializable;
import java.util.ArrayList;

import banco.dados.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import exceptions.logica.OrgaoInexistenteException;
import exceptions.logica.PermissaoException;
import exceptions.logica.StringVaziaException;
import exceptions.logica.TipoSanguineoInexistenteException;
import exceptions.logica.TipoSanguineoInvalidoException;
import factory.orgaos.OrgaoFactory;
import model.usuarios.Funcionario;
import model.usuarios.PermissaoFuncionario;
import validacao.orgao.ValidaOrgao;

/**
 * Model que representa o banco de orgaos do sistema
 */
public class BancoDeOrgaos implements Serializable {

	private static final long serialVersionUID = 4504834495360884617L;

	private ArrayList<Orgao> bancoDeOrgaos;
	private OrgaoFactory orgaoFactory;

	/**
	 * Construtor do banco de orgaos
	 */
	public BancoDeOrgaos() {
		bancoDeOrgaos = new ArrayList<Orgao>();
		orgaoFactory = new OrgaoFactory();
	}

	/**
	 * Consulta todos os orgaos que tem o nome informado
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @return String contendo todos os orgaos que tem esse nome
	 * @throws DadoInvalidoException
	 * @throws OrgaoInexistenteException
	 */
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

	/**
	 * Consulta quantos orgaos existem no banco de orgaos
	 * 
	 * @return Inteiro quantidade de orgaos disponiveis
	 */
	public int totalOrgaosDisponiveis() {
		return bancoDeOrgaos.size();
	}

	/**
	 * Verifica a existencia de um orgao no banco de orgaos
	 * 
	 * @param nomeOrgao
	 *            - nome do orgao
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao
	 * @return Boolean se existe o orgao
	 * @throws TipoSanguineoInvalidoException
	 * @throws StringVaziaException
	 * @throws DadoInvalidoException
	 */
	public boolean verificaOrgao(String nomeOrgao, String tipoSanguineo)
			throws TipoSanguineoInvalidoException, StringVaziaException, DadoInvalidoException {
		ValidaOrgao.validaNomeOrgao(nomeOrgao);
		ValidaOrgao.validaTipoSanguineo(tipoSanguineo);

		for (int i = 0; i < bancoDeOrgaos.size(); i++) {
			Orgao orgao = bancoDeOrgaos.get(i);

			if (orgao.getNome().equals(nomeOrgao) && orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Retorna o índice do orgão na lista
	 * 
	 * @param nomeOrgao
	 *            Nome do orgão
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgão
	 * @return Índice do orgão na lista
	 * @throws OrgaoInexistenteException
	 *             Quando o orgão não está na lista
	 * @throws StringVaziaException
	 *             Quando o nome do orgão ou o tipo sanguineo forem vazios
	 * @throws TipoSanguineoInvalidoException
	 *             Quando o tipo sanguineo for invalido
	 */
	public int buscaOrgao(String nomeOrgao, String tipoSanguineo)
			throws OrgaoInexistenteException, StringVaziaException, TipoSanguineoInvalidoException {
		if (nomeOrgao.trim().isEmpty()) {
			throw new StringVaziaException("Nome do orgao nao pode ser vazio.");
		}

		ValidaOrgao.validaTipoSanguineo(tipoSanguineo);

		for (int i = 0; i < bancoDeOrgaos.size(); i++) {
			Orgao orgao = bancoDeOrgaos.get(i);

			if (orgao.getNome().equals(nomeOrgao) && orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				return i;
			}
		}

		throw new OrgaoInexistenteException("Orgao nao cadastrado.");
	}

	/**
	 * Cadastra um orgao no banco de orgaos
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao
	 * @throws DadoInvalidoException
	 * @throws LogicaException
	 */
	public void cadastraOrgao(String nome, String tipoSanguineo) throws DadoInvalidoException, LogicaException {
		Funcionario usuarioLogado = BancoDeDados.getInstance().getUsuarioLogado();
		if (!usuarioLogado.temPermissao(PermissaoFuncionario.CADASTRO_ORGAOS)) {
			throw new PermissaoException(
					"O funcionario " + usuarioLogado.getNome() + " nao tem permissao para cadastrar orgaos.");
		}
		Orgao orgao = orgaoFactory.criaOrgao(nome, tipoSanguineo);
		bancoDeOrgaos.add(orgao);
	}

	/**
	 * Consulta todos os orgaos que possuem o tipo sanguineo informado
	 * 
	 * @param tipoSanguineo
	 *            - tipo sanguineo desejado
	 * @return String contendo todos os orgaos desse tipo sanguineo
	 * @throws TipoSanguineoInvalidoException
	 * @throws StringVaziaException
	 * @throws TipoSanguineoInexistenteException
	 */
	public String buscaOrgPorSangue(String tipoSanguineo)
			throws TipoSanguineoInvalidoException, StringVaziaException, TipoSanguineoInexistenteException {
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

	/**
	 * Retira um orgao do banco de orgaos
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao
	 * @return Boolean se a retirada foi feita
	 * @throws DadoInvalidoException
	 * @throws LogicaException
	 */
	public boolean retiraOrgao(String nome, String tipoSanguineo) throws DadoInvalidoException, LogicaException {
		Orgao orgao = bancoDeOrgaos.get(buscaOrgao(nome, tipoSanguineo));

		bancoDeOrgaos.remove(orgao);
		return true;
	}

	/**
	 * Consulta a quantidade de orgaos de acordo com o nome
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @return String contendo todos os orgaos que tem esse nome
	 * @throws OrgaoInexistenteException
	 * @throws DadoInvalidoException
	 */
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
