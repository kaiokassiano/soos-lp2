package model.prontuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exceptions.logica.DataInvalidaException;
import factory.pacientes.PacienteFactory;

public class Prontuario implements Serializable {

	private static final long serialVersionUID = 5588910044733686821L;

	private Paciente paciente;
	private PacienteFactory pacienteFactory;
	private List<String> procedimentos; // TODO refatorar
	private List<String> tratamentos; // TODO refatorar

	public Prontuario(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws DataInvalidaException {
		this.procedimentos = new ArrayList<>();
		this.tratamentos = new ArrayList<>();
		this.pacienteFactory = new PacienteFactory();

		// TODO valida��o prontuario
		this.paciente = pacienteFactory.criaPaciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
	}

	public String getNome() {
		return paciente.getNome();
	}

	public String getInfoPaciente(String nome, String atributo) {

		String saida = "";
		
		switch (atributo.toLowerCase().trim()) {
		case "nome":
			return saida = this.paciente.getNome();
		case "data":
			return saida = this.paciente.getDataNascimento().toString();
		case "sexo":
			return saida = this.paciente.getSexoBiologico();
		case "genero":
			return saida = this.paciente.getGenero();
		case "tiposanguineo":
			return saida = this.paciente.getTipoSanguineo();
		case "peso":
			return saida = this.paciente.getPeso() + "";
		case "idade":
			return saida = this.paciente.getIdadePaciente() + "";	
			
		}

		 return saida;
	}
	
}
