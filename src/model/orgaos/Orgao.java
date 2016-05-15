package model.orgaos;

public class Orgao {

	private String nome;
	private String tipoSanguineo;

	public Orgao(String nome, String tipoSanguineo) {
		this.nome = nome;
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	@Override
	public String toString() {
		return "Orgao " + getNome() + ", com tipo sanguineo " + getTipoSanguineo();
	}

}
