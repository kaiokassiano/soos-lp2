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
<<<<<<< HEAD
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PREFIXO = 3;
	
	public TecnicoAdministrativo(String nome, LocalDate data) throws DadoInvalidoException, LogicaException {
		super(nome, data);
=======
	 * Construtor de TecnicoAdministrativo
	 * 
	 * @param nome           Nome do técnico administrativo
	 * @param matricula      Matrícula do técnico administrativo
	 * @param dataNascimento Data de nascimento do técnico administrativo
	 */
	public TecnicoAdministrativo(String nome, String matricula, LocalDate dataNascimento) {
		super(nome, matricula, dataNascimento);
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
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
