package model.procedimentos;

import java.io.Serializable;

import model.prontuarios.Prontuario;

public interface Procedimento extends Serializable {

	public void realizaProcedimento(Prontuario prontuario);
	
}
