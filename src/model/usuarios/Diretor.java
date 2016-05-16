package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.*;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import exceptions.logica.StringVaziaException;

/**
 * Classe de diretor do sistema, possui todas as permissões
 */
public class Diretor extends Funcionario {
	
	private static final long serialVersionUID = 6991832192337349566L;

	/**
	 * Construtor de Diretor
	 * 
	 * @param nome           Nome do diretor
	 * @param matricula      Matrícula do diretor
	 * @param dataNascimento Data de nascimento do diretor 
	 * @throws DadoInvalidoException 
	 * @throws StringVaziaException 
	 */
	public Diretor(String nome, String matricula, LocalDate dataNascimento) throws LogicaException, DadoInvalidoException {
		super(nome, matricula, dataNascimento);
	}

	/**
	 * Define todas as permissões que um funcionário com privilégios de
	 * diretor possui
	 * 
	 * @return Conjunto com as permissões de Diretor
	 */
	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();

		permissoes.add(PermissaoFuncionario.CRIACAO_USUARIOS);
		permissoes.add(PermissaoFuncionario.CRIACAO_PACIENTES);
		permissoes.add(PermissaoFuncionario.LER_SENHAS);
		permissoes.add(PermissaoFuncionario.EXCLUSAO_FUNCIONARIOS);

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
