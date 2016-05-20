package model.prontuarios;

public class FidelidadePadrao implements TipoFidelidade {

	private static final long serialVersionUID = 5416047838113107376L;

	@Override
	public double aplicaDesconto(double gasto) {
		return getDesconto() * gasto;
	}

	@Override
	public double getDesconto() {
		return 1;
	}

}
