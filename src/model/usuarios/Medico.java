package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;

/**
 * Classe de m�dico do sistema, possui todas as permiss�es relacionadas
 * ao corpo cl�nico
 */
public class Medico extends Funcionario {

	/**
<<<<<<< HEAD
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PREFIXO = 2;

	public Medico(String nome, LocalDate data) throws DadoInvalidoException, LogicaException {
		super(nome, data);
=======
	 * Construtor de Medico
	 * 
	 * @param nome           Nome do m�dico
	 * @param matricula      Matr�cula do m�dico
	 * @param dataNascimento Data de nascimento do m�dico
	 * @throws LogicaException 
	 * @throws DadoInvalidoException 
	 */
	public Medico(String nome, String matricula, LocalDate dataNascimento) throws DadoInvalidoException, LogicaException {
		super(nome, matricula, dataNascimento);
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
	}

	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();
		
		return permissoes;
	}

	@Override
	public String getCargo() {
		return "Medico";
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

}