package model.usuarios;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;

import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import exceptions.logica.StringVaziaException;

/**
 * Model que representa todos os funcionários do sistema
 */
public abstract class Funcionario implements Serializable {

	private static final long serialVersionUID = 2579440738034457486L;

	private String nome;
	private HashSet<PermissaoFuncionario> permissoes;
	private LocalDate dataNascimento;
	private String matricula;
	private String senha;

	/**
	 * Construtor de um objeto do tipo funcionários
	 * 
	 * @param nome
	 *            Nome do funcionário
	 * @param matricula
	 *            Matrícula do funcionário
	 * @param dataNascimento
	 *            Data de nascimento do funcionário
	 * @throws DadoInvalidoException
	 * @throws StringVaziaException
	 */
	public Funcionario(String nome, String matricula, LocalDate dataNascimento)
			throws DadoInvalidoException, LogicaException {
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo");
		} else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome nao pode ser vazio.");
		}
		if (dataNascimento == null) {
			throw new DadoInvalidoException("Data nao pode ser nulo.");
		}

		this.permissoes = definePermissoes(); // chamada polimorfica
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.matricula = matricula;
		gerarSenha();
	}

	/**
	 * Define as permissões que o funcionário atual possui
	 * 
	 * @return Conjunto contendo todas as permissões que o usuário possui
	 */
	public abstract HashSet<PermissaoFuncionario> definePermissoes();

	/**
	 * Verifica se o usuário possui uma determinada permissão
	 * 
	 * @param permissao
	 *            Permissão a ser checada
	 * @return Boleano indicando se o usuário possui a permissão
	 * @throws DadoInvalidoException
	 */
	public boolean temPermissao(PermissaoFuncionario permissao) throws DadoInvalidoException {
		if (permissao == null) {
			throw new DadoInvalidoException("Permissao nao pode ser nulo.");
		}
		return permissoes.contains(permissao);
	}

	/**
	 * Consulta matricula do funcionario
	 * 
	 * @return String matricula do funcionario
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Define o nome do funcionario
	 * 
	 * @param nome
	 *            - nome do funcionario
	 * @throws StringVaziaException
	 * @throws NullStringException
	 */
	public void setNome(String nome) throws StringVaziaException, NullStringException {
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo.");
		} else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome nao pode ser vazio.");
		}
		this.nome = nome;
	}

	/**
	 * Consulta nome do funcionario
	 * 
	 * @return String nome do funcionario
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Gera uma senha automática para o usuário, seguindo o padrão de que os 4
	 * primeiros digitos correspondem ao ano de nascimento do usuário, e os 4
	 * últimos correspondem aos 4 primeiros digitos de sua matrícula
	 */
	private void gerarSenha() {
		this.senha = dataNascimento.getYear() + getMatricula().substring(0, 4);
	}

	/**
	 * Muda a senha de um usuario
	 * 
	 * @param senha
	 *            - nova senha
	 * @throws NullStringException
	 */
	protected void setSenha(String senha) throws NullStringException {
		if (senha == null) {
			throw new NullStringException("Senha nao pode ser nula.");
		}
		this.senha = senha;
	}

	/**
	 * Consulta a senha de um usuario
	 * 
	 * @return String senha do usuario
	 */
	protected String getSenha() {
		return senha;
	}

	/**
	 * Define a data de nascimento do usuario
	 * 
	 * @param data
	 *            - data de nascimento de acordo com o padrao dd/MM/yyyy
	 * @throws DadoInvalidoException
	 */
	public void setData(LocalDate data) throws DadoInvalidoException {
		if (data == null) {
			throw new DadoInvalidoException("Data nao pode ser nulo.");
		}
		this.dataNascimento = data;
	}

	/**
	 * Consulta a data de nascimento de um funcionario
	 * 
	 * @return String data de nascimento
	 */
	public String getData() {
		return dataNascimento.toString();
	}

	/**
	 * Retorna a representação do cargo do funcionário em forma de String
	 * 
	 * @return Cargo do funcionário
	 */
	public abstract String getCargo();
}
