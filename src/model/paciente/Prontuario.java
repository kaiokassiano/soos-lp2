package model.paciente;

import java.util.ArrayList;
import java.util.List;

public class Prontuario {
	
	private Paciente paciente;
	private List<String> tratamentos =  new ArrayList();
	private List<String> procedimentos = new ArrayList();
	
	public Prontuario(Paciente paciente)throws Exception{ // tratamentos e procedimentos serao adicionados apos a criacao do prontuario
		this.paciente = paciente;
	}
	
	public Prontuario(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws Exception { // tratamentos e procedimentos serao adicionados apos a criacao do prontuario
		
		this.paciente = new Paciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
		
	}

	public Paciente getPaciente() {
		return this.paciente;
	}
}