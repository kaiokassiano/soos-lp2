package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

public class TecnicoAdministrativo extends Funcionario {

	private static final int PREFIXO = 3;
	private String matricula;
	private String senha;
	
	public TecnicoAdministrativo(String nome, LocalDate data) {
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
