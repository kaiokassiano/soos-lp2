package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

public class TecnicoAdministrativo extends Funcionario {

	public TecnicoAdministrativo(String nome, LocalDate data) {
		super(nome, data);
	}

	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();
		
		permissoes.add(PermissaoFuncionario.leitura);
		permissoes.add(PermissaoFuncionario.escrita);
		
		return permissoes;
	}

}
