package model.prontuarios;

public class FidelidadeMaster implements TipoFidelidade {

	@Override
	public double aplicaDesconto(double gasto) {
		return getDesconto() * gasto;
	}

	@Override
	public double getDesconto() {
		return 0.85;
	}

}
