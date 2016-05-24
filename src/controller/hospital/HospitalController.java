package controller.hospital;

import java.io.Serializable;
import java.util.HashMap;

import banco.dados.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.CategoriaInexistenteException;
import exceptions.logica.CategoriaInvalidaException;
import exceptions.logica.LogicaException;
import exceptions.logica.LogoutException;
import exceptions.logica.ObjetoInexistenteException;
import exceptions.logica.OrgaoInexistenteException;
import exceptions.logica.PacienteNaoCadastradoException;
import exceptions.logica.TipoSanguineoInvalidoException;
import model.farmacia.Farmacia;
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

	/**
	 * Construtor do controller
	 */
	public HospitalController() {
		gerenciadorFuncionarios = new GerenciadorFuncionarios();
		farmacia = new Farmacia();
		gerenciadorProntuarios = new GerenciadorProntuario();
		bancoDeOrgaos = new BancoDeOrgaos();
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

	/**
	 * Libera o sistema para ser utilizado
	 * 
	 * @param chave
	 *            - chave para liberar o sistema
	 * @param nome
	 *            - nome do Diretor
	 * @param dataNascimento
	 *            - data de nascimento do diretor conforme o padrao dd/MM/yyyy
	 * @return String contendo a matricula do diretor
	 * @throws LogicaException
	 */
	public String liberaSistema(String chave, String nome, String dataNascimento) throws LogicaException {
		try {
			return gerenciadorFuncionarios.liberaSistema(chave, nome, dataNascimento);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao liberar o sistema. " + e.getMessage());
		}
	}

	/**
	 * Realiza login do usuario no sistema
	 * 
	 * @param matricula
	 *            - matricula do usuario
	 * @param senha
	 *            - senha do usuario
	 * @throws LogicaException
	 */
	public void login(String matricula, String senha) throws LogicaException {
		try {
			gerenciadorFuncionarios.login(matricula, senha);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Nao foi possivel realizar o login. " + e.getMessage());
		}
	}

	/**
	 * Realiza o login de um usuario, e informa um erro se nao houver nenhum
	 * usuario logado
	 * 
	 * @throws LogicaException
	 */
	public void logout() throws LogicaException {
		try {
			gerenciadorFuncionarios.logout();
		} catch (LogoutException e) {
			throw new LogicaException("Nao foi possivel realizar o logout. " + e.getMessage());
		}
	}

	/**
	 * Faz uma requisicao de algum atributo do funcionario
	 * 
	 * @param matricula
	 *            - matricula do funcionario
	 * @param atributo
	 *            - atributo desejado
	 * @return Valor do atributo de funcionario
	 * @throws LogicaException
	 */
	public String getInfoFuncionario(String matricula, String atributo) throws LogicaException {
		try {
			return gerenciadorFuncionarios.getInfoFuncionario(matricula, atributo);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro na consulta de funcionario. " + e.getMessage());
		}
	}

	/**
	 * Cadastra um funcionario, retornando o numero de sua matricula
	 * 
	 * @param nome
	 *            - nome do funcionario
	 * @param cargo
	 *            - cargo do funcionario
	 * @param dataNascimento
	 *            - data de nascimento no padrao dd/MM/yyyy
	 * @return Matricula do funcionario criado
	 * @throws LogicaException
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) throws LogicaException {
		try {
			return gerenciadorFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro no cadastro de funcionario. " + e.getMessage());
		}
	}

	/**
	 * Exclui o cadastro de um funcionario do sistema, solicitando do usuario
	 * logado a senha do diretor para que a remocao seja feita
	 * 
	 * @param matricula
	 *            - matricula do funcionario a ser excluido
	 * @param senha
	 *            - senha do diretor
	 * @throws LogicaException
	 */
	public void excluiFuncionario(String matricula, String senha) throws LogicaException {
		try {
			gerenciadorFuncionarios.excluiFuncionario(matricula, senha);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao excluir funcionario. " + e.getMessage());
		}
	}

	/**
	 * Atualiza o parametro desejado do funcionario, que nao eh o usuario logado
	 * atualmente no sistema.
	 * 
	 * @param matricula
	 *            - matricula do funcionario que deseja alterar
	 * @param atributo
	 *            - atributo que deseja alterar
	 * @param novoValor
	 *            - novo valor do atributo
	 * @throws LogicaException
	 */
	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor) throws LogicaException {
		try {
			gerenciadorFuncionarios.atualizaInfoFuncionario(matricula, atributo, novoValor);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}

	/**
	 * Atualiza o parametro desejado do usuario logado atualmente no sistema
	 * 
	 * @param atributo
	 *            - atributo que deseja alterar
	 * @param novoValor
	 *            - novo valor do atributo
	 * @throws LogicaException
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws LogicaException {
		try {
			gerenciadorFuncionarios.atualizaInfoFuncionario(atributo, novoValor);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}

	/**
	 * Atualiza a senha do usuario logado atualmente no sistema
	 * 
	 * @param antigaSenha
	 *            - senha antiga
	 * @param novaSenha
	 *            - senha nova
	 * @throws LogicaException
	 */
	public void atualizaSenha(String antigaSenha, String novaSenha) throws LogicaException {
		try {
			gerenciadorFuncionarios.atualizaSenha(antigaSenha, novaSenha);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}

	/**
	 * Cadastra um medicamento no sistema. Informa um erro se o usuario logado
	 * nao tiver permissao de cadastrar medicamentos
	 * 
	 * @param nome
	 *            - nome do medicamento
	 * @param tipo
	 *            - tipo do medicamento
	 * @param preco
	 *            - preco do medicamento
	 * @param quantidade
	 *            - numero de unidades do medicamento
	 * @param categorias
	 *            - categorias do medicamento
	 * @return String com o nome do medicamento cadastrado
	 * @throws LogicaException
	 */
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws LogicaException {
		try {
			return farmacia.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro no cadastro de medicamento. " + e.getMessage());
		}
	}

	/**
	 * Faz uma requisicao de algum atributo do medicamento
	 * 
	 * @param requisicao
	 *            - atributo que deseja ler
	 * @param nome
	 *            - nome do medicamento
	 * @return String com o valor do atributo
	 * @throws LogicaException
	 */
	public String getInfoMedicamento(String requisicao, String nome) throws LogicaException {
		try {
			return farmacia.getInfoMedicamento(requisicao, nome);
		} catch (LogicaException e) {
			throw new LogicaException("Erro no cadastro de medicamento. " + e.getMessage());
		}
	}

	/**
	 * Atualiza o atributo desejado em um medicamento
	 * 
	 * @param nome
	 *            - nome do medicamento que deseja alterar
	 * @param atributo
	 *            - atributo que deseja alterar
	 * @param novoValor
	 *            - novo valor do atributo
	 * @throws LogicaException
	 */
	public void atualizaMedicamento(String nome, String atributo, String novoValor) throws LogicaException {
		try {
			farmacia.atualizaMedicamento(nome, atributo, novoValor);
		} catch (LogicaException e) {
			throw new LogicaException("Erro ao atualizar medicamento. " + e.getMessage());
		}
	}

	/**
	 * Consulta os medicamentos cadastrados de uma categoria. Informa erros se a
	 * categoria eh invalida, ou se nao existem medicamentos cadastrados na
	 * categoria
	 * 
	 * @param categoria
	 *            - categoria do medicamento
	 * @return String com todos os medicamentos da categoria desejada
	 * @throws LogicaException
	 */
	public String consultaMedCategoria(String categoria) throws LogicaException {
		try {
			return farmacia.getMedicamentosPelaCategoria(categoria);
		} catch (CategoriaInexistenteException | CategoriaInvalidaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}

	/**
	 * Consulta todas as informacoes de um medicamento cadastrado. Informa
	 * mensagem de erro caso o medicamento solicitado nao exista
	 * 
	 * @param nome
	 *            - nome do medicamento solicitado
	 * @return String representando as informacoes do medicamento
	 * @throws LogicaException
	 */
	public String consultaMedNome(String nome) throws LogicaException {
		try {
			return farmacia.consultaMedNome(nome);
		} catch (LogicaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}

	/**
	 * Consulta todos os medicamentos cadastrados atualmente na farmacia
	 * 
	 * @param tipoOrdenacao
	 *            - tipo de ordenacao desejada (preco ou alfabetica)
	 * @return String com todos os medicamentos da farmacia
	 * @throws LogicaException
	 */
	public String getEstoqueFarmacia(String tipoOrdenacao) throws LogicaException {
		try {
			return farmacia.getEstoqueFarmacia(tipoOrdenacao);
		} catch (LogicaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}

	/**
	 * Cadastra um paciente no sistema, ao mesmo tempo que associa esse paciente
	 * a um prontuario. Informa um erro se o usuario logado nao tiver permissao
	 * de cadastrar pacientes
	 * 
	 * @param nome
	 *            - nome do paciente
	 * @param dataNascimento
	 *            - data de nascimento do paciente no padrao dd/MM/yyyy
	 * @param peso
	 *            - peso do paciente
	 * @param sexoBiologico
	 *            - sexo biologico do paciente
	 * @param genero
	 *            - genero do paciente
	 * @param tipoSanguineo
	 *            - tipo sanguineo do paciente
	 * @return String com o nome do paciente cadastrado
	 * @throws LogicaException
	 */
	public String cadastraPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws LogicaException {
		try {
			return gerenciadorProntuarios.cadastraPaciente(nome, dataNascimento, peso, sexoBiologico, genero,
					tipoSanguineo);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Nao foi possivel cadastrar o paciente. " + e.getMessage());
		}
	}

	/**
	 * Faz uma requisicao de algum atributo de um paciente
	 * 
	 * @param nome
	 *            - nome do paciente
	 * @param atributo
	 *            - atributo do paciente
	 * @return String com o valor do atributo
	 */
	public String getInfoPaciente(String nome, String atributo) {
		return gerenciadorProntuarios.getInfoPaciente(nome, atributo);
	}

	/**
	 * Consulta o nome do paciente na posicao informada. Informa erros se nao
	 * existe paciente na posicao, ou se a posicao for invalida
	 * 
	 * @param posicao
	 *            - posicao do paciente na lista de prontuarios
	 * @return String com o nome do paciente
	 * @throws LogicaException
	 */
	public String getProntuario(int posicao) throws LogicaException {
		try {
			return gerenciadorProntuarios.getProntuario(posicao);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro ao consultar prontuario. " + e.getMessage());
		}
	}

	/**
	 * Cadastra um orgao no banco de orgaos. Informa erros se o usuario logado
	 * nao tiver permissao para cadastrar orgaos
	 * 
	 * @param nome
	 *            - nome do orgao a cadastrar
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao
	 * @throws LogicaException
	 */
	public void cadastraOrgao(String nome, String tipoSanguineo) throws LogicaException {
		try {
			bancoDeOrgaos.cadastraOrgao(nome, tipoSanguineo);
		} catch (TipoSanguineoInvalidoException | DadoInvalidoException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	/**
	 * Busca todos os orgaos que possuam o tipo sanguineo informado
	 * 
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao
	 * @return String contendo todos os orgaos
	 * @throws LogicaException
	 */
	public String buscaOrgPorSangue(String tipoSanguineo) throws LogicaException {
		try {
			return bancoDeOrgaos.buscaOrgPorSangue(tipoSanguineo);
		} catch (LogicaException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	/**
	 * Busca um orgao pelo nome. Informa erro se nao existir orgao(s) com o nome
	 * informado
	 * 
	 * @param nome
	 *            - nome do orgao a buscar
	 * @return String contendo todos os orgaos
	 * @throws LogicaException
	 */
	public String buscaOrgPorNome(String nome) throws LogicaException {
		try {
			return bancoDeOrgaos.buscaOrgPorNome(nome);
		} catch (DadoInvalidoException | OrgaoInexistenteException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	/**
	 * Busca um orgao especifico no banco de orgaos
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao
	 * @return Boolean se o orgao existe
	 * @throws LogicaException
	 */
	public boolean buscaOrgao(String nome, String tipoSanguineo) throws LogicaException {
		try {
			return bancoDeOrgaos.verificaOrgao(nome, tipoSanguineo);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	/**
	 * Retira um orgao do banco de orgaos (para transplante, por exemplo).
	 * Informa erros se o banco nao possuir o orgao desejado
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao
	 * @return Boolean se a retirada foi feita
	 * @throws LogicaException
	 */
	public boolean retiraOrgao(String nome, String tipoSanguineo) throws LogicaException {
		try {
			return bancoDeOrgaos.retiraOrgao(nome, tipoSanguineo);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro na retirada de orgaos. " + e.getMessage());
		}
	}

	/**
	 * Busca a quantidade de orgaos com o nome informado
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @return Inteiro com a quantidade de orgaos
	 * @throws LogicaException
	 */
	public int qtdOrgaos(String nome) throws LogicaException {
		try {
			return bancoDeOrgaos.qtdOrgaos(nome);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	/**
	 * Busca a quantidade total de orgaos existentes no banco de orgaos
	 * 
	 * @return Inteiro com a quantidade de orgaos
	 */
	public int totalOrgaosDisponiveis() {
		return bancoDeOrgaos.totalOrgaosDisponiveis();
	}

	/**
	 * Busca o paciente pelo nome
	 * 
	 * @param nome
	 *            - nome do paciente
	 * @return Nome do paciente
	 */
	public String getPacienteID(String nome) {
		return gerenciadorProntuarios.getInfoPaciente(nome, "nome");
	}

	/**
	 * Realiza um procedimento em um paciente. Informa mensagens de erro, caso o
	 * procedimento nao possa ser realizado
	 * 
	 * @param procedimentoSolicitado
	 *            - nome do procedimento desejado
	 * @param nomePaciente
	 *            - nome do paciente a realizar o procedimento
	 * @param medicamentos
	 *            - medicamentos necessarios para a realizacao do procedimento
	 * @throws LogicaException
	 */
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, String medicamentos)
			throws LogicaException {
		try {
			ValidaProcedimento.validaProcedimentoSolicitado(procedimentoSolicitado);
			ValidacaoMedicamentos.validaNomeMedicamento(medicamentos);
			ValidaProcedimento.validaNomePaciente(nomePaciente);

			double precoMedicamentos = farmacia.calculaPrecoMedicamentos(medicamentos);

			HashMap<String, Object> params = new HashMap<>();

			params.put("nomeMedico", BancoDeDados.getInstance().getUsuarioLogado().getNome());

			gerenciadorProntuarios.realizaProcedimento(procedimentoSolicitado, nomePaciente, precoMedicamentos, params);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}

	/**
	 * Realiza um procedimento em um paciente. Informa mensagens de erro, caso o
	 * procedimento nao possa ser realizado. Nesse caso, o procedimento eh um
	 * transplante de orgaos
	 * 
	 * @param procedimentoSolicitado
	 *            - nome do procedimento desejado
	 * @param nomePaciente
	 *            - nome do paciente a realizar o procedimento
	 * @param medicamentos
	 *            - medicamentos necessarios para a realizacao do procedimento
	 * @param nomeOrgao
	 *            - nome do orgao a ser solicitado
	 * @throws LogicaException
	 */
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, String nomeOrgao,
			String medicamentos) throws LogicaException {
		try {
			ValidaProcedimento.validaProcedimentoSolicitado(procedimentoSolicitado);
			ValidaProcedimento.validaNomePaciente(nomePaciente);
			ValidacaoMedicamentos.validaNomeMedicamento(medicamentos);

			String tipoSanguineoPaciente = gerenciadorProntuarios.getInfoPaciente(nomePaciente, "tiposanguineo");

			double precoMedicamentos = farmacia.calculaPrecoMedicamentos(medicamentos);

			if (!bancoDeOrgaos.verificaOrgao(nomeOrgao, tipoSanguineoPaciente)) {
				throw new LogicaException("Banco nao possui o orgao especificado.");
			}

			bancoDeOrgaos.retiraOrgao(nomeOrgao, tipoSanguineoPaciente);

			HashMap<String, Object> params = new HashMap<>();

			params.put("nomeMedico", BancoDeDados.getInstance().getUsuarioLogado().getNome());
			params.put("orgao", nomeOrgao);

			gerenciadorProntuarios.realizaProcedimento(procedimentoSolicitado, nomePaciente, precoMedicamentos, params);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}

	/**
	 * Realiza um procedimento em um paciente. Informa mensagens de erro, caso o
	 * procedimento nao possa ser realizado. Nesse caso, nao informa
	 * medicamentos necessarios
	 * 
	 * @param procedimentoSolicitado
	 *            - nome do procedimento desejado
	 * @param nomePaciente
	 *            - nome do paciente a realizar o procedimento
	 * @throws LogicaException
	 */
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

	/**
	 * Calcula o preco total do custo de medicamentos
	 * 
	 * @param medicamentos
	 *            - medicamentos solicitados
	 * @return Double com o valor total
	 * @throws LogicaException
	 */
	public double calculaPrecoMedicamentos(String medicamentos) throws LogicaException {
		try {
			return farmacia.calculaPrecoMedicamentos(medicamentos);
		} catch (ObjetoInexistenteException e) {
			throw new LogicaException("Erro ao calcular preco de medicamentos. " + e.getMessage());
		}
	}

	/**
	 * Consulta o total de procedimentos realizados por um paciente
	 * 
	 * @param nomePaciente
	 *            - nome do paciente que deseja consultar procedimentos
	 * @return Inteiro com a quantidade de procedimentos
	 */
	public int getTotalProcedimento(String nomePaciente) {
		return gerenciadorProntuarios.getTotalProcedimento(nomePaciente);
	}

	/**
	 * Consulta o total de pontos de fidelidade de um paciente
	 * 
	 * @param nomePaciente
	 *            - nome do paciente que deseja consultar pontos
	 * @return Inteiro com a quantidade de pontos
	 */
	public int getPontosFidelidade(String nomePaciente) {
		return gerenciadorProntuarios.getPontosFidelidade(nomePaciente);
	}

	/**
	 * Consulta todos os gastos de um paciente
	 * 
	 * @param nomePaciente
	 *            - nome do paciente que deseja consultar gastos
	 * @return String com a quantidade de gastos
	 */
	public String getGastosPaciente(String nomePaciente) {
		return gerenciadorProntuarios.getGastosPaciente(nomePaciente);
	}

	/**
	 * Exporta a ficha de um paciente para o arquivo do banco de dados
	 * 
	 * @param idPaciente
	 *            - id do paciente solicitado
	 * @throws LogicaException
	 */
	public void exportaFichaPaciente(String idPaciente) throws LogicaException {
		try {
			gerenciadorProntuarios.exportaFichaPaciente(idPaciente);
		} catch (PacienteNaoCadastradoException e) {
			throw new LogicaException("Erro ao exportar ficha do paciente. " + e.getMessage());
		}
	}
}
