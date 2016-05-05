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
<<<<<<< HEAD
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int PREFIXO = 1;
	
	public Diretor(String nome, LocalDate data) throws DadoInvalidoException, LogicaException {
		super(nome, data);
=======
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
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
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
<<<<<<< HEAD
	
	@Override
	public String toString() {
		return getMatricula() + " - " + getNome();
	}
	
	@Override
	public boolean equals(Object obj){
	  if (this == obj)
	    return true;
	  if (obj == null)
	    return false;
	  if (getClass() != obj.getClass())
	    return false;
	  return true;
	}
=======
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
}
