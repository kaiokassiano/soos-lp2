package model.prontuarios;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Classe que compara dois prontuarios do sistema
 */
public class Comparador implements Comparator<Prontuario>, Serializable {

	private static final long serialVersionUID = 8835951019931417120L;

	@Override
	public int compare(Prontuario prontuario1, Prontuario prontuario2) {
		return prontuario1.getInfoPaciente("nome").compareTo(prontuario2.getInfoPaciente("nome"));
	}

}
