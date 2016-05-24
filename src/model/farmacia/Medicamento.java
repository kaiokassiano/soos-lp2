package model.farmacia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.TreeSet;

import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import exceptions.logica.OperacaoInvalidaException;
import validacao.medicamentos.ValidacaoMedicamentos;

/**
 * Model que representa um Medicamento
 */
public class Medicamento implements Comparable<Medicamento>, Serializable {

	private static final long serialVersionUID = 9144424243399198829L;

	private String nome;
	private String tipo;
	private double preco;
	private int quantidade;
	private HashSet<CategoriaMedicamento> categorias;

	/**
	 * Construtor do medicamento
	 * 
	 * @param nome
	 *            - nome do medicamento
	 * @param tipo
	 *            - tipo do medicamento
	 * @param preco
	 *            - preco do medicamento
	 * @param quantidade
	 *            - unidades do medicamento
	 * @param categoriasRecebidas
	 *            - categorias do medicamento
	 * @throws NullStringException
	 * @throws LogicaException
	 */
	public Medicamento(String nome, String tipo, double preco, int quantidade, String categoriasRecebidas)
			throws NullStringException, LogicaException {

		ValidacaoMedicamentos.validaDadosMedicamento(nome, tipo, preco, quantidade);

		this.nome = nome;
		defineTipo(tipo);
		this.preco = preco;
		this.quantidade = quantidade;
		categorias = new HashSet<CategoriaMedicamento>();

		defineCategoriasMedicamento(categoriasRecebidas);
		defineDesconto();
	}

	/**
	 * Define as categorias de um medicamento
	 * 
	 * @param categoriasRecebidas
	 *            - categorias do medicamento
	 * @throws NullStringException
	 */
	private void defineCategoriasMedicamento(String categoriasRecebidas) throws NullStringException {
		String[] arrayCategorias = categoriasRecebidas.split(",");

		for (int i = 0; i < arrayCategorias.length; i++) {
			CategoriaMedicamento categoria = CategoriaMedicamento.converteString(arrayCategorias[i]);

			if (ValidacaoMedicamentos.validaCategoriasMedicamento(arrayCategorias)) {
				categorias.add(categoria);
			}
		}
	}

	/**
	 * Retorna uma informacao do medicamento
	 * 
	 * @param requisicao
	 *            - informacao do medicamento que deseja consultar
	 * @return String com o valor da requisicao
	 */
	public String getInfoMedicamento(String requisicao) {
		switch (requisicao) {
		case "nome":
			return getNome();
		case "tipo":
			return getTipo();
		case "preco":
			return Double.toString(getPreco());
		case "quantidade":
			return Integer.toString(getQuantidade());
		case "categorias":
			return getCategorias();
		}
		return null;
	}

	/**
	 * Atualiza uma informacao do medicamento
	 * 
	 * @param atributo
	 *            - atributo que deseja atualizar
	 * @param novoValor
	 *            - novo valor do atributo
	 * @throws OperacaoInvalidaException
	 */
	public void atualizaMedicamento(String atributo, String novoValor) throws OperacaoInvalidaException {
		switch (atributo) {
		case "preco":
			double valorDouble = Double.parseDouble(novoValor);

			if (this.tipo.equals("Generico")) {
				setPreco(valorDouble * 0.6);
				break;
			}
			setPreco(valorDouble);
			break;

		case "quantidade":
			int valorInteiro = Integer.valueOf(novoValor);
			setQuantidade(valorInteiro);
			break;

		case "nome":
			throw new OperacaoInvalidaException("Nome do medicamento nao pode ser alterado.");

		case "tipo":
			throw new OperacaoInvalidaException("Tipo do medicamento nao pode ser alterado.");
		}

	}

	/**
	 * Retorna o nome do medicamento
	 * 
	 * @return String nome do medicamento
	 */
	private String getNome() {
		return nome;
	}

	/**
	 * Retorna o preco do medicamento
	 * 
	 * @return Double preco do medicamento
	 */
	private double getPreco() {
		return preco;
	}

	/**
	 * Muda o preco do medicamento
	 * 
	 * @param preco
	 *            - preco a alterar
	 */
	private void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * Define se o medicamento tem desconto, caso seja generico
	 */
	private void defineDesconto() {
		if (this.tipo.equals("Generico")) {
			setPreco((this.preco * 60) / 100);
		}
	}

	/**
	 * Retorna o tipo do medicamento
	 * 
	 * @return String tipo do medicamento
	 */
	private String getTipo() {
		return this.tipo;
	}

	/**
	 * Define o tipo do medicamento
	 * 
	 * @param tipo
	 *            - tipo do medicamento (generico / referencia)
	 */
	private void defineTipo(String tipo) {
		if (tipo.equals("generico")) {
			this.tipo = "Generico";
		} else {
			this.tipo = "de Referencia";
		}
	}

	/**
	 * Retorna o numero de unidades do medicamento
	 * 
	 * @return Inteiro unidades do medicamento
	 */
	private int getQuantidade() {
		return quantidade;
	}

	/**
	 * Muda a quantidade de medicamentos
	 * 
	 * @param quantidade
	 *            - quantidade a alterar
	 */
	private void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Retorna todas as categorias do medicamento
	 * 
	 * @return String categorias do medicamento
	 */
	private String getCategorias() {

		TreeSet<String> treeset = new TreeSet<String>();

		for (CategoriaMedicamento categoriaMedicamento : categorias) {
			treeset.add(categoriaMedicamento.getCategoria());
		}

		return String.join(",", treeset);

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Medicamento))
			return false;

		Medicamento other = (Medicamento) obj;
		return other.getNome().equals(getNome()) && other.getTipo().equals(getTipo());
	}

	@Override
	public String toString() {
		return String.format("Medicamento %s: %s - Preco: R$ %.2f - Disponivel: %d - Categorias: %s", this.getTipo(),
				this.getNome(), this.getPreco(), this.getQuantidade(), this.getCategorias());
	}

	@Override
	public int compareTo(Medicamento medicamento) {
		if (medicamento instanceof Medicamento) {
			Medicamento novoMedicamento = (Medicamento) medicamento;
			if (this.getPreco() < novoMedicamento.getPreco()) {
				return -1;
			} else if (this.getPreco() > novoMedicamento.getPreco()) {
				return 1;
			} else {
				return 0;
			}
		}
		return -1;
	}

}