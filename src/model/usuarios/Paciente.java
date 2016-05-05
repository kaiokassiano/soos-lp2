package model.usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import exceptions.logica.DataInvalidaException;

public class Paciente {

	private String nome;
	private LocalDate dataNascimento;
	private double peso;
	private String tipoSanguineo;
	private String sexoBiologico;
	private String genero;
	private UUID id;
	private String[] tiposSanguineos = new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }; // TODO
	// REFATORAR

	public Paciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws Exception {
		validaPaciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
		// TODO Refatorar para validar tipos primitivos
		this.nome = nome;
		this.dataNascimento = parseData(dataNascimento);
		this.peso = peso;
		this.tipoSanguineo = tipoSanguineo;
		this.sexoBiologico = sexoBiologico;
		this.genero = genero;
		this.id = UUID.randomUUID();
	}

	// TODO REFATORAR AS EXCEPTIONS !!!!
	private void validaPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws Exception {
		validaNome(nome);
		validaPeso(peso);
		validaTipoSanguineo(tipoSanguineo);
		validaDataNasc(dataNascimento);
		/*validaSexo(sexoBiologico);
		validaGenero(genero);
		*/ 

	}

	private void validaNome(String nome) throws Exception {
		try {
			validaString(nome);
		} catch (Exception e) {
			throw new Exception(" Nome do paciente" + e.getMessage());
		}
	}

	private void validaString(String palavra) throws Exception {
		if (palavra.equals("") || palavra.equals(" ") || palavra == null) {
			throw new Exception(" nao pode ser vazio.");
		}

	}

	private void validaPeso(double peso) throws Exception {
		try {
			validaDouble(peso);
		} catch (Exception e) {
			throw new Exception(" Peso do paciente" + e.getMessage());
		}
	}

	private void validaDouble(double valor) throws Exception {
		if (valor < 0) {
			throw new Exception(" nao pode ser negativo.");
		}
	}

	private void validaTipoSanguineo(String tipoSanguineo) throws Exception {
		if (!contem(tipoSanguineo)) {
			throw new Exception(" Tipo sanguineo invalido.");
		}
	}

	public boolean contem(String tipoSanguineo) {
		for (int i = 0; i < tiposSanguineos.length; i++) {
			if (tipoSanguineo.toUpperCase().equals(tiposSanguineos[i])) {
				return true;
			}
		}
		return false;
	}

	private void validaDataNasc(String dataNasc) throws Exception {
		parseData(dataNasc);
	}

	// TODO Refatorar depois, remover da classe FuncioanrioFactory
	private LocalDate parseData(String dataNascimento) throws Exception {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			return LocalDate.parse(dataNascimento, formato);
		} catch (Exception e) {
			throw new Exception(" Data invalida.");
		}
	}

	public String getNome() {
		return this.nome;
	}

	public String getData() {
		return this.dataNascimento.toString();
	}

	public String getSexo() {
		return this.sexoBiologico;
	}

	public String getGenero() {
		return this.genero;
	}

	public String getTipoSanguineo() {
		return this.tipoSanguineo;
	}

	public String getPeso() {
		return this.peso + "";
	}
	
	public UUID getUuid() {
		return this.id;
	}	
	
/*	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Paciente) {
			Paciente outroPaciente = (Paciente) obj;
			return (this.nome.equals(outroPaciente.getNome()) && this.getData().equals(outroPaciente.getData()));
		}
		return false;
	}
*/

}
