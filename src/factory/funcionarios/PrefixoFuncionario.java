package factory.funcionarios;

/**
<<<<<<< HEAD
 * Enum representando o prefixo de cada tipo de funcionÃ¡rio
=======
 * Enum representando o prefixo de cada tipo de funcionário
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
 */
public enum PrefixoFuncionario {
	
	Diretor(1), Medico(2), TecnicoAdministrativo(3);
	
	private int prefixo;
	
	/**
	 * Construtor do enum
<<<<<<< HEAD
	 * @param prefixo Prefixo dado ao funcionÃ¡rio
=======
	 * @param prefixo Prefixo dado ao funcionário
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
	 */
	private PrefixoFuncionario(int prefixo) {
		this.prefixo = prefixo;
	}
	
	public String getPrefixo() {
		return String.format("%d", prefixo);
	}
}
