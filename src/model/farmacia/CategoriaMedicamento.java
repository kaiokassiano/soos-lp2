package model.farmacia;

public enum CategoriaMedicamento {

	analgesico("analgesico"), antibiotico("antibiotico"), antiemetico("antiemetico"), antiinflamatorio("antiinflamatorio"), antitermico("antitermico"), hormonal("hormonal");
	
	private String categoria;
	
	private CategoriaMedicamento(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCategoria() {
		return this.categoria;
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
