package factory.funcionarios;

/**
<<<<<<< HEAD
<<<<<<< HEAD
 * Enum representando o prefixo de cada tipo de funcionário
=======
 * Enum representando o prefixo de cada tipo de funcion�rio
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
=======
 * Enum representando o prefixo de cada tipo de funcionário
>>>>>>> 817f1631a7bcd94c25a99cd211a62dd9beb30bad
 */
public enum PrefixoFuncionario {
	
	Diretor(1), Medico(2), TecnicoAdministrativo(3);
	
	private int prefixo;
	
	/**
	 * Construtor do enum
<<<<<<< HEAD
<<<<<<< HEAD
	 * @param prefixo Prefixo dado ao funcionário
=======
	 * @param prefixo Prefixo dado ao funcion�rio
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
=======
	 * @param prefixo Prefixo dado ao funcionário
>>>>>>> 817f1631a7bcd94c25a99cd211a62dd9beb30bad
	 */
	private PrefixoFuncionario(int prefixo) {
		this.prefixo = prefixo;
	}
	
	public String getPrefixo() {
		return String.format("%d", prefixo);
	}
}
