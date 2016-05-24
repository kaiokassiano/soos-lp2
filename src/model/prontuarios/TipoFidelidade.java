package model.prontuarios;

import java.io.Serializable;

/**
 * Model que representa o tipo de fidelidade do cartao de fidelidade do paciente
 */
public interface TipoFidelidade extends Serializable {

	/**
	 * Aplica o desconto do cartao de fidelidade
	 * 
	 * @param gasto
	 *            - gasto a ser computado
	 * @return Double gasto apos o desconto
	 */
	double aplicaDesconto(double gasto);

	/**
	 * Pega o desconto do cartao de fidelidade
	 * 
	 * @return Double desconto do cartao
	 */
	double getDesconto();

}
