package model.farmacia;

public enum CategoriaMedicamento {

	ANALGESICO("analgesico"), ANTIBIOTICO("antibiotico"), ANTIEMETICO("antiemetico"), ANTIINFLAMATORIO(
			"antiinflamatorio"), ANTITERMICO("antitermico"), HORMONAL("hormonal");

	private String valorCategoria;

	private CategoriaMedicamento(String categoria) {
		this.valorCategoria = categoria;
	}

	public String getCategoria() {
		return this.valorCategoria;
	}

	public static CategoriaMedicamento converteString(String categoria) {

		for (int i = 0; i < CategoriaMedicamento.values().length; i++) {
			if (categoria.equals(CategoriaMedicamento.values()[i].getCategoria())) {
				return CategoriaMedicamento.values()[i];
			}
		}

		return null;
	}
}
