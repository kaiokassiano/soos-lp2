package model.usuarios;

import java.io.Serializable;
import java.util.HashMap;

import banco.dados.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.AtributoInvalidoException;
import exceptions.logica.ChaveIncorretaException;
import exceptions.logica.LogicaException;
import exceptions.logica.LoginException;
import exceptions.logica.LogoutException;
import exceptions.logica.ObjetoInexistenteException;
import exceptions.logica.PadraoException;
import exceptions.logica.PermissaoException;
import exceptions.logica.SenhaIncorretaException;
import exceptions.logica.SistemaException;
import exceptions.logica.StringVaziaException;
import factory.funcionarios.FuncionarioFactory;
import validacao.dados.ValidaMatricula;
import validacao.dados.ValidaNome;
import validacao.dados.ValidaSenha;

public class GerenciadorFuncionarios implements Serializable {

	private static final long serialVersionUID = -1957084040534534334L;

	private static final String CHAVE_SISTEMA = "c041ebf8";
	
	private HashMap<String, Funcionario> funcionarios;
	private FuncionarioFactory funcionarioFactory;	
	
	private Funcionario usuarioLogado;
	
	public GerenciadorFuncionarios() {
		funcionarios = new HashMap<String, Funcionario>();
		funcionarioFactory = new FuncionarioFactory(); // test
	}
	
	public void initGerenciadorFuncionarios() {
		
	}
	
	public void fecharGerenciadorFuncionarios() throws LogicaException {
		if (isUsuarioLogado()) {
			throw new LogicaException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
		}
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
		if (BancoDeDados.getInstance().isSistemaLiberado()) {
			throw new LogicaException("Sistema liberado anteriormente.");
		}
		if (!chave.equals(CHAVE_SISTEMA)) {
			throw new ChaveIncorretaException("Chave invalida.");
		}
		BancoDeDados.getInstance().setSistemaLiberado(true);
		
		Diretor diretor = (Diretor) funcionarioFactory.criaFuncionario(nome, "Diretor Geral", dataNascimento);
		funcionarios.put(diretor.getMatricula(), diretor);

		return diretor.getMatricula();
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
		if (!BancoDeDados.getInstance().isSistemaLiberado()) {
			throw new SistemaException("Sistema nao liberado.");
		} else if (isUsuarioLogado()) {
			throw new LoginException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
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
		BancoDeDados.getInstance().setUsuarioLogado(usuarioLogado);
	}

	/**
	 * Realiza o logout de um usuário, e joga um erro caso não tenha usuários
	 * logados
	 * 
	 * @throws LogicaException
	 */
	public void logout() throws LogoutException {
		if (!isUsuarioLogado()) {
			throw new LogoutException("Nao ha um funcionario logado.");
		}
		usuarioLogado = null;
		BancoDeDados.getInstance().setUsuarioLogado(usuarioLogado);
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

		if (!BancoDeDados.getInstance().isSistemaLiberado()) {
			throw new SistemaException("O sistema esta bloqueado.");
		} else if (!isUsuarioLogado()) {
			throw new SistemaException("Usuario nao esta logado.");
		} else if (!usuarioLogado.temPermissao(PermissaoFuncionario.CRIACAO_USUARIOS)) {
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
		} else if (!ValidaMatricula.validar(matricula)) {
			throw new PadraoException("A matricula nao segue o padrao.");
		}

		Funcionario funcionario = getFuncionarioPorMatricula(matricula);

		String attr = null;

		switch (atributo) {

		case "Nome":
			attr = funcionario.getNome();
			break;
		case "Cargo":
			attr = funcionario.getCargo();
			break;
		case "Data":
			attr = funcionario.getData();
			break;
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
	
	/**
	 * Exclui um funcionário do sistema, necessitando do usuário logado dar
	 * uma confirmação da senha do diretor e possuir a permissão de excluir
	 * outros funcionarios 
	 * 
	 * @param matricula              Matrícula do funcionário a ser excluido
	 * @param senhaDiretor           Confirmação da senha do diretor
	 * @throws DadoInvalidoException Quando um dos dados for inválido
	 * @throws LogicaException       Quando o usuário não tem permissão de exclusão ou a senha
	 *                               do diretor estiver incorreta.
	 */
	public void excluiFuncionario(String matricula, String senhaDiretor) throws DadoInvalidoException, LogicaException {
		if (matricula == null) {
			throw new NullStringException("Matricula nao pode ser nulo.");
		} else if (senhaDiretor == null) {
			throw new NullStringException("Senha nao pode ser nula.");
		} else if (matricula.trim().isEmpty()) {
			throw new StringVaziaException("Matricula nao pode ser vazia.");
		} else if (senhaDiretor.trim().isEmpty()) {
			throw new StringVaziaException("Senha nao pode ser vazia.");
		} else if (!ValidaMatricula.validar(matricula)) {
			throw new PadraoException("A matricula nao segue o padrao.");
		}
		
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		
		if (!usuarioLogado.temPermissao(PermissaoFuncionario.EXCLUSAO_FUNCIONARIOS)) {
			throw new PermissaoException("O funcionario " + usuarioLogado.getNome() + " nao tem permissao para excluir funcionarios.");
		} else if (!getFuncionarioPorMatricula("12016001").getSenha().equals(senhaDiretor)) {
			throw new SenhaIncorretaException("Senha invalida.");
		}
		
		funcionarios.remove(funcionario.getMatricula());
	}
	
	/**
	 * Atualiza a informação de um funcionário que não é o usuário que
	 * está atualmente logado no sistema.
	 * 
	 * @param matricula              Matrícula do funcionário a ser alterado
	 * @param atributo               Atributo do funcionário a ser alterado
	 * @param novoValor              Novo valor para o atributo
	 * @throws DadoInvalidoException Quando um dos parâmetros for inválido
	 * @throws LogicaException       Quando houver um erro no padrão dos atributos
	 */
	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor) throws DadoInvalidoException, LogicaException {
		if (matricula == null) {
			throw new NullStringException("Matricula nao pode ser nulo.");
		} else if (matricula.trim().isEmpty()) {
			throw new StringVaziaException("Matricula nao pode ser vazia.");
		} else if (atributo == null) {
			throw new NullStringException("Atributo nao pode ser nulo.");
		} else if (novoValor == null) {
			throw new NullStringException("Novo valor nao pode ser nulo.");
		}
		
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		atualizaInfoFuncionario(funcionario, atributo, novoValor);
	}
	
	/**
	 * Atualiza a informação do usuário que está logado no sistema
	 * 
	 * @param atributo
	 * @param novoValor
	 * @throws DadoInvalidoException
	 * @throws LogicaException
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws DadoInvalidoException, LogicaException {
		atualizaInfoFuncionario(usuarioLogado, atributo, novoValor);
	}
	
	/**
	 * Atualiza a informação de um funcionário arbitrário
	 * 
	 * @param funcionario            Funcionário a ser atualizado
	 * @param atributo               Atributo a ser atualizado
	 * @param novoValor              Novo valor para o atributo
	 * @throws DadoInvalidoException Quando o novo valor for inválido (null ou vazio)
	 * @throws LogicaException       Quando o usuário logado não possuir permissão 
	 */
	public void atualizaInfoFuncionario(Funcionario funcionario, String atributo, String novoValor) throws DadoInvalidoException, LogicaException {
		if (atributo.equalsIgnoreCase("nome")) {
			if (novoValor.trim().isEmpty()) {
				throw new StringVaziaException("Nome do funcionario nao pode ser vazio.");
			} else if (!ValidaNome.validar(novoValor)) {
				throw new PadraoException("Nome invalido.");
			}
			funcionario.setNome(novoValor);
		} else if (atributo.equalsIgnoreCase("data")) {
			if (novoValor.trim().isEmpty()) {
				throw new StringVaziaException("Data de nascimento do funcionario nao pode ser vazia.");
			}
			funcionario.setData(FuncionarioFactory.parseData(novoValor));
		}
	}
	
	/**
	 * Atualiza a senha do usuário atualmente logado
	 * 
	 * @param antigaSenha            Senha antiga
	 * @param novaSenha              Nova senha
	 * @throws DadoInvalidoException Quando um dos parâmetros forem inválidos
	 * @throws LogicaException       Quando a senha antiga estiver incorreta ou a nova senha
	 *                               não seguir o padrão
	 */
	public void atualizaSenha(String antigaSenha, String novaSenha) throws DadoInvalidoException, LogicaException {
		if (antigaSenha == null) {
			throw new NullStringException("A senha antiga nao pode ser nulo.");
		} else if (antigaSenha.trim().isEmpty()) {
			throw new StringVaziaException("A senha antiga nao pode ser vazio.");
		} else if (novaSenha == null) {
			throw new NullStringException("A nova senha nao pode ser nulo.");
		} else if (novaSenha.trim().isEmpty()) {
			throw new StringVaziaException("A nova senha nao pode ser vazia.");
		}
		
		if (!usuarioLogado.getSenha().equals(antigaSenha)) {
			throw new SenhaIncorretaException("Senha invalida.");
		} else if (!ValidaSenha.validar(novaSenha)) {
			throw new PadraoException("A nova senha deve ter entre 8 - 12 caracteres alfanumericos.");
		}
		
		usuarioLogado.setSenha(novaSenha);
	}
}
