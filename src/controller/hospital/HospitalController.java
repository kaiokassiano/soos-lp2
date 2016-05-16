package controller.hospital;

import java.io.Serializable;

import banco.dados.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.CategoriaInexistenteException;
import exceptions.logica.CategoriaInvalidaException;
import exceptions.logica.ComparacaoInvalidaException;
import exceptions.logica.LogicaException;
import exceptions.logica.LogoutException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.ObjetoInexistenteException;
import exceptions.logica.OperacaoInvalidaException;
import exceptions.logica.OrgaoInexistenteException;
import exceptions.logica.TipoSanguineoInvalidoException;
import model.farmacia.Farmacia;
import model.farmacia.Medicamento;
import model.orgaos.BancoDeOrgaos;
import model.prontuarios.GerenciadorProntuario;
import model.usuarios.GerenciadorFuncionarios;

/**
 * Controller principal da aplicação, faz o gerenciamento de todas as áreas e
 * assim como o do sistema
 */
public class HospitalController implements Serializable {

	private static final long serialVersionUID = 2263371848242193064L;
	
	private GerenciadorFuncionarios gerenciadorFuncionarios;
	private Farmacia farmacia;
	
	private GerenciadorProntuario gerenciadorProntuarios;
	private BancoDeOrgaos bancoDeOrgaos;

	public HospitalController() {
		gerenciadorFuncionarios = new GerenciadorFuncionarios();
		farmacia = new Farmacia();

		gerenciadorProntuarios = new GerenciadorProntuario();
		bancoDeOrgaos = new BancoDeOrgaos();
	}

	public void iniciaController() {
	}

	/**
	 * Fecha o sistema, junto com o banco de dados, verificando se alguém ainda
	 * está logado e, caso esteja, joga um erro
	 */
	public void fechaController() throws LogicaException {
		gerenciadorFuncionarios.fecharGerenciadorFuncionarios();
	}

	public String liberaSistema(String chave, String nome, String dataNascimento)
			throws LogicaException, DadoInvalidoException {
		return gerenciadorFuncionarios.liberaSistema(chave, nome, dataNascimento);
	}

	public void login(String matricula, String senha) throws NullStringException, LogicaException {
		gerenciadorFuncionarios.login(matricula, senha);
	}

	public void logout() throws LogoutException {
		gerenciadorFuncionarios.logout();
	}

	public String getInfoFuncionario(String matricula, String atributo) throws NullStringException, LogicaException {
		return gerenciadorFuncionarios.getInfoFuncionario(matricula, atributo);
	}

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento)
			throws LogicaException, DadoInvalidoException {
		return gerenciadorFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	public void excluiFuncionario(String matricula, String senha) throws DadoInvalidoException, LogicaException {
		gerenciadorFuncionarios.excluiFuncionario(matricula, senha);
	}

	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor)
			throws DadoInvalidoException, LogicaException {
		gerenciadorFuncionarios.atualizaInfoFuncionario(matricula, atributo, novoValor);
	}

	public void atualizaInfoFuncionario(String atributo, String novoValor)
			throws DadoInvalidoException, LogicaException {
		gerenciadorFuncionarios.atualizaInfoFuncionario(atributo, novoValor);
	}

	public void atualizaSenha(String antigaSenha, String novaSenha) throws DadoInvalidoException, LogicaException {
		gerenciadorFuncionarios.atualizaSenha(antigaSenha, novaSenha);
	}

	public Medicamento getMedicamentoPeloNome(String nome) throws ObjetoInexistenteException {
		return farmacia.getMedicamentoPeloNome(nome);
	}

	public String getMedicamentosPelaCategoria(String categoria)
			throws CategoriaInexistenteException, CategoriaInvalidaException {
		return farmacia.getMedicamentosPelaCategoria(categoria);
	}

	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws DadoInvalidoException, LogicaException {
		return farmacia.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	public String getInfoMedicamento(String requisicao, String nome) throws ObjetoInexistenteException {
		return farmacia.getInfoMedicamento(requisicao, nome);
	}

	public void atualizaMedicamento(String nome, String atributo, String novoValor)
			throws ObjetoInexistenteException, OperacaoInvalidaException {
		farmacia.atualizaMedicamento(nome, atributo, novoValor);
	}

	public String consultaMedCategoria(String categoria) throws LogicaException {
		try {
			return farmacia.getMedicamentosPelaCategoria(categoria);
		} catch (CategoriaInexistenteException | CategoriaInvalidaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}

	public String consultaMedNome(String nome) throws ObjetoInexistenteException {
		return farmacia.consultaMedNome(nome);
	}

	public String getEstoqueFarmacia(String tipoOrdenacao) throws ComparacaoInvalidaException {

		return farmacia.getEstoqueFarmacia(tipoOrdenacao);
	}

	public String cadastraPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws LogicaException, DadoInvalidoException {
		return gerenciadorProntuarios.cadastraPaciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
	}

	public String getInfoPaciente(String nome, String atributo) {
		return gerenciadorProntuarios.getInfoPaciente(nome, atributo);
	}

	public String getProntuario(int posicao) throws NumeroNegativoException, DadoInvalidoException {
		return gerenciadorProntuarios.getProntuario(posicao);
	}

	public void cadastraOrgao(String nome, String tipoSanguineo) throws LogicaException {
		try {
			bancoDeOrgaos.cadastraOrgao(nome, tipoSanguineo);
		} catch (TipoSanguineoInvalidoException | DadoInvalidoException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}
	
	public String buscaOrgPorSangue(String tipoSanguineo) throws LogicaException, DadoInvalidoException {
		return bancoDeOrgaos.buscaOrgPorSangue(tipoSanguineo);
	}
	
	public String buscaOrgPorNome(String nome) throws DadoInvalidoException, OrgaoInexistenteException {
		return bancoDeOrgaos.buscaOrgPorNome(nome);
	}
	
	public boolean buscaOrgao(String nome, String tipoSanguineo) throws TipoSanguineoInvalidoException, DadoInvalidoException {
		return bancoDeOrgaos.buscaOrgao(nome, tipoSanguineo);
	}
	
	public boolean retiraOrgao(String nome, String tipoSanguineo) throws TipoSanguineoInvalidoException, OrgaoInexistenteException, DadoInvalidoException {
		return bancoDeOrgaos.retiraOrgao(nome, tipoSanguineo);
	}
	
	public int qtdOrgaos(String nome) throws OrgaoInexistenteException, DadoInvalidoException {
		return bancoDeOrgaos.qtdOrgaos(nome);
	}
	
	public int totalOrgaosDisponiveis() {
		return bancoDeOrgaos.totalOrgaosDisponiveis();
	}
	
}
