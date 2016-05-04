package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;

public class TecnicoAdministrativo extends Funcionario {

	private static final int PREFIXO = 3;
	
	public TecnicoAdministrativo(String nome, LocalDate data) throws DadoInvalidoException, LogicaException {
		super(nome, data);
	}

	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();
		
		permissoes.add(PermissaoFuncionario.criacaoPacientes);
		
		return permissoes;
	}

	@Override
	public int getPrefixo() {
		return PREFIXO;
	}

	@Override
	public String getCargo() {
		return "Tecnico Administrativo";
	}

}
