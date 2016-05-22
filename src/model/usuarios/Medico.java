package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import exceptions.logica.StringVaziaException;

/**
 * Classe de médico do sistema, possui todas as permissões relacionadas
 * ao corpo clínico
 */
public class Medico extends Funcionario {

	private static final long serialVersionUID = 896465749455349591L;

	/**
	 * Construtor de Medico
	 * 
	 * @param nome           Nome do médico
	 * @param matricula      Matrícula do médico
	 * @param dataNascimento Data de nascimento do médico
	 * @throws DadoInvalidoException 
	 * @throws StringVaziaException 
	 */
	public Medico(String nome, String matricula, LocalDate dataNascimento) throws LogicaException, DadoInvalidoException {
		super(nome, matricula, dataNascimento);
	}

	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();
		
		permissoes.add(PermissaoFuncionario.CADASTRO_ORGAOS);
		permissoes.add(PermissaoFuncionario.CRIACAO_PROCEDIMENTOS);
		
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