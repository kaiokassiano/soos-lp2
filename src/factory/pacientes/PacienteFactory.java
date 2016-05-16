package factory.pacientes;

import java.io.Serializable;

import exceptions.logica.DataInvalidaException;
import model.prontuarios.Paciente;

public class PacienteFactory implements Serializable {

	private static final long serialVersionUID = -8738794875196797342L;

	public Paciente criaPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws DataInvalidaException {
		Paciente paciente = new Paciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);

		return paciente;
	}
}
