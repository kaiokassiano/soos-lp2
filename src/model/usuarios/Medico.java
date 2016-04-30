package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

public class Medico extends Funcionario {

	private static final int PREFIXO = 2;
	private String matricula;
	private String senha;
	
	public Medico(String nome, LocalDate data) {
		super(nome, data);
	}

	@Override
	public HashSet<PermissaoUsuario> definePermissoes() {
		HashSet<PermissaoUsuario> permissoes = new HashSet<PermissaoUsuario>();
		
		permissoes.add(PermissaoUsuario.leitura);
		permissoes.add(PermissaoUsuario.escrita);
		
		return permissoes;
	}

	@Override
	public int getPrefixo() {
		return PREFIXO;
	}

}