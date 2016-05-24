package controller.hospital;

import java.io.Serializable;
import java.util.HashMap;

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
import exceptions.logica.PacienteNaoCadastradoException;
import exceptions.logica.PermissaoException;
import exceptions.logica.ProcedimentoInvalidoException;
import exceptions.logica.TipoSanguineoInvalidoException;
import model.farmacia.Farmacia;
import model.farmacia.Medicamento;
import model.orgaos.BancoDeOrgaos;
import model.prontuarios.GerenciadorProntuario;
import model.usuarios.GerenciadorFuncionarios;
import validacao.medicamentos.ValidacaoMedicamentos;
import validacao.procedimentos.ValidaProcedimento;

/**
 * Controller principal da aplicação, faz o gerenciamento de todas as áreas e
 * assim como o do sistema
 */
public class HospitalController implements Serializable {

	private static final long serialVersionUID = 2263371848242193064L;

	private BancoDeOrgaos bancoDeOrgaos;
	private Farmacia farmacia;
	private GerenciadorFuncionarios gerenciadorFuncionarios;
	private GerenciadorProntuario gerenciadorProntuarios;

	public HospitalController() {
		gerenciadorFuncionarios = new GerenciadorFuncionarios();
		farmacia = new Farmacia();
		gerenciadorProntuarios = new GerenciadorProntuario();
		bancoDeOrgaos = new BancoDeOrgaos();
	}

	public void iniciaController() {
	}

	/**
	 * Fecha o sistema verificando se alguém ainda está logado e, caso esteja,
	 * joga um erro
	 */
	public void fechaController() throws LogicaException {
		try {
			gerenciadorFuncionarios.fecharGerenciadorFuncionarios();
		} catch (LogicaException e) {
			throw new LogicaException("Nao foi possivel fechar o sistema. " + e.getMessage());
		}
	}

	public String liberaSistema(String chave, String nome, String dataNascimento)
			throws LogicaException {
		try {
			return gerenciadorFuncionarios.liberaSistema(chave, nome, dataNascimento);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao liberar o sistema. " + e.getMessage());
		}
	}

	public void login(String matricula, String senha) throws LogicaException {
		try {
			gerenciadorFuncionarios.login(matricula, senha);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Nao foi possivel realizar o login. " + e.getMessage());
		}
	}

	public void logout() throws LogicaException {
		try {
			gerenciadorFuncionarios.logout();
		} catch (LogoutException e) {
			throw new LogicaException("Nao foi possivel realizar o logout. " + e.getMessage());
		}
	}

	public String getInfoFuncionario(String matricula, String atributo) throws LogicaException {
		try {
			return gerenciadorFuncionarios.getInfoFuncionario(matricula, atributo);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro na consulta de funcionario. " + e.getMessage());
		}
	}

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento)
			throws LogicaException {
		try {
		return gerenciadorFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro no cadastro de funcionario. " + e.getMessage());
		}
	}

	public void excluiFuncionario(String matricula, String senha) throws LogicaException {
		try {
		gerenciadorFuncionarios.excluiFuncionario(matricula, senha);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao excluir funcionario. " + e.getMessage());
		}
	}

	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor)
			throws LogicaException {
		try {
		gerenciadorFuncionarios.atualizaInfoFuncionario(matricula, atributo, novoValor);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}

	public void atualizaInfoFuncionario(String atributo, String novoValor)
			throws LogicaException {
		try {
		gerenciadorFuncionarios.atualizaInfoFuncionario(atributo, novoValor);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}

	public void atualizaSenha(String antigaSenha, String novaSenha) throws LogicaException {
		try {
		gerenciadorFuncionarios.atualizaSenha(antigaSenha, novaSenha);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}
	
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws LogicaException {
		try {
			return farmacia.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro no cadastro de medicamento. " + e.getMessage());
		}
	}
	
	public String getInfoMedicamento(String requisicao, String nome) throws LogicaException {
		try {
			return farmacia.getInfoMedicamento(requisicao, nome);
		} catch (LogicaException e) {
			throw new LogicaException("Erro no cadastro de medicamento. " + e.getMessage());
		}
	}

//	public Medicamento getMedicamentoPeloNome(String nome) throws LogicaException {
//		try {
//			return farmacia.getMedicamentoPeloNome(nome);
//		} catch (ObjetoInexistenteException e) {
//			throw new LogicaException("Erro ao procurar o medicamento. " + e.getMessage());
//		}
//	}

//	public String getMedicamentosPelaCategoria(String categoria)
//			throws LogicaException {
//		return farmacia.getMedicamentosPelaCategoria(categoria);
//	}

	public void atualizaMedicamento(String nome, String atributo, String novoValor)
			throws LogicaException {
		try {
			farmacia.atualizaMedicamento(nome, atributo, novoValor);
		} catch (LogicaException e) {
			throw new LogicaException("Erro ao atualizar medicamento. " + e.getMessage());
		}
	}

	public String consultaMedCategoria(String categoria) throws LogicaException {
		try {
			return farmacia.getMedicamentosPelaCategoria(categoria);
		} catch (CategoriaInexistenteException | CategoriaInvalidaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}

	public String consultaMedNome(String nome) throws LogicaException {
		try {
			return farmacia.consultaMedNome(nome);
		} catch (LogicaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}

	public String getEstoqueFarmacia(String tipoOrdenacao) throws LogicaException {
		try {
			return farmacia.getEstoqueFarmacia(tipoOrdenacao);
		} catch (LogicaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}

	public String cadastraPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws LogicaException {
		try {
			return gerenciadorProntuarios.cadastraPaciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Nao foi possivel cadastrar o paciente. " + e.getMessage());
		}
	}

	public String getInfoPaciente(String nome, String atributo) {
		return gerenciadorProntuarios.getInfoPaciente(nome, atributo);
	}

	public String getProntuario(int posicao) throws LogicaException {
		try {
			return gerenciadorProntuarios.getProntuario(posicao);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro ao consultar prontuario. " + e.getMessage());
		}
	}

	public void cadastraOrgao(String nome, String tipoSanguineo) throws LogicaException {
		try {
			bancoDeOrgaos.cadastraOrgao(nome, tipoSanguineo);
		} catch (TipoSanguineoInvalidoException | DadoInvalidoException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	public String buscaOrgPorSangue(String tipoSanguineo) throws LogicaException {
		try {
		return bancoDeOrgaos.buscaOrgPorSangue(tipoSanguineo);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	public String buscaOrgPorNome(String nome) throws LogicaException {
		try {
			return bancoDeOrgaos.buscaOrgPorNome(nome);
		} catch (DadoInvalidoException | OrgaoInexistenteException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	public boolean buscaOrgao(String nome, String tipoSanguineo)
			throws LogicaException {
		try {
			return bancoDeOrgaos.buscaOrgao(nome, tipoSanguineo);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	public boolean retiraOrgao(String nome, String tipoSanguineo)
			throws LogicaException {
		try {
			return bancoDeOrgaos.retiraOrgao(nome, tipoSanguineo);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro na retirada de orgaos. " + e.getMessage());
		}
	}

	public int qtdOrgaos(String nome) throws LogicaException {
		try {
			return bancoDeOrgaos.qtdOrgaos(nome);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	public int totalOrgaosDisponiveis() {
		return bancoDeOrgaos.totalOrgaosDisponiveis();
	}

	public String getPacienteID(String nome) {
		return gerenciadorProntuarios.getInfoPaciente(nome, "nome");
	}
	
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, String medicamentos) throws LogicaException {
		try {
			ValidaProcedimento.validaProcedimentoSolicitado(procedimentoSolicitado);
			ValidacaoMedicamentos.validaNomeMedicamento(medicamentos);
			ValidaProcedimento.validaNomePaciente(nomePaciente);
			
			existeMedicamento(medicamentos);
			
			HashMap<String, Object> params = new HashMap<>();
			
			params.put("nomeMedico", BancoDeDados.getInstance().getUsuarioLogado().getNome());
			
			gerenciadorProntuarios.realizaProcedimento(procedimentoSolicitado, nomePaciente, medicamentos, params);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}
	
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, String nomeOrgao, String medicamentos) throws LogicaException {
		try {
			ValidaProcedimento.validaProcedimentoSolicitado(procedimentoSolicitado);
			ValidaProcedimento.validaNomePaciente(nomePaciente);
			ValidacaoMedicamentos.validaNomeMedicamento(medicamentos);
			
			String tipoSanguineoPaciente = gerenciadorProntuarios.getInfoPaciente(nomePaciente, "tiposanguineo");
			
			existeMedicamento(medicamentos);
			existeOrgao(nomeOrgao, tipoSanguineoPaciente);

			HashMap<String, Object> params = new HashMap<>();
			
			params.put("nomeMedico", BancoDeDados.getInstance().getUsuarioLogado().getNome());
			params.put("orgao", nomeOrgao);

			gerenciadorProntuarios.realizaProcedimento(procedimentoSolicitado, nomePaciente, medicamentos, params);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}

	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente) throws LogicaException {
		try {
			ValidaProcedimento.validaProcedimentoSolicitado(procedimentoSolicitado);
			ValidaProcedimento.validaNomePaciente(nomePaciente);
			
			HashMap<String, Object> params = new HashMap<>();
			
			params.put("nomeMedico", BancoDeDados.getInstance().getUsuarioLogado().getNome());
			
			gerenciadorProntuarios.realizaProcedimento(procedimentoSolicitado, nomePaciente, params);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro na realizacao de procedimentos. " + e.getMessage());
		}	
	}

	public double calculaPrecoMedicamentos(String medicamentos) throws LogicaException {
		try {
			return farmacia.calculaPrecoMedicamentos(medicamentos);
		} catch (ObjetoInexistenteException e) {
			throw new LogicaException("Erro ao calcular preco de medicamentos. " + e.getMessage());
		}
	}

	public void existeMedicamento(String medicamentos) throws ObjetoInexistenteException {
		String[] arrayMedicamentos = medicamentos.split(",");
		for (String nomeMedicamento : arrayMedicamentos) {
			if (!farmacia.temMedicamento(nomeMedicamento)) {
				throw new ObjetoInexistenteException("Medicamento nao cadastrado.");
			}
		}
	}

	public boolean existeOrgao(String nome, String tipoSanguineo)
			throws OrgaoInexistenteException, DadoInvalidoException, TipoSanguineoInvalidoException {

		boolean temOrgao = bancoDeOrgaos.buscaOrgao(nome, tipoSanguineo);

		if (!temOrgao) {
			throw new OrgaoInexistenteException("Banco nao possui o orgao especificado.");
		}

		return temOrgao;
	}

	public int getTotalProcedimento(String nomePaciente) {
		return gerenciadorProntuarios.getTotalProcedimento(nomePaciente);
	}

	public int getPontosFidelidade(String nomePaciente) {
		return gerenciadorProntuarios.getPontosFidelidade(nomePaciente);
	}

	public String getGastosPaciente(String nomePaciente) {
		return gerenciadorProntuarios.getGastosPaciente(nomePaciente);
	}

	public void exportaFichaPaciente(String idPaciente) throws LogicaException {
		try {
			gerenciadorProntuarios.exportaFichaPaciente(idPaciente);
		} catch (PacienteNaoCadastradoException e) {
			throw new LogicaException("Erro ao exportar ficha do paciente. " + e.getMessage());
		}
	}
}
