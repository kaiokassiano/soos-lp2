package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.*;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;

public class Diretor extends Funcionario {
	
	private final static int PREFIXO = 1;
	
	public Diretor(String nome, LocalDate data) throws DadoInvalidoException, LogicaException {
		super(nome, data);
	}
	
	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();

		permissoes.add(PermissaoFuncionario.criacaoUsuarios);
		permissoes.add(PermissaoFuncionario.criacaoPacientes);
		permissoes.add(PermissaoFuncionario.lerSenhas);

		return permissoes;
	}
	
	@Override
	public int getPrefixo() {
		return PREFIXO;
	}

	@Override
	public String getCargo() {
		return "Diretor Geral";
	}

	public String toString() {
		return getMatricula() + " - " + getNome();
	}
}
