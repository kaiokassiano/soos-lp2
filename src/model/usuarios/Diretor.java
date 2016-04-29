package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

public class Diretor extends Funcionario {
	
	public Diretor(String nome, LocalDate data) {
		super(nome, data);
	}
	
	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();

		permissoes.add(PermissaoFuncionario.criacaoUsuarios);
		permissoes.add(PermissaoFuncionario.leitura);
		permissoes.add(PermissaoFuncionario.escrita);

		return permissoes;
	}

}
