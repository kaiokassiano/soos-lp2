package factory.pacientes;

import exceptions.logica.DataInvalidaException;
import model.prontuarios.Paciente;

public class PacienteFactory {

	public Paciente criaPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws DataInvalidaException {
		Paciente paciente = new Paciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);

		return paciente;
	}
}
