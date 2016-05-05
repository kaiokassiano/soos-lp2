package factory.funcionarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import data.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.CargoInvalidoException;
import exceptions.logica.DataInvalidaException;
import exceptions.logica.LogicaException;
import exceptions.logica.StringVaziaException;
import model.usuarios.Diretor;
import model.usuarios.Funcionario;
import model.usuarios.Medico;
import model.usuarios.TecnicoAdministrativo;


/**
 * Factory de funcionários
 */
public class FuncionarioFactory {
	
	/**
	 * Cria um funcionário dado suas informações
	 * 
	 * @param nome           Nome do funcionário
	 * @param cargo          Cargo do funcionário
	 * @param dataNascimento Data de nascimento do funcionário
	 * @return               Instância do objeto Funcionario
	 * @throws DadoInvalidoException 
	 * @throws LogicaException 
	 */
	public Funcionario criaFuncionario(String nome, String cargo, String dataNascimento) throws DadoInvalidoException, LogicaException {
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
	 * Gera uma matrícula para um funcionário com o prefixo dado
	 * 
	 * @param prefixo Prefixo do funcionário
	 * @return        Matrícula do funcionário
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
	 * @throws DataInvalidaException 
	 */
	private LocalDate parseData(String dataNascimento) throws DataInvalidaException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			return LocalDate.parse(dataNascimento, formato);
		}
		catch (Exception e) {
			throw new DataInvalidaException("Data invalida.");
		}
	}
}