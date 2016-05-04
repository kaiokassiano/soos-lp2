package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;

/**
 * Classe de médico do sistema, possui todas as permissões relacionadas
 * ao corpo clínico
 */
public class Medico extends Funcionario {

	/**
	 * Construtor de Medico
	 * 
	 * @param nome           Nome do médico
	 * @param matricula      Matrícula do médico
	 * @param dataNascimento Data de nascimento do médico
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

}