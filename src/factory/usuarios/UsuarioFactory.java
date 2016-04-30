package factory.usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.usuarios.*;

public class UsuarioFactory {
	
	public Usuario criaFuncionario(String nome, String data, TipoUsuario tipo) /* throws TipoInvalidoException */ {
		Usuario usuario = null;
		
		if (tipo.equals(TipoUsuario.Medico)) {
			usuario = new Medico(nome, parseData(data));
		}
		else if (tipo.equals(TipoUsuario.TecnicoAdministrativo)) {
			usuario = new TecnicoAdministrativo(nome, parseData(data));
		}
		else {
			// throw new TipoInvalidoException();
		}
		
		return usuario;
	}
	
	private LocalDate parseData(String data) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/yyyy");
		return LocalDate.parse(data, formato);
	}
}
