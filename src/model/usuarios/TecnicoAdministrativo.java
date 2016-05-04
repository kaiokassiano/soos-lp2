package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;

/**
 * Classe de técnico administrativo do sistema, possui
 * todas as permissões relacionadas ao corpo profissional
 */
public class TecnicoAdministrativo extends Funcionario {

	/**
	 * Construtor de TecnicoAdministrativo
	 * 
	 * @param nome           Nome do técnico administrativo
	 * @param matricula      Matrícula do técnico administrativo
	 * @param dataNascimento Data de nascimento do técnico administrativo
	 */
	public TecnicoAdministrativo(String nome, String matricula, LocalDate dataNascimento) {
		super(nome, matricula, dataNascimento);
	}

	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();
		
		permissoes.add(PermissaoFuncionario.criacaoPacientes);
		
		return permissoes;
	}

	@Override
	public String getCargo() {
		return "Tecnico Administrativo";
	}

}
