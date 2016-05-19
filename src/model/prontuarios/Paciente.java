package model.prontuarios;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import exceptions.logica.DataInvalidaException;

public class Paciente implements Serializable {

	private static final long serialVersionUID = -7182415301026656229L;

	private String nome;
	private LocalDate dataNascimento;
	private double peso;
	private String sexoBiologico;
	private String genero;
	private String tipoSanguineo; // TODO criar enum
	private double gastos;
	private UUID id;
	private Cartao cartao;

	public Paciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero, String tipoSanguineo) throws DataInvalidaException {
		
		this.nome = nome;
		this.dataNascimento = parseData(dataNascimento);
		this.peso = peso;
		this.sexoBiologico = sexoBiologico;
		this.genero = genero;
		this.tipoSanguineo = tipoSanguineo;
		this.id = UUID.randomUUID();
		this.gastos = 0.0;
		this.cartao = new Cartao();
	}
	
	public LocalDate parseData(String dataNascimento) throws DataInvalidaException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			return LocalDate.parse(dataNascimento, formato);
		}
		catch (Exception e) {
			throw new DataInvalidaException("Data invalida.");
		}
	}


	public String getNome() {
		return this.nome;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public double getPeso() {
		return this.peso;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getSexoBiologico() {
		return this.sexoBiologico;
	}

	public void mudaGenero() {
		if (this.genero.equals("feminino")) {
			this.genero = "masculino";
		} else {
			this.genero = "feminino";
		}
	}
	
	public String getGenero() {
		return this.genero;
	}

	public String getTipoSanguineo() {
		return this.tipoSanguineo;
	}

	public UUID getId() {
		return this.id;
	}
	
	public int getIdadePaciente() {
		int idade;
		idade = this.dataNascimento.until(LocalDate.now()).getYears();
		return idade;
	}
	
	public void adicionaGastos(double gastos) {
		this.gastos += gastos;
	}
	
	public double getGastos() {
		return this.gastos;
	}

	public int getPontosFidelidade() {
		return this.cartao.getPontosFidelidade();
	}

	public void adicionaPontosFidelidade(int valor) {
		this.cartao.adicionaPontosFidelidade(valor);
	}

	public double aplicaDesconto() {
		return this.cartao.aplicaDesconto();
	}

	public double getDesconto() {
		return this.cartao.aplicaDesconto();
	}
	
}
