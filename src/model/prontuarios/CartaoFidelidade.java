package model.prontuarios;

import java.io.Serializable;

public class CartaoFidelidade implements Serializable {

	private static final long serialVersionUID = -1736823742135308661L;
	private TipoFidelidade tipoFidelidade;
	private int pontosFidelidade;

	public CartaoFidelidade() {
		this.tipoFidelidade = new FidelidadePadrao();
		this.pontosFidelidade = 0;
	}

//	public double aplicaDesconto(double valorProcedimento) {
//		return tipoFidelidade.aplicaDesconto(valorProcedimento); // TODO
//	}

	public int getPontosFidelidade() {
		return this.pontosFidelidade;
	}

	private void updateFidelidade() {
		if (pontosFidelidade < 150) {
			this.tipoFidelidade = new FidelidadePadrao();
		} else if (pontosFidelidade >= 150 && pontosFidelidade <= 350) {
			this.tipoFidelidade = new FidelidadeMaster();
		} else if (pontosFidelidade > 350) {
			this.tipoFidelidade = new FidelidadeVIP();
		}
	}
	
	public void adicionaPontosFidelidade(int valor) {
		this.pontosFidelidade += valor;
		updateFidelidade();
	}

	public double aplicaDesconto(double gasto) {
		return tipoFidelidade.aplicaDesconto(gasto);
	}

	public double getDesconto() {
		return tipoFidelidade.getDesconto();
	}

}
