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

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de Medico
	 * 
	 * @param nome           Nome do m�dico
	 * @param matricula      Matr�cula do m�dico
	 * @param dataNascimento Data de nascimento do m�dico
	 */
	public Medico(String nome, String matricula, LocalDate dataNascimento) {
		super(nome, matricula, dataNascimento);
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