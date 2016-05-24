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
	private TipoSanguineo tipoSanguineo;
	private double gastos;
	private UUID id;
	private CartaoFidelidade cartaoFidelidade;

	public Paciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero, String tipoSanguineo) throws DataInvalidaException {
		this.nome = nome;
		this.dataNascimento = parseData(dataNascimento);
		this.peso = peso;
		this.sexoBiologico = sexoBiologico;
		this.genero = genero;
		this.tipoSanguineo = TipoSanguineo.getTipoSanguineo(tipoSanguineo);
		this.id = UUID.randomUUID();
		this.gastos = 0.0;
		this.cartaoFidelidade = new CartaoFidelidade();
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
		if (this.genero.equals("masculino")) {
			this.genero = "feminino";
		} else {
			this.genero = "masculino";
		}
	}
	
	public String getGenero() {
		return this.genero;
	}
	
	public TipoSanguineo getTipoSanguineo() {
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
	
	public String getGastos() {
		return String.format("%.2f", gastos);
	}

	public int getPontosFidelidade() {
		return cartaoFidelidade.getPontosFidelidade();
	}

	public void adicionaPontosFidelidade(int valor) {
		cartaoFidelidade.adicionaPontosFidelidade(valor);
	}

	public double aplicaDesconto(double gasto) {
		return cartaoFidelidade.aplicaDesconto(gasto);
	}

	public double getDesconto() {
		return cartaoFidelidade.getDesconto();
	}
	
	@Override
	public String toString() {
		String res = "";
		
		res += "Paciente: " + getNome() + System.lineSeparator() +
			   "Peso: " + getPeso() + " kg Tipo Sanguineo: " + getTipoSanguineo().toString() + System.lineSeparator() +
			   "Sexo: " + getSexoBiologico() + " Genero: " + getGenero() + System.lineSeparator() +
			   "Gasto total: R$ " + getGastos() + " Pontos acumulados: " + getPontosFidelidade();
		
		return res;
	}
}
