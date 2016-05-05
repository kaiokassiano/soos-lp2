package factory.funcionarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.dado.*;
import exceptions.logica.*;
import model.usuarios.Diretor;
import model.usuarios.Funcionario;
import model.usuarios.Medico;
import model.usuarios.TecnicoAdministrativo;

public class FuncionarioFactory {
	
	public Funcionario criaFuncionario(String nome, String cargo, String dataNascimento) throws DadoInvalidoException, LogicaException {
		Funcionario funcionario = null;
		
		if (cargo == null) {
			throw new NullStringException("Cargo nao pode ser nulo.");
		}
		else if (cargo.trim().isEmpty()) {
			throw new StringVaziaException("Cargo nao pode ser vazio.");
		}
		else if (cargo.equalsIgnoreCase("Diretor Geral")) {
			funcionario = new Diretor(nome, parseData(dataNascimento));
		}
		else if (cargo.equalsIgnoreCase("Medico")) {
			funcionario = new Medico(nome, parseData(dataNascimento));
		}
		else if (cargo.equalsIgnoreCase("Tecnico Administrativo")) {
			funcionario = new TecnicoAdministrativo(nome, parseData(dataNascimento));
		}
		else {
			throw new CargoInvalidoException("Cargo invalido.");
		}
		
		return funcionario;
	}
	
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
