package model.prontuarios;

public class TipoFidelidadeMaster implements TipoFidelidade{
	
	private int pontos;

	@Override
	public void aplicaDesconto(Cartao cartao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionaPontos(Cartao cartao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionaPontosBonus(Cartao cartao) {
		// TODO Auto-generated method stub
		
	}


	
//		if (this.pontos < 150) return "Padrao";
//		if (this.pontos >= 150 && pontos <= 350) return "Master";
//		if (this.pontos > 350) return "VIP";
//		return "Padrao";

}
