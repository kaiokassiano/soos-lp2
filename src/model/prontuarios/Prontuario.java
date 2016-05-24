package model.prontuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exceptions.logica.DataInvalidaException;
import factory.pacientes.PacienteFactory;
import model.procedimentos.Procedimento;

public class Prontuario implements Serializable {

	private static final long serialVersionUID = 5588910044733686821L;

	private Paciente paciente;
	private PacienteFactory pacienteFactory;
	private List<Procedimento> procedimentos;

	public Prontuario(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws DataInvalidaException {
		this.procedimentos = new ArrayList<>();
		this.pacienteFactory = new PacienteFactory();

		this.paciente = pacienteFactory.criaPaciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
	}

	public String getNome() {
		return paciente.getNome();
	}

	public String getInfoPaciente(String atributo) {

		String saida = null;
		
		switch (atributo.toLowerCase().trim()) {
		case "nome":
			saida = this.paciente.getNome();
			break;
		case "data":
			saida = this.paciente.getDataNascimento().toString();
			break;
		case "sexo":
			saida = this.paciente.getSexoBiologico();
			break;
		case "genero":
			saida = this.paciente.getGenero();
			break;
		case "tiposanguineo":
			saida = this.paciente.getTipoSanguineo().toString();
			break;
		case "peso":
			saida = this.paciente.getPeso() + "";
			break;
		case "idade":
			saida = this.paciente.getIdadePaciente() + "";
			break;
		case "id":
			saida = this.paciente.getId().toString();
		}

		 return saida;
	}
	
	public void atualizaInfoPaciente(String valor, String atributo) {
		
		double valorDouble = Double.parseDouble(valor);

		switch (atributo.toLowerCase().trim()) {
			
			case "gastos":
				paciente.adicionaGastos(valorDouble);
				break;
			case "peso":
				paciente.setPeso(valorDouble);	
				break;
			case "pontos":
				int valorInt = Integer.parseInt(valor);
				paciente.adicionaPontosFidelidade(valorInt);
				break;
		}
	}
	
	public void mudaGeneroPaciente() {
		paciente.mudaGenero();
	}
	
	public void adicionaProcedimento(Procedimento procedimento) {
		procedimentos.add(procedimento);
	}

	public int getTotalProcedimento() {
		return procedimentos.size();
	}
	
	private Paciente getPaciente(){
		return this.paciente;
	}
	
	public int getPontosFidelidade() {
		return getPaciente().getPontosFidelidade();	
	}
	
	public String getGastosPaciente(){
		return getPaciente().getGastos();
	}

	public double aplicaDesconto(double gasto) {
		return getPaciente().aplicaDesconto(gasto);
	}

	public double getDesconto() {
		return this.paciente.getDesconto();
	}
	
	@Override
	public String toString() {
		String res = "";
		
		res += getPaciente().toString() + "\n" +
			   "Resumo de Procedimentos: " + getTotalProcedimento() + " procedimento(s)";
		
		for (Procedimento procedimento: procedimentos) {
			res += procedimento.toString() + "\n";
		}
		
		return res;
	}
}
