package model.prontuarios;

import java.io.Serializable;

/**
 * Model que representa o cartao de fidelidade do paciente
 */
public class CartaoFidelidade implements Serializable {

	private static final long serialVersionUID = -1736823742135308661L;
	private TipoFidelidade tipoFidelidade;
	private int pontosFidelidade;

	/**
	 * Construtor do cartao de fidelidade
	 */
	public CartaoFidelidade() {
		this.tipoFidelidade = new FidelidadePadrao();
		this.pontosFidelidade = 0;
	}

	/**
	 * Consulta a quantidade de pontos de fidelidade
	 * 
	 * @return Inteiro pontos de fidelidade
	 */
	public int getPontosFidelidade() {
		return this.pontosFidelidade;
	}

	/**
	 * Realiza a troca dinamica do tipo de fidelidade do paciente
	 */
	private void updateFidelidade() {
		if (pontosFidelidade < 150) {
			this.tipoFidelidade = new FidelidadePadrao();
		} else if (pontosFidelidade >= 150 && pontosFidelidade <= 350) {
			this.tipoFidelidade = new FidelidadeMaster();
		} else if (pontosFidelidade > 350) {
			this.tipoFidelidade = new FidelidadeVIP();
		}
	}

	/**
	 * Adiciona um valor de pontos de fidelidade ao cartao de fidelidade do
	 * paciente
	 * 
	 * @param valor
	 *            - valor que deseja adicionar
	 */
	public void adicionaPontosFidelidade(int valor) {
		this.pontosFidelidade += valor;
		updateFidelidade();
	}

	/**
	 * Aplica o desconto do cartao de fidelidade
	 * 
	 * @param gasto
	 *            - gasto a ser computador
	 * @return Double gasto apos o desconto
	 */
	public double aplicaDesconto(double gasto) {
		return tipoFidelidade.aplicaDesconto(gasto);
	}

	/**
	 * Pega o desconto do cartao de fidelidade
	 * 
	 * @return Double desconto do cartao
	 */
	public double getDesconto() {
		return tipoFidelidade.getDesconto();
	}

}
