package model.prontuarios;

import java.util.Comparator;

public class Comparador implements Comparator<Prontuario> {
	
	@Override
	public int compare(Prontuario prontuario1, Prontuario prontuario2) {
		return prontuario1.getNome().compareTo(prontuario2.getNome());
	}

}
