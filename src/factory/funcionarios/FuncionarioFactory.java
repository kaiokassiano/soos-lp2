package factory.funcionarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import data.BancoDeDados;
import exceptions.dado.*;
import exceptions.logica.*;
import model.usuarios.Diretor;
import model.usuarios.Funcionario;
import model.usuarios.Medico;
import model.usuarios.TecnicoAdministrativo;

/**
<<<<<<< HEAD
<<<<<<< HEAD
 * Factory de funcionários
=======
 * Factory de funcion�rios
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
=======
 * Factory de funcionários
>>>>>>> 817f1631a7bcd94c25a99cd211a62dd9beb30bad
 */
public class FuncionarioFactory {
	
	/**
<<<<<<< HEAD
<<<<<<< HEAD
	 * Cria um funcionário dado suas informações
	 * 
	 * @param nome           Nome do funcionário
	 * @param cargo          Cargo do funcionário
	 * @param dataNascimento Data de nascimento do funcionário
	 * @return               Instância do objeto Funcionario
=======
	 * Cria um funcion�rio dado suas informa��es
	 * 
	 * @param nome           Nome do funcion�rio
	 * @param cargo          Cargo do funcion�rio
	 * @param dataNascimento Data de nascimento do funcion�rio
	 * @return               Inst�ncia do objeto Funcionario
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
=======
	 * Cria um funcionário dado suas informações
	 * 
	 * @param nome           Nome do funcionário
	 * @param cargo          Cargo do funcionário
	 * @param dataNascimento Data de nascimento do funcionário
	 * @return               Instância do objeto Funcionario
>>>>>>> 817f1631a7bcd94c25a99cd211a62dd9beb30bad
	 */
	public Funcionario criaFuncionario(String nome, String cargo, String dataNascimento) {
		Funcionario funcionario = null;
		
		LocalDate data = parseData(dataNascimento);
		
		if (cargo.equalsIgnoreCase("Diretor Geral")) {
			funcionario = new Diretor(nome, geraMatriculaFuncionario(PrefixoFuncionario.Diretor), data);
		}
		else if (cargo.equalsIgnoreCase("Medico")) {
			funcionario = new Medico(nome, geraMatriculaFuncionario(PrefixoFuncionario.Medico), data);
		}
		else if (cargo.equalsIgnoreCase("Tecnico Administrativo")) {
			funcionario = new TecnicoAdministrativo(nome, geraMatriculaFuncionario(PrefixoFuncionario.TecnicoAdministrativo), data);
		}
		else {
			throw new CargoInvalidoException("Cargo invalido.");
		}
		
		return funcionario;
	}
	
	/**
<<<<<<< HEAD
<<<<<<< HEAD
	 * Gera uma matrícula para um funcionário com o prefixo dado
	 * 
	 * @param prefixo Prefixo do funcionário
	 * @return        Matrícula do funcionário
=======
	 * Gera uma matr�cula para um funcion�rio com o prefixo dado
	 * 
	 * @param prefixo Prefixo do funcion�rio
	 * @return        Matr�cula do funcion�rio
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
=======
	 * Gera uma matrícula para um funcionário com o prefixo dado
	 * 
	 * @param prefixo Prefixo do funcionário
	 * @return        Matrícula do funcionário
>>>>>>> 817f1631a7bcd94c25a99cd211a62dd9beb30bad
	 */
	private String geraMatriculaFuncionario(PrefixoFuncionario prefixo) {
		return prefixo.getPrefixo() + LocalDate.now().getYear() + String.format("%03d", BancoDeDados.getInstance().getProximoId());
	}
	
	/**
	 * Faz o parse de uma string que representa a data de nascimento
	 * no formato dd/MM/yyyy
	 * 
	 * @param dataNascimento Data a ser feita o parsing
	 * @return               Data como um objeto de LocalDate
	 */
	private LocalDate parseData(String dataNascimento) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			return LocalDate.parse(dataNascimento, formato);
		}
		catch (Exception e) {
			throw new DataInvalidaException("Data invalida.");
		}
	}
}
