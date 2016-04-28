package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

public abstract class Funcionario {

	private String nome;
	private HashSet<PermissaoFuncionario> permissoes;
	private LocalDate data;
	private String matricula;
	private String senha;

	public Funcionario(String nome, LocalDate data) {
		this.permissoes = definePermissoes(); // chamada polimorfica
		this.nome = nome;
		this.data = data;
	}

	public abstract HashSet<PermissaoFuncionario> definePermissoes();

	public boolean temPermissao(PermissaoFuncionario permissao) {
		return permissoes.contains(permissao);
	}
}
