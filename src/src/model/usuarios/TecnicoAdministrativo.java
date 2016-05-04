package model.usuarios;

import java.time.LocalDate;
import java.util.HashSet;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;

public class TecnicoAdministrativo extends Funcionario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PREFIXO = 3;

	public TecnicoAdministrativo(String nome, LocalDate data) throws DadoInvalidoException, LogicaException {
		super(nome, data);
	}

	@Override
	public HashSet<PermissaoFuncionario> definePermissoes() {
		HashSet<PermissaoFuncionario> permissoes = new HashSet<PermissaoFuncionario>();
		
		permissoes.add(PermissaoFuncionario.criacaoPacientes);
		
		return permissoes;
	}

	@Override
	public int getPrefixo() {
		return PREFIXO;
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
