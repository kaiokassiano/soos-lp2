package factory.pacientes;

import java.io.Serializable;

import exceptions.logica.DataInvalidaException;
import model.prontuarios.Paciente;

/**
 * Factory de pacientes
 */
public class PacienteFactory implements Serializable {

	private static final long serialVersionUID = -8738794875196797342L;

	/**
	 * Cria um paciente de acordo com as informacoes recebidas
	 * 
	 * @param nome
	 *            - nome do paciente
	 * @param dataNascimento
	 *            - data de nascimento seguindo o padrao dd/MM/yyyy
	 * @param peso
	 *            - peso do paciente
	 * @param sexoBiologico
	 *            - sexo biologico do paciente
	 * @param genero
	 *            - genero do paciente
	 * @param tipoSanguineo
	 *            - tipo sanguineo do paciente
	 * @return Instancia do paciente criado
	 * @throws DataInvalidaException
	 */
	public Paciente criaPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws DataInvalidaException {
		Paciente paciente = new Paciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);

		return paciente;
	}
}
