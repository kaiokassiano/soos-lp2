package model.farmacia;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparador de dois medicamentos
 */
public class Comparador implements Comparator<Medicamento>, Serializable {

	private static final long serialVersionUID = -3963623303507189614L;

	@Override
	public int compare(Medicamento med1, Medicamento med2) {
		return med1.getInfoMedicamento("nome").compareTo(med2.getInfoMedicamento("nome"));
	}

}
