package model.prontuarios;

public class FidelidadePadrao implements TipoFidelidade {

	@Override
	public double aplicaDesconto(double gasto) {
		return getDesconto() * gasto;
	}

	@Override
	public double getDesconto() {
		return 1;
	}

}
