package model.procedimentos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import model.prontuarios.Prontuario;

public abstract class Procedimento implements Serializable {

	private static final long serialVersionUID = -9090351685269376187L;
	
	private String nomeMedico;
	private LocalDate dataProcedimento;
	
	/**
	 * Realiza um procedimento em um paciente, passando parametros opcionais
	 * 
	 * @param prontuario Prontuário do paciente a ser realizado o procedimento
	 * @param params     Parâmetros opcionais do procedimento
	 */
	public abstract void realizaProcedimento(Prontuario prontuario, HashMap<String, Object> params);
	
	/**
	 * Realiza um procedimento em um paciente
	 * 
	 * @param prontuario Prontuário do paciente a ser realizado o procedimento
	 */
	public void realizaProcedimento(Prontuario prontuario) {
		realizaProcedimento(prontuario, null);
	}
	
	public abstract String getTipoProcedimento();
	
	public void setMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	
	public String getMedico() {
		return this.nomeMedico;
	}
	
	public void setDataProcedimento(LocalDate dataProcedimento) {
		this.dataProcedimento = dataProcedimento;
	}
	
	public LocalDate getDataProcedimento() {
		return this.dataProcedimento;
	}
	
	@Override
	public String toString() {
		String res = "";
		
		res += "--> " + getTipoProcedimento() + ":" + System.lineSeparator() +
			   "....... Data: " + getDataProcedimento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
			   " Medico: " + getMedico();

		return res;
	};
}
