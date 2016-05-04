package factory.funcionarios;

/**
 * Enum representando o prefixo de cada tipo de funcion�rio
 */
public enum PrefixoFuncionario {
	
	Diretor(1), Medico(2), TecnicoAdministrativo(3);
	
	private int prefixo;
	
	/**
	 * Construtor do enum
	 * @param prefixo Prefixo dado ao funcion�rio
	 */
	private PrefixoFuncionario(int prefixo) {
		this.prefixo = prefixo;
	}
	
	public String getPrefixo() {
		return String.format("%d", prefixo);
	}
}
