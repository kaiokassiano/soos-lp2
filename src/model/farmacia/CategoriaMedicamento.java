package model.farmacia;

import java.io.Serializable;

/**
 * Enum representando a(s) categoria(s) do(s) medicamento(s)
 */
public enum CategoriaMedicamento implements Serializable {

	ANALGESICO("analgesico"), ANTIBIOTICO("antibiotico"), ANTIEMETICO("antiemetico"), ANTIINFLAMATORIO(
			"antiinflamatorio"), ANTITERMICO("antitermico"), HORMONAL("hormonal");

	private String valorCategoria;

	/**
	 * Construtor do enum
	 * 
	 * @param categoria
	 *            - categoria dada ao medicamento
	 */
	private CategoriaMedicamento(String categoria) {
		this.valorCategoria = categoria;
	}

	/**
	 * Retorna o valor de uma das constantes
	 * 
	 * @return String com o valor da constante
	 */
	public String getCategoria() {
		return this.valorCategoria;
	}

	/**
	 * Converte para String o valor de uma constante
	 * 
	 * @param categoria
	 *            - categoria desejada
	 * @return String com o valor da constante
	 */
	public static CategoriaMedicamento converteString(String categoria) {
		for (int i = 0; i < CategoriaMedicamento.values().length; i++) {
			if (categoria.equals(CategoriaMedicamento.values()[i].getCategoria())) {
				return CategoriaMedicamento.values()[i];
			}
		}

		return null;
	}
}
