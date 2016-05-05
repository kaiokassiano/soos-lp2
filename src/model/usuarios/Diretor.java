package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.*;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;

/**
 * Classe de diretor do sistema, possui todas as permiss�es
 */
public class Diretor extends Funcionario {
	
	/**
	 * Construtor de Diretor
	 * 
	 * @param nome           Nome do diretor
	 * @param matricula      Matr�cula do diretor
	 * @param dataNascimento Data de nascimento do diretor 
	 * @throws LogicaException 
	 * @throws DadoInvalidoException 
	 */
	public Diretor(String nome, String matricula, LocalDate dataNascimento) throws DadoInvalidoException, LogicaException {
		super(nome, matricula, dataNascimento);
	}
	
	/**
	 * Define todas as permiss�es que um funcion�rio com privil�gios de
	 * diretor possui
	 * 
	 * @return Conjunto com as permiss�es de Diretor
	 */
	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();

		permissoes.add(PermissaoFuncionario.criacaoUsuarios);
		permissoes.add(PermissaoFuncionario.criacaoPacientes);
		permissoes.add(PermissaoFuncionario.lerSenhas);

		return permissoes;
	}

	@Override
	public String getCargo() {
		return "Diretor Geral";
	}
}
