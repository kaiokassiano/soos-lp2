package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

public class Diretor implements Usuario {
	
	private final static int PREFIXO = 1;
	private static Diretor instance = null;
	
	private HashSet<PermissaoUsuario> permissoes;
	private String matricula;
	private String senha;
	
	private Diretor(String senha) {
		this.senha = senha;
		gerarMatricula();
		
		permissoes = definePermissoes();
	}
	
	public Diretor getInstance() {
		if (instance == null) {
			instance = new Diretor("c041ebf8");
		}
		return instance;
	}
	
	@Override
	public HashSet<PermissaoUsuario> definePermissoes() {
		HashSet<PermissaoUsuario> permissoes = new HashSet<PermissaoUsuario>();

		permissoes.add(PermissaoUsuario.criacaoUsuarios);
		permissoes.add(PermissaoUsuario.leitura);
		permissoes.add(PermissaoUsuario.escrita);

		return permissoes;
	}
	
	@Override
	public int getPrefixo() {
		return PREFIXO;
	}

	@Override
	public String getMatricula() {
		return matricula;
	}

	@Override
	public String getSenha() {
		return senha;
	}
	
	private void gerarMatricula() {
		this.matricula = getPrefixo() + LocalDate.now().getYear() + "001";
	}
	
	@Override
	public boolean temPermissao(PermissaoUsuario permissao) {
		return permissoes.contains(permissao);
	}

}
