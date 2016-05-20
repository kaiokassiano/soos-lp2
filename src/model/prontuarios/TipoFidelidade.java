package model.prontuarios;

import java.io.Serializable;

public interface TipoFidelidade extends Serializable {
	
	double aplicaDesconto(double gasto);

	double getDesconto();

}
