package model.prontuarios;

public class FidelidadeVIP implements TipoFidelidade {

	private static final long serialVersionUID = -7635478839306721741L;

	@Override
	public double aplicaDesconto(double gasto) {
		return getDesconto() * gasto;
	}

	@Override
	public double getDesconto() {
		return 0.7;
	}

}
