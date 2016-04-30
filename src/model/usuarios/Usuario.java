package model.usuarios;

import java.util.HashSet;

public interface Usuario {

	HashSet<PermissaoUsuario> definePermissoes();
	
	boolean temPermissao(PermissaoUsuario permissao);
	
	String getMatricula();
	
	String getSenha();
	
	int getPrefixo();
}
