package controller.hospital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.AtributoInvalidoException;
import exceptions.logica.ChaveIncorretaException;
import exceptions.logica.LogicaException;
import exceptions.logica.ObjetoInexistenteException;
import exceptions.logica.PacienteJaCadastradoException;
import exceptions.logica.PermissaoException;
import exceptions.logica.SenhaIncorretaException;
import exceptions.logica.StringVaziaException;
import factory.funcionarios.FuncionarioFactory;
import factory.medicamentos.MedicamentoFactory;
import model.usuarios.Diretor;
import model.usuarios.Funcionario;
import model.usuarios.Paciente;
import model.usuarios.PermissaoFuncionario;
import model.usuarios.Prontuario;

/**
 * Classe responsável pelo camada de Controle de fluxo de execuçao no modelo
 * MVC.
 * 
 * @author Lucas Cordeiro
 * @version 1.0
 */

public class HospitalController {

	private static final String chaveSistema = "c041ebf8";

	private FuncionarioFactory funcionarioFactory;
	private MedicamentoFactory medicamentoFactory;

	private Funcionario usuarioLogado;
	private HashMap<String, Funcionario> funcionarios;
	private BancoDeDados bancoDeDados;

	private boolean sistemaLiberado;
	private List<Prontuario> prontuarios = new ArrayList<Prontuario>();

	/**
	 * Cria novos objetos do tipo Funcionario, Medicamento e uma instancia da
	 * classe BancoDeDados.
	 */

	public HospitalController() {
		funcionarioFactory = new FuncionarioFactory();
		medicamentoFactory = new MedicamentoFactory();

		funcionarios = new HashMap<String, Funcionario>();

		bancoDeDados = BancoDeDados.getInstance();
	}

	/**
	 * Incia o Sistema.
	 */

	public void iniciaSistema() {
		bancoDeDados.init();
	}

	/**
	 * Fecha o Sistema desde que nao haja nenhum usuario logado.
	 * 
	 * @throws LogicaException
	 */

	public void fechaSistema() throws LogicaException {
		if (isUsuarioLogado()) {
			throw new LogicaException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
		}
		bancoDeDados.fechar();
	}

	/**
	 * Libera o sistema para uso criando um Diretor Geral e retornando o numero
	 * da matricula, caso a chave informada seja valida. Tambem verifica se o
	 * sitema ja foi liberado anteriormente antes de cria-lo.
	 * 
	 * @param chave
	 * @param nome
	 * @param dataNascimento
	 * @return Numero da Matricula do Diretor Geral.
	 * @throws DadoInvalidoException
	 * @throws LogicaException
	 */

	public String liberaSistema(String chave, String nome, String dataNascimento)
			throws DadoInvalidoException, LogicaException {
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
		if (!chave.equals(chaveSistema)) {
			throw new ChaveIncorretaException("Chave invalida.");
		}
		sistemaLiberado = true;

		Diretor diretor = (Diretor) funcionarioFactory.criaFuncionario(nome, "Diretor Geral", dataNascimento);
		funcionarios.put(diretor.getMatricula(), diretor);

		return diretor.getMatricula();
	}

	/**
	 * Verifica se existe Usuario logado no sistema.
	 * 
	 * @return Tipo booleano que representa se existe Usuario logado no sistema.
	 */

	public boolean isUsuarioLogado() {
		return usuarioLogado != null;
	}

	/**
	 * Faz Login no sistema; Verifica se ha algum funcionario logado Gera
	 * excessoes de Matricula ou Senha forem nulos.
	 * 
	 * @param matricula
	 * @param senha
	 * @throws DadoInvalidoException
	 * @throws LogicaException
	 */

	public void login(String matricula, String senha) throws DadoInvalidoException, LogicaException {
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
	 * Faz logout no Sistema apresentando mensagem de erro caso nao haja
	 * funcionario logado.
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
	 * Obtem o objeto do tipo Funcionario atraves da String matricula. Apresenta
	 * mensagem de erro caso o funcionario não esteja cadastrado.
	 * 
	 * @param matricula
	 * @return
	 * @throws DadoInvalidoException
	 * @throws ObjetoInexistenteException
	 */

	public Funcionario getFuncionarioPorMatricula(String matricula)
			throws DadoInvalidoException, ObjetoInexistenteException {
		Funcionario funcionario = funcionarios.get(matricula);

		if (funcionario == null) {
			throw new ObjetoInexistenteException("Funcionario nao cadastrado.");
		}

		return funcionario;
	}

	/**
	 * Cadastra um novo objeto do tipo Funcionario e retorna o numero da
	 * matricula do funcionario.
	 * 
	 * @param nome
	 * @param cargo
	 * @param dataNascimento
	 * @return
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
	 * Retorna informacoes sobre o funcionario, dependendo do parametro que for
	 * informado.
	 * 
	 * @param matricula
	 * @param atributo
	 * @return Informação do funcionário requisitada
	 * @throws DadoInvalidoException
	 * @throws LogicaException
	 */
	public String getInfoFuncionario(String matricula, String atributo) throws DadoInvalidoException, LogicaException {
		if (!isUsuarioLogado()) {
			throw new LogicaException("Usuario nao esta logado.");
		} else if (matricula == null) {
			throw new NullStringException("Matricula nao pode ser nulo.");
		} else if (atributo == null) {
			throw new NullStringException("Atributo nao pode ser nulo.");
		}

		Funcionario funcionario = getFuncionarioPorMatricula(matricula);

		String attr = null;

		if (atributo.equalsIgnoreCase("Nome")) {
			attr = funcionario.getNome();
		} else if (atributo.equalsIgnoreCase("Cargo")) {
			attr = funcionario.getCargo();
		} else if (atributo.equalsIgnoreCase("Data")) {
			attr = funcionario.getDataNascimento();
		} else if (atributo.equalsIgnoreCase("Senha")) {
			if (usuarioLogado.getMatricula().equals(funcionario.getMatricula())) {
				attr = funcionario.getSenha();
			} else {
				throw new PermissaoException("A senha do funcionario eh protegida.");
			}
		} else {
			throw new AtributoInvalidoException("Atributo invalido.");
		}

		return attr;
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

	// TODO A FAZER
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
				if (prontuarios.get(i).getPaciente().getNome().equals(nome) && prontuarios.get(i).getPaciente() != null) {
					return prontuarios.get(i).getPaciente();
				}
			}
		} catch (Exception e) {
			throw new Exception("Paciente nao encontrado.");
		}
		return null;
	}
}
