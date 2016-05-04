package controller.hospital;

import java.util.HashMap;

import data.BancoDeDados;
import exceptions.dado.*;
import exceptions.logica.*;
import factory.funcionarios.*;
import factory.medicamentos.*;
import model.usuarios.*;

/**
 * Controller principal da aplica��o, faz o gerenciamento de todas as �reas
 * e assim como o do sistema
 */
public class HospitalController {

	private static final String chaveSistema = "c041ebf8";
	
	private FuncionarioFactory funcionarioFactory;
	private MedicamentoFactory medicamentoFactory;
	
	private Funcionario usuarioLogado;
	private HashMap<String, Funcionario> funcionarios;
	private BancoDeDados bancoDeDados;
	
	private boolean sistemaLiberado;
	
	/**
	 * Construtor do HospitalController
	 */
	public HospitalController() {
		funcionarioFactory = new FuncionarioFactory();
		medicamentoFactory = new MedicamentoFactory();
		
		funcionarios = new HashMap<String, Funcionario>();
		
		bancoDeDados = BancoDeDados.getInstance();
	}
	
	/**
	 * Inicia o sistema, juntamente com o banco de dados
	 */
	public void iniciaSistema() {
		bancoDeDados.init();
	}
	
	/**
	 * Fecha o sistema, junto com o banco de dados, verificando 
	 * se algu�m ainda est� logado e, caso esteja, joga um erro
	 */
	public void fechaSistema() {
		if (isUsuarioLogado()) {
			throw new LogicaException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
		}
		bancoDeDados.fechar();
	}
	
	/**
	 * Libera o sistema, criando um usu�rio com privil�gios de Diretor
	 * e retornando o seu numero de matr�cula
	 * 
	 * @param chave          Chave para liberar o sistema
	 * @param nome           Nome do diretor
	 * @param dataNascimento Data de nascimento do diretor conforme o padr�o dd/MM/yyyy
	 * @return               Matr�cula do diretor
	 */
	public String liberaSistema(String chave, String nome, String dataNascimento) {
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
	 * Verifica se tem algum usu�rio logado
	 * 
	 * @return Boleano indicando se tem algum usu�rio logado
	 */
	public boolean isUsuarioLogado() {
		return usuarioLogado != null;
	}
	
	/**
	 * Realiza o login de um usu�rio
	 * 
	 * @param matricula Matr�cula do usu�rio
	 * @param senha     Senha do usu�rio
	 */
	public void login(String matricula, String senha) {
		if (!sistemaLiberado) {
			throw new LogicaException("Sistema nao liberado.");
		}
		else if (isUsuarioLogado()) {
			throw new LogicaException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
		}
		else if (matricula == null) {
			throw new NullStringException("Matricula nao pode ser nulo.");
		}
		else if (senha == null) {
			throw new NullStringException("Senha nao pode ser nulo.");
		}
		
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		
		if (!funcionario.getSenha().equals(senha)) {
			throw new SenhaIncorretaException("Senha incorreta.");
		}
		
		usuarioLogado = funcionario;
	}
	
	/**
	 * Realiza o logout de um usu�rio, e joga um erro caso n�o
	 * tenha usu�rios logados
	 */
	public void logout() {
		if (!isUsuarioLogado()) {
			throw new LogicaException("Nao ha um funcionario logado.");
		}
		usuarioLogado = null;
	}
	
	/**
	 * Retorna um funcion�rio dado seu numero de matr�cula, e joga um erro
	 * caso n�o exista um funcion�rio com aquela matr�cula
	 * 
	 * @param matricula Matr�cula do usu�rio
	 * @return          Inst�ncia de um objeto Funcion�rio
	 */
	public Funcionario getFuncionarioPorMatricula(String matricula) {
		Funcionario funcionario = funcionarios.get(matricula);
		
		if (funcionario == null) {
			throw new ObjetoInexistenteException("Funcionario nao cadastrado.");
		}
		
		return funcionario;
	}
	
	/**
	 * Cadastra um funcion�rio, retornando seu n�mero de matr�cula
	 * 
	 * @param nome           Nome do funcion�rio
	 * @param cargo          Cargo do funcion�rio
	 * @param dataNascimento Data de nascimento do funcion�rio
	 * @return               Matr�cula do funcion�rio criado
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) {
		if (nome == null) {
			throw new NullStringException("Nome do funcionario nao pode ser nulo.");
		}
		else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome do funcionario nao pode ser vazio.");
		}
		if (cargo == null) {
			throw new NullStringException("Nome do cargo nao pode ser nulo.");
		}
		else if (cargo.trim().isEmpty()) {
			throw new StringVaziaException("Nome do cargo nao pode ser vazio.");
		}
		
		if (!sistemaLiberado) {
			throw new LogicaException("O sistema esta bloqueado.");
		}
		else if (!isUsuarioLogado()) {
			throw new LogicaException("Usuario nao esta logado.");
		}
		else if (!usuarioLogado.temPermissao(PermissaoFuncionario.criacaoUsuarios)) {
			throw new PermissaoException("O funcionario " + usuarioLogado.getNome() + " nao tem permissao para cadastrar funcionarios.");
		}
		else if (cargo.equalsIgnoreCase("Diretor Geral")) {
			throw new PermissaoException("Nao eh possivel criar mais de um Diretor Geral.");
		}
		
		Funcionario funcionario = funcionarioFactory.criaFuncionario(nome, cargo, dataNascimento);
		funcionarios.put(funcionario.getMatricula(), funcionario);
		
		return funcionario.getMatricula();
	}
	
	/**
	 * Pega o atributo do funcion�rio requisitado
	 * 
	 * @param matricula Matr�cula do funcion�rio
	 * @param atributo  Atributo a ser requisitado
	 * @return          Valor do atributo do funcion�rio
	 */
	public String getInfoFuncionario(String matricula, String atributo) {
		if (!isUsuarioLogado()) {
			throw new LogicaException("Usuario nao esta logado.");
		}
		else if (matricula == null) {
			throw new NullStringException("Matricula nao pode ser nulo.");
		}
		else if (atributo == null) {
			throw new NullStringException("Atributo nao pode ser nulo.");
		}
		
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		
		String attr = null;
		
		if (atributo.equalsIgnoreCase("Nome")) {
			attr = funcionario.getNome();
		}
		else if (atributo.equalsIgnoreCase("Cargo")) {
			attr = funcionario.getCargo();
		}
		else if (atributo.equalsIgnoreCase("Data")) {
			attr = funcionario.getDataNascimento();
		}
		else if (atributo.equalsIgnoreCase("Senha")) {
			if (usuarioLogado.getMatricula().equals(funcionario.getMatricula())) {
				attr = funcionario.getSenha();
			}
			else {
				throw new PermissaoException("A senha do funcionario eh protegida.");
			}
		}
		else {
			throw new AtributoInvalidoException("Atributo invalido.");
		}
		
		return attr;
	}

}
