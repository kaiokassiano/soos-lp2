package model.prontuarios;

public class FidelidadeMaster implements TipoFidelidade {

	private static final long serialVersionUID = 4400485480080514659L;

	@Override
	public double aplicaDesconto(double gasto) {
		return getDesconto() * gasto;
	}

	@Override
	public double getDesconto() {
		return 0.85;
	}

}
