package model.prontuarios;

public class TipoFidelidadeMaster implements TipoFidelidade{
	
	private int pontos;

	@Override
	public double aplicaDesconto() {
		return 0.85;
	}

	@Override
	public void adicionaPontosBonus() {
		// TODO Auto-generated method stub
		
	}

}
