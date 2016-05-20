package model.procedimentos;

import java.io.Serializable;

import model.prontuarios.Prontuario;

public interface Procedimento extends Serializable {

	void realizaProcedimento(Prontuario prontuario);
	
}
