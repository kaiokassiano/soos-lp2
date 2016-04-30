package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

public abstract class Funcionario implements Usuario {

	private String nome;
	private HashSet<PermissaoUsuario> permissoes;
	private LocalDate data;
	private String matricula;
	private String senha;

	public Funcionario(String nome, LocalDate data) /* throws Exception */ {
		if (nome == null) {
			// throw new Exception();
		}
		if (data == null) {
			// throw new Exception();
		}
		
		this.permissoes = definePermissoes(); // chamada polimorfica
		gerarMatricula(); // chamada polimorfica
		this.nome = nome;
		this.data = data;
	}

	@Override
	public boolean temPermissao(PermissaoUsuario permissao) {
		return permissoes.contains(permissao);
	}
	
	private void gerarMatricula() {
		this.matricula = getPrefixo() + LocalDate.now().getYear() + "001";
	}
	
	@Override
	public String getMatricula() {
		return matricula;
	}
	
	@Override
	public String getSenha() {
		return senha;
	}
}
