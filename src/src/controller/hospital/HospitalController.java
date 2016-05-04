package controller.hospital;

import java.util.HashMap;
import data.BancoDeDados;
import exceptions.dado.*;
import exceptions.logica.*;
import factory.funcionarios.*;
import factory.medicamentos.*;
import model.usuarios.*;
import valida.dados.*;

public class HospitalController {

	private static final String chaveSistema = "c041ebf8";
	
	private FuncionarioFactory funcionarioFactory;
	private MedicamentoFactory medicamentoFactory;
	
	private Funcionario usuarioLogado;
	private HashMap<String, Funcionario> funcionarios;
	private BancoDeDados bancoDeDados;
	
	private boolean sistemaLiberado;
	
	public HospitalController() {
		funcionarioFactory = new FuncionarioFactory();
		medicamentoFactory = new MedicamentoFactory();
		
		funcionarios = new HashMap<String, Funcionario>();
		
		bancoDeDados = BancoDeDados.getInstance();
	}
	
	public void iniciaSistema() {
		bancoDeDados.init();
	}
	
	public void fechaSistema() throws LogicaException {
		if (isUsuarioLogado()) {
			throw new LogicaException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
		}
		bancoDeDados.fechar();
	}
	
	public String liberaSistema(String chave, String nome, String dataNascimento) throws DadoInvalidoException, LogicaException {
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
	
	public boolean isUsuarioLogado() {
		return usuarioLogado != null;
	}
	
	public void login(String matricula, String senha) throws DadoInvalidoException, LogicaException {
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
	
	public void logout() throws LogicaException {
		if (!isUsuarioLogado()) {
			throw new LogicaException("Nao ha um funcionario logado.");
		}
		usuarioLogado = null;
	}
	
	//acha fncionario
	public Funcionario getFuncionarioPorMatricula(String matricula) throws DadoInvalidoException, ObjetoInexistenteException {
		ValidaMatricula validaMatricula = new ValidaMatricula();
		Funcionario funcionario = funcionarios.get(matricula);
		
		if(!validaMatricula.validaMatricula(matricula).find()){
			throw new DadoInvalidoException("Erro na consulta de funcionario. A matricula nao segue o padrao.");
		}
		if (funcionario == null) {
			throw new ObjetoInexistenteException("Funcionario nao cadastrado.");
		}
		
		return funcionario;
	}
	
	//valida operacao de atualizacao de dados
	public boolean validaOperacao(String matricula){
		if (usuarioLogado instanceof Diretor){
			return true;								//se o usuario for diretor pode atualizar 
		}												//qualquer coisa
		else{
			if (matricula.equals(usuarioLogado.getMatricula())) return true;
			return false;
		}
	}
	
	//atualizacao de nomes
	public void atualizaNome(String matricula, String newNome) throws DadoInvalidoException, ObjetoInexistenteException,
				PermissaoException{
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		ValidaNomes validaNome = new ValidaNomes();
		
		if(validaOperacao(matricula)){
			if(!validaNome.validaNovoNome(newNome).find()){
				throw new DadoInvalidoException("Erro ao atualizar funcionario. Nome do funcionario nao pode ser vazio.");
			}
			funcionario.setNome(newNome);
		}
		throw new PermissaoException("Voce nao possui permissao para concluir a acao.");
	}
	
	//atualizacao de senhas
	public void atualizaSenha(String matricula, String senha, String newSenha)throws DadoInvalidoException, ObjetoInexistenteException,
				SenhaIncorretaException, PermissaoException{
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		ValidaSenhas validaSenha = new ValidaSenhas(newSenha);
		
		if(!senha.equals(funcionario.getSenha())){
			throw new SenhaIncorretaException("Erro ao atualizar funcionario. Senha invalida.");
		}
		
		if(validaOperacao(matricula)){
			if(!validaSenha.validaNovaSenha(newSenha).find()){
				throw new DadoInvalidoException("Erro ao atualizar funcionario. A nova senha deve ter entre 8 - 12 caracteres alfanumericos." );
			}
			funcionario.setSenha(newSenha);
		}
		
		throw new PermissaoException("Voce nao possui permissao para concluir a acao.");
	}
	
	//atualiza data de nascimento
	public void atualizaDataNascimento(String matricula, String newDataNascimento) throws DadoInvalidoException, ObjetoInexistenteException,
				PermissaoException{
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		
		if(!validaOperacao(matricula)){
			throw new PermissaoException("Voce nao possui permissao para concluir a acao.");
		}
		
		
		
	}
	
	//remove usuarios
	public boolean removeUsuario(String matricula, String senha)throws PermissaoException, DadoInvalidoException, ObjetoInexistenteException,
					SenhaIncorretaException{
		
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		
		if(!validaOperacao(usuarioLogado.getMatricula())){
			throw new PermissaoException("Voce nao possui permissao para concluir a acao.");
		}
		
		if(!(usuarioLogado instanceof Diretor)){
			throw new PermissaoException("Voce nao possui permissao para concluir a acoa.");
		}
		
		//if(){
			
		//}
		
		
		return true;
	}
	
	
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) throws LogicaException, DadoInvalidoException {
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
	
	public String getInfoFuncionario(String matricula, String atributo) throws DadoInvalidoException, LogicaException {
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
