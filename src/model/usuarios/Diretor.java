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

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor de Diretor
	 * 
	 * @param nome           Nome do diretor
	 * @param matricula      Matr�cula do diretor
	 * @param dataNascimento Data de nascimento do diretor 
	 */
	public Diretor(String nome, String matricula, LocalDate dataNascimento) {
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
	
	@Override
	public String toString() {
		return getMatricula() + " - " + getNome();
	}
	
	@Override
	public boolean equals(Object obj) {
	  if (this == obj)
	    return true;
	  if (obj == null)
	    return false;
	  if (getClass() != obj.getClass())
	    return false;
	  return true;
	}
}
