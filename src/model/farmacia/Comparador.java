package model.farmacia;

import java.util.Comparator;

public class Comparador implements Comparator<Medicamento>{

	@Override
	public int compare(Medicamento med1, Medicamento med2) {
		return med1.getInfoMedicamento("nome").compareTo(med2.getInfoMedicamento("nome"));
	}

	

	

	
	
}
