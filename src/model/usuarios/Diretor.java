package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.*;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;

/**
 * Classe de diretor do sistema, possui todas as permissões
 */
public class Diretor extends Funcionario {
	
	/**
	 * Construtor de Diretor
	 * 
	 * @param nome           Nome do diretor
	 * @param matricula      Matrícula do diretor
	 * @param dataNascimento Data de nascimento do diretor 
	 */
	public Diretor(String nome, String matricula, LocalDate dataNascimento) {
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
