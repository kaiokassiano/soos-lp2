package model.usuarios;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.HashSet;

import data.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import exceptions.logica.StringVaziaException;

public abstract class Funcionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private HashSet<PermissaoFuncionario> permissoes;
	private LocalDate dataNascimento;
	private static String matricula;
	private String senha;

	public Funcionario(String nome, LocalDate dataNascimento) throws DadoInvalidoException, LogicaException {
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
		gerarMatricula(); // chamada polimorfica
		gerarSenha();
	}

	public abstract HashSet<PermissaoFuncionario> definePermissoes();
	
	public boolean temPermissao(PermissaoFuncionario permissao) throws DadoInvalidoException {
		if (permissao == null) {
			throw new DadoInvalidoException("Permissao nao pode ser nulo.");
		}
		return permissoes.contains(permissao);
	}
	
	public abstract int getPrefixo();
	
	private void gerarMatricula() {
		Funcionario.matricula = getPrefixo() + (LocalDate.now().getYear() + String.format("%03d", BancoDeDados.getInstance().getProximoId()));
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setNome(String nome) throws NullStringException {
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo.");
		}
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
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
	
	public abstract String getCargo();
}
