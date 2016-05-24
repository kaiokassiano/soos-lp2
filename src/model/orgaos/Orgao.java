package model.orgaos;

import java.io.Serializable;

/**
 * Model que representa um orgao do sistema
 */
public class Orgao implements Serializable {

	private static final long serialVersionUID = -541010157090076328L;

	private String nome;
	private String tipoSanguineo;

	/**
	 * Construtor do orgao
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao
	 */
	public Orgao(String nome, String tipoSanguineo) {
		this.nome = nome;
		this.tipoSanguineo = tipoSanguineo;
	}

	/**
	 * Consulta o nome do orgao
	 * 
	 * @return String nome do orgao
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Consulta o tipo sanguineo de um orgao
	 * 
	 * @return String tipo sanguineo do orgao
	 */
	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	@Override
	public String toString() {
		return "Orgao " + getNome() + ", com tipo sanguineo " + getTipoSanguineo();
	}

	@Override
	public boolean equals(Object orgao) {
		if (orgao instanceof Orgao) {
			Orgao outroOrgao = (Orgao) orgao;
			if (outroOrgao.getNome().equals(this.nome) && outroOrgao.getTipoSanguineo().equals(this.tipoSanguineo)) {
				return true;
			}
		}
		return false;
	}

}
