package model.prontuarios;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import exceptions.logica.DataInvalidaException;

/**
 * Model que representa um paciente do sistema
 */
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

	/**
	 * Construtor do paciente
	 * 
	 * @param nome
	 *            - nome do paciente
	 * @param dataNascimento
	 *            - data de nascimento de acordo com o formato dd/MM/yyyy
	 * @param peso
	 *            - peso do paciente
	 * @param sexoBiologico
	 *            - sexo biologico do paciente
	 * @param genero
	 *            - genero do paciente
	 * @param tipoSanguineo
	 *            - tipo sanguineo do paciente
	 * @throws DataInvalidaException
	 */
	public Paciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws DataInvalidaException {
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

	/**
	 * Analisa e valida o formato de data seguindo o padrao dd/MM/yyyy
	 * 
	 * @param dataNascimento
	 *            - data de nascimento do paciente
	 * @return data de nascimento do paciente
	 * @throws DataInvalidaException
	 */
	public LocalDate parseData(String dataNascimento) throws DataInvalidaException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			return LocalDate.parse(dataNascimento, formato);
		} catch (Exception e) {
			throw new DataInvalidaException("Data invalida.");
		}
	}

	/**
	 * Consulta o nome do paciente
	 * 
	 * @return String nome do paciente
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Consulta data de nascimento do paciente
	 * 
	 * @return Data de nascimento do paciente
	 */
	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	/**
	 * Consulta o peso do paciente
	 * 
	 * @return Double peso do paciente
	 */
	public double getPeso() {
		return this.peso;
	}

	/**
	 * Reduz o peso do paciente por uma dada porcentagem
	 * 
	 * @param porcentagem
	 *            Porcentagem a ser removida do peso do paciente
	 */
	public void reduzPeso(double porcentagem) {
		this.peso -= peso * porcentagem;
	}

	/**
	 * Consulta o sexo biologico do paciente
	 * 
	 * @return String sexo biologico
	 */
	public String getSexoBiologico() {
		return this.sexoBiologico;
	}

	/**
	 * Muda o genero do paciente, de acordo com a cirurgia de mudanca de sexo
	 */
	public void mudaGenero() {
		if (this.genero.equals("masculino")) {
			this.genero = "feminino";
		} else {
			this.genero = "masculino";
		}
	}

	/**
	 * Consulta o genero do paciente
	 * 
	 * @return String genero do paciente
	 */
	public String getGenero() {
		return this.genero;
	}

	/**
	 * Consulta o tipo sanguineo do paciente
	 * 
	 * @return Tipo sanguineo do paciente
	 */
	public TipoSanguineo getTipoSanguineo() {
		return this.tipoSanguineo;
	}

	/**
	 * Consulta o UUID (Unique User ID) do paciente
	 * 
	 * @return UUID do paciente
	 */
	public UUID getId() {
		return this.id;
	}

	/**
	 * Consulta a idade do paciente de acordo com a data de nascimento
	 * 
	 * @return Idade do paciente
	 */
	public int getIdadePaciente() {
		return this.dataNascimento.until(LocalDate.now()).getYears();
	}

	/**
	 * Adiciona gastos ao paciente
	 * 
	 * @param gastos
	 *            - gastos a serem adicionados
	 */
	public void adicionaGastos(double gastos) {
		this.gastos += gastos;
	}

	/**
	 * Consulta os gastos do paciente
	 * 
	 * @return String gastos do paciente
	 */
	public String getGastos() {
		return String.format("%.2f", gastos).replace(",", ".");
	}

	/**
	 * Consulta os pontos de fidelidade do paciente
	 * 
	 * @return Inteiro pontos de fidelidade
	 */
	public int getPontosFidelidade() {
		return cartaoFidelidade.getPontosFidelidade();
	}

	/**
	 * Adiciona pontos de fidelidade ao paciente
	 * 
	 * @param valor
	 *            - pontos a serem adicionados
	 */
	public void adicionaPontosFidelidade(int valor) {
		cartaoFidelidade.adicionaPontosFidelidade(valor);
	}

	/**
	 * Aplica desconto para as operacoes do paciente, de acordo com a fidelidade
	 * 
	 * @param gasto
	 *            - gasto a ser computado
	 * @return Double gasto apos o desconto
	 */
	public double aplicaDesconto(double gasto) {
		return cartaoFidelidade.aplicaDesconto(gasto);
	}

	/**
	 * Pega o desconto do cartao fidelidade
	 * 
	 * @return Double desconto do cartao
	 */
	public double getDesconto() {
		return cartaoFidelidade.getDesconto();
	}

	@Override
	public String toString() {
		String res = "";

		res += "Paciente: " + getNome() + System.lineSeparator() + "Peso: " + getPeso() + " kg Tipo Sanguineo: "
				+ getTipoSanguineo().toString() + System.lineSeparator() + "Sexo: " + getSexoBiologico() + " Genero: "
				+ getGenero() + System.lineSeparator() + "Gasto total: R$ " + getGastos() + " Pontos acumulados: "
				+ getPontosFidelidade();

		return res;
	}
}
