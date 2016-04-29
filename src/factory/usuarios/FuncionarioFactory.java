package factory.usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.usuarios.*;

public class FuncionarioFactory {
	
	public Funcionario criaFuncionario(String nome, String data, TipoFuncionario tipo) /* throws TipoInvalidoException */ {
		Funcionario funcionario = null;
		
		if (tipo.equals(TipoFuncionario.Diretor)) {
			funcionario = new Diretor(nome, parseData(data));
		}
		else if (tipo.equals(TipoFuncionario.Medico)) {
			funcionario = new Medico(nome, parseData(data));
		}
		else if (tipo.equals(TipoFuncionario.TecnicoAdministrativo)) {
			funcionario = new TecnicoAdministrativo(nome, parseData(data));
		}
		else {
			// throw new TipoInvalidoException();
		}
		
		return funcionario;
	}
	
	private LocalDate parseData(String data) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/yyyy");
		return LocalDate.parse(data, formato);
	}
}
