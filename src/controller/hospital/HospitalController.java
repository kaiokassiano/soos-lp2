package controller.hospital;

import java.util.ArrayList;
import java.util.HashMap;

import data.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.AtributoInvalidoException;
import exceptions.logica.CategoriaInexistenteException;
import exceptions.logica.CategoriaInvalidaException;
import exceptions.logica.ChaveIncorretaException;
import exceptions.logica.ComparacaoInvalidaException;
import exceptions.logica.LogicaException;
import exceptions.logica.ObjetoInexistenteException;
import exceptions.logica.OperacaoInvalidaException;
import exceptions.logica.PermissaoException;
import exceptions.logica.SenhaIncorretaException;
import exceptions.logica.StringVaziaException;
import factory.funcionarios.FuncionarioFactory;
import model.farmacia.Farmacia;
import model.farmacia.Medicamento;
import model.usuarios.Diretor;
import model.usuarios.Funcionario;
import model.usuarios.Paciente;
import model.usuarios.PermissaoFuncionario;
import model.usuarios.Prontuario;

/**
 * Controller principal da aplicação, faz o gerenciamento de todas as áreas e
 * assim como o do sistema
 */
public class HospitalController {

	private static final String CHAVE_SISTEMA = "c041ebf8";

	private FuncionarioFactory funcionarioFactory;
	private Farmacia farmacia;

	private Funcionario usuarioLogado;
	private HashMap<String, Funcionario> funcionarios;
	private ArrayList<Prontuario> prontuarios;
	private BancoDeDados bancoDeDados;

	private boolean sistemaLiberado;

	public HospitalController() {
		funcionarioFactory = new FuncionarioFactory();
		farmacia = new Farmacia();

		funcionarios = new HashMap<String, Funcionario>();
		prontuarios = new ArrayList<Prontuario>();

		bancoDeDados = BancoDeDados.getInstance();
	}

	public void iniciaSistema() {
		bancoDeDados.init();
	}

	/**
	 * Fecha o sistema, junto com o banco de dados, verificando se alguém ainda
	 * está logado e, caso esteja, joga um erro
	 */
	public void fechaSistema() throws LogicaException {
		if (isUsuarioLogado()) {
			throw new LogicaException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
		}
		bancoDeDados.fechar();
	}

	/**
	 * Libera o sistema, criando um usuário com privilégios de Diretor e
	 * retornando o seu numero de matrícula
	 * 
	 * @param chave
	 *            Chave para liberar o sistema
	 * @param nome
	 *            Nome do diretor
	 * @param dataNascimento
	 *            Data de nascimento do diretor conforme o padrão dd/MM/yyyy
	 * @return Matrícula do diretor
	 * @throws LogicaException
	 * @throws DadoInvalidoException
	 */
	public String liberaSistema(String chave, String nome, String dataNascimento)
			throws LogicaException, DadoInvalidoException {
		if (chave == null) {
			throw new NullStringException("Chave nao pode ser nulo.");
		}
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo.");
		}
		if (dataNascimento == null) {
			throw new NullStringException("Data nao pode ser nulo.");
		}
		if (sistemaLiberado) {
			throw new LogicaException("Sistema liberado anteriormente.");
		}
		if (!chave.equals(CHAVE_SISTEMA)) {
			throw new ChaveIncorretaException("Chave invalida.");
		}
		sistemaLiberado = true;

		Diretor diretor = (Diretor) funcionarioFactory.criaFuncionario(nome, "Diretor Geral", dataNascimento);
		funcionarios.put(diretor.getMatricula(), diretor);

		return diretor.getMatricula();
	}

	/**
	 * Verifica se tem algum usuário logado
	 * 
	 * @return Boleano indicando se tem algum usuário logado
	 */
	public boolean isUsuarioLogado() {
		return usuarioLogado != null;
	}

	/**
	 * Realiza o login de um usuário
	 * 
	 * @param matricula
	 *            Matrícula do usuário
	 * @param senha
	 *            Senha do usuário
	 * @throws LogicaException
	 * @throws NullStringException
	 */
	public void login(String matricula, String senha) throws LogicaException, NullStringException {
		if (!sistemaLiberado) {
			throw new LogicaException("Sistema nao liberado.");
		} else if (isUsuarioLogado()) {
			throw new LogicaException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
		} else if (matricula == null) {
			throw new NullStringException("Matricula nao pode ser nulo.");
		} else if (senha == null) {
			throw new NullStringException("Senha nao pode ser nulo.");
		}

		Funcionario funcionario = getFuncionarioPorMatricula(matricula);

		if (!funcionario.getSenha().equals(senha)) {
			throw new SenhaIncorretaException("Senha incorreta.");
		}

		usuarioLogado = funcionario;
	}

	/**
	 * Realiza o logout de um usuário, e joga um erro caso não tenha usuários
	 * logados
	 * 
	 * @throws LogicaException
	 */
	public void logout() throws LogicaException {
		if (!isUsuarioLogado()) {
			throw new LogicaException("Nao ha um funcionario logado.");
		}
		usuarioLogado = null;
	}

	/**
	 * Retorna um funcionário dado seu numero de matrícula, e joga um erro
	 * caso não exista um funcionário com aquela matrícula
	 * 
	 * @param matricula
	 *            Matrícula do usuário
	 * @return Instância de um objeto Funcionario
	 * @throws ObjetoInexistenteException
	 */
	public Funcionario getFuncionarioPorMatricula(String matricula) throws ObjetoInexistenteException {
		Funcionario funcionario = funcionarios.get(matricula);

		if (funcionario == null) {
			throw new ObjetoInexistenteException("Funcionario nao cadastrado.");
		}

		return funcionario;
	}

	/**
	 * Cadastra um funcionário, retornando seu número de matrícula
	 * 
	 * @param nome
	 *            Nome do funcionário
	 * @param cargo
	 *            Cargo do funcionário
	 * @param dataNascimento
	 *            Data de nascimento do funcionário
	 * @return Matrícula do funcionário criado
	 * @throws LogicaException
	 * @throws DadoInvalidoException
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento)
			throws LogicaException, DadoInvalidoException {
		if (nome == null) {
			throw new NullStringException("Nome do funcionario nao pode ser nulo.");
		} else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome do funcionario nao pode ser vazio.");
		}
		if (cargo == null) {
			throw new NullStringException("Nome do cargo nao pode ser nulo.");
		} else if (cargo.trim().isEmpty()) {
			throw new StringVaziaException("Nome do cargo nao pode ser vazio.");
		}

		if (!sistemaLiberado) {
			throw new LogicaException("O sistema esta bloqueado.");
		} else if (!isUsuarioLogado()) {
			throw new LogicaException("Usuario nao esta logado.");
		} else if (!usuarioLogado.temPermissao(PermissaoFuncionario.criacaoUsuarios)) {
			throw new PermissaoException(
					"O funcionario " + usuarioLogado.getNome() + " nao tem permissao para cadastrar funcionarios.");
		} else if (cargo.equalsIgnoreCase("Diretor Geral")) {
			throw new PermissaoException("Nao eh possivel criar mais de um Diretor Geral.");
		}

		Funcionario funcionario = funcionarioFactory.criaFuncionario(nome, cargo, dataNascimento);
		funcionarios.put(funcionario.getMatricula(), funcionario);

		return funcionario.getMatricula();
	}

	/**
	 * Pega o atributo do funcionário requisitado
	 * 
	 * @param matricula
	 *            Matrícula do funcionário
	 * @param atributo
	 *            Atributo a ser requisitado
	 * @return Valor do atributo do funcionário
	 * @throws LogicaException
	 * @throws NullStringException
	 */
	public String getInfoFuncionario(String matricula, String atributo) throws LogicaException, NullStringException {
		if (!isUsuarioLogado()) {
			throw new LogicaException("Usuario nao esta logado.");
		} else if (matricula == null) {
			throw new NullStringException("Matricula nao pode ser nulo.");
		} else if (atributo == null) {
			throw new NullStringException("Atributo nao pode ser nulo.");
		}

		Funcionario funcionario = getFuncionarioPorMatricula(matricula);

		String attr = null;

		switch (atributo) {

		case "Nome":
			attr = funcionario.getNome();

		case "Cargo":
			attr = funcionario.getCargo();

		case "Data":
			attr = funcionario.getDataNascimento();

		case "Senha":
			if (usuarioLogado.getMatricula().equals(funcionario.getMatricula())) {
				attr = funcionario.getSenha();
			} else {
				throw new PermissaoException("A senha do funcionario eh protegida.");
			}
			break;

		default:
			throw new AtributoInvalidoException("Atributo invalido.");
		}

		return attr;
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

	public String getInfoPaciente(String nomePaciente, String atributo) throws Exception {

		Paciente paciente = retornaPacientePeloNome(nomePaciente);

		String saida = "";

		switch (atributo) {
		case "Nome":
			saida = paciente.getNome();
		case "Data":
			saida = paciente.getData();
		case "Sexo":
			saida = paciente.getSexo();
		case "Genero":
			saida = paciente.getGenero();
		case "TipoSanguineo":
			saida = paciente.getTipoSanguineo();
		case "Peso":
			saida = paciente.getPeso();
		}
		return saida;
	}

	public void cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) throws Exception {

		Paciente p = retornaPacientePeloNome(nome);
		if (p != null) {
			throw new Exception("Nao foi possivel cadastrar o paciente. Paciente ja cadastrado.");
		}

		try {
			Paciente paciente = new Paciente(nome, data, peso, sexo, genero, tipoSanguineo);
			Prontuario prontuario = new Prontuario(paciente);
			this.prontuarios.add(prontuario);
		} catch (Exception e) {
			throw new Exception("Nao foi possivel cadastrar o paciente." + e.getMessage());
		}
	}

	public Paciente retornaPacientePeloNome(String nome) throws Exception {
		try {
			for (int i = 0; i < prontuarios.size(); i++) {
				if (prontuarios.get(i).getPaciente().getNome().equals(nome)
						&& prontuarios.get(i).getPaciente() != null) {
					return prontuarios.get(i).getPaciente();
				}
			}
		} catch (Exception e) {
			throw new Exception("Paciente nao encontrado.");
		}
		return null;
	}
}
