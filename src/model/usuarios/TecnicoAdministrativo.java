package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;

/**
 * Classe de t�cnico administrativo do sistema, possui
 * todas as permiss�es relacionadas ao corpo profissional
 */
public class TecnicoAdministrativo extends Funcionario {

	/**
	 * Construtor de TecnicoAdministrativo
	 * 
	 * @param nome           Nome do t�cnico administrativo
	 * @param matricula      Matr�cula do t�cnico administrativo
	 * @param dataNascimento Data de nascimento do t�cnico administrativo
	 * @throws LogicaException 
	 * @throws DadoInvalidoException 
	 */
	public TecnicoAdministrativo(String nome, String matricula, LocalDate dataNascimento) throws DadoInvalidoException, LogicaException {
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
