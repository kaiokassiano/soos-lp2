package model.prontuarios;

public class FidelidadeVIP implements TipoFidelidade {

	@Override
	public double aplicaDesconto(double gasto) {
		return getDesconto() * gasto;
		
	}

	@Override
	public double getDesconto() {
		return 0.7;
	}

}
