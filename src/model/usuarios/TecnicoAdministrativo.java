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

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor de TecnicoAdministrativo
	 * 
	 * @param nome           Nome do t�cnico administrativo
	 * @param matricula      Matr�cula do t�cnico administrativo
	 * @param dataNascimento Data de nascimento do t�cnico administrativo
	 */
	public TecnicoAdministrativo(String nome, String matricula, LocalDate data) throws DadoInvalidoException, LogicaException {
		super(nome, matricula, data);
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
