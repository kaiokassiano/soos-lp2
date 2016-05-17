package model.prontuarios;

public interface TipoFidelidade {
	
	void aplicaDesconto(Cartao cartao);
	void adicionaPontos(Cartao cartao);	
	void adicionaPontosBonus(Cartao cartao);	

}
