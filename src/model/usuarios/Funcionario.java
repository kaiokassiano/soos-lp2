package model.usuarios;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.HashSet;

import data.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import exceptions.logica.StringVaziaException;

/**
 * Model que representa todos os funcion�rios do sistema
 */
public abstract class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private HashSet<PermissaoFuncionario> permissoes;
	private LocalDate dataNascimento;
	private String matricula;
	private String senha;

	/**
	 * Construtor de um objeto do tipo funcion�rio
	 * 
	 * @param nome           Nome do funcion�rio
	 * @param matricula      Matr�cula do funcion�rio
	 * @param dataNascimento Data de nascimento do funcion�rio
	 */
	public Funcionario(String nome, String matricula, LocalDate dataNascimento) {
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo");
		}
		else if (nome.trim().isEmpty()) {
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
	 * Define as permiss�es que o funcion�rio atual possui
	 * 
	 * @return Conjunto contendo todas as permiss�es que o usu�rio possui
	 */
	public abstract HashSet<PermissaoFuncionario> definePermissoes();
	
	/**
	 * Verifica se o usu�rio possui uma determinada permiss�o
	 * 
	 * @param permissao Permiss�o a ser checada
	 * @return          Boleano indicando se o usu�rio possui a permiss�o
	 */
	public boolean temPermissao(PermissaoFuncionario permissao) {
		if (permissao == null) {
			throw new DadoInvalidoException("Permissao nao pode ser nulo.");
		}
		return permissoes.contains(permissao);
	}

	public String getMatricula() {
		return matricula;
	}
	
	public void setNome(String nome) {
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo.");
		}
		else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome nao pode ser vazio.");
		}
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	/**
	 * Gera uma senha autom�tica para o usu�rio, seguindo o padr�o de
	 * que os 4 primeiros digitos correspondem ao ano de nascimento do
	 * usu�rio, e os 4 �ltimos correspondem aos 4 primeiros digitos de sua
	 * matr�cula
	 */
	private void gerarSenha() {
		this.senha = dataNascimento.getYear() + getMatricula().substring(0, 4);
	}
	
	public void setSenha(String senha) throws NullStringException {
		if (senha == null) {
			throw new NullStringException("Senha nao pode ser nula.");
		}
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getDataNascimento() {
		return dataNascimento.toString();
	}
	
	/**
	 * Retorna a representa��o do cargo do funcion�rio em forma de String
	 * 
	 * @return Cargo do funcion�rio
	 */
	public abstract String getCargo();
}
