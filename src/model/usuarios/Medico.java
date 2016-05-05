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

}