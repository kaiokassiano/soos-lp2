package model.prontuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exceptions.logica.DataInvalidaException;
import factory.pacientes.PacienteFactory;
import model.procedimentos.Procedimento;

/**
 * Model que representa o prontuario do paciente do sistema
 */
public class Prontuario implements Serializable {

	private static final long serialVersionUID = 5588910044733686821L;

	private Paciente paciente;
	private PacienteFactory pacienteFactory;
	private List<Procedimento> procedimentos;

	/**
	 * Construtor do prontuario
	 * 
	 * @param nome
	 *            - nome do paciente
	 * @param dataNascimento
	 *            - data de nascimento do paciente de acordo com o formato
	 *            dd/MM/yyyy
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
	public Prontuario(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws DataInvalidaException {
		this.procedimentos = new ArrayList<>();
		this.pacienteFactory = new PacienteFactory();

		this.paciente = pacienteFactory.criaPaciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
	}

	/**
	 * Consulta alguma informacao do paciente
	 * 
	 * @param atributo
	 *            - atributo que deseja consultar do paciente
	 * @return String valor do atributo
	 */
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

	/**
	 * Adiciona gastos ao paciente
	 * 
	 * @param gastos
	 *            - quantidade de gastos
	 */
	public void adicionaGastos(double gastos) {
		paciente.adicionaGastos(gastos);
	}

	/**
	 * Reduz o peso do paciente em uma determinada porcentagem, de acordo com a
	 * cirurgia bariatrica
	 * 
	 * @param porcentagem
	 *            - porcentagem de peso a diminuir
	 */
	public void reduzPesoPaciente(double porcentagem) {
		paciente.reduzPeso(porcentagem);
	}

	/**
	 * Adiciona uma quantidade de pontos ao paciente
	 * 
	 * @param pontos
	 */
	public void adicionaPontosFidelidade(int pontos) {
		paciente.adicionaPontosFidelidade(pontos);
	}

	/**
	 * Muda genero do paciente de acordo com a cirurgia de mudanca de sexo
	 */
	public void mudaGeneroPaciente() {
		paciente.mudaGenero();
	}

	/**
	 * Adiciona um procedimento na lista de procedimentos do paciente
	 * 
	 * @param procedimento
	 *            - procedimento a ser adicionado
	 */
	public void adicionaProcedimento(Procedimento procedimento) {
		procedimentos.add(procedimento);
	}

	/**
	 * Consulta o total de procedimentos realizados pelo paciente
	 * 
	 * @return Inteiro total de procedimentos
	 */
	public int getTotalProcedimento() {
		return procedimentos.size();
	}

	/**
	 * Retorna um paciente para o prontuario
	 * 
	 * @return Paciente
	 */
	private Paciente getPaciente() {
		return this.paciente;
	}

	/**
	 * Consulta o total de pontos de fidelidade
	 * 
	 * @return Inteiro quantidade de pontos de fidelidade
	 */
	public int getPontosFidelidade() {
		return getPaciente().getPontosFidelidade();
	}

	/**
	 * Consulta os gastos totais do paciente
	 * 
	 * @return String gastos totais do paciente
	 */
	public String getGastosPaciente() {
		return getPaciente().getGastos();
	}

	/**
	 * Aplica o desconto referente ao cartao fidelidade
	 * 
	 * @param gasto
	 *            - gasto a ser computado
	 * @return Double gasto apos o desconto
	 */
	public double aplicaDesconto(double gasto) {
		return getPaciente().aplicaDesconto(gasto);
	}

	/**
	 * Pega o desconto do cartao
	 * 
	 * @return Double valor do desconto
	 */
	public double getDesconto() {
		return this.paciente.getDesconto();
	}

	@Override
	public String toString() {
		String res = "";

		res += getPaciente().toString() + System.lineSeparator() + "Resumo de Procedimentos: " + getTotalProcedimento()
				+ " procedimento(s)" + System.lineSeparator();

		for (Procedimento procedimento : procedimentos) {
			res += procedimento.toString() + System.lineSeparator();
		}

		return res;
	}
}
