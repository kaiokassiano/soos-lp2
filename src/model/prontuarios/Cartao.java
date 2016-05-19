package model.prontuarios;

public class Cartao {

	private TipoFidelidade tipoFidelidade;
	private int pontosFidelidade;

	public Cartao() {
		this.tipoFidelidade = new TipoFidelidadePadrao();
		this.pontosFidelidade = 0;
	}

//	public double aplicaDesconto(double valorProcedimento) {
//		double valorComDesconto = tipoFidelidade.aplicaDesconto(valorProcedimento); // TODO
//		return valorComDesconto;
//	}

	public int getPontosFidelidade() {
		return this.pontosFidelidade;
	}

	private void upgradeFidelidade() {
		if (this.pontosFidelidade >= 150 && pontosFidelidade <= 350) {
			this.tipoFidelidade = new TipoFidelidadeMaster();
		} else if (this.pontosFidelidade > 350) {
			this.tipoFidelidade = new TipoFidelidadeVIP();
		}
	}
	
	public void adicionaPontosFidelidade(int valor) {
		this.pontosFidelidade += valor;
		upgradeFidelidade();
	}

	public double aplicaDesconto() {
		return tipoFidelidade.aplicaDesconto();
	}

}
