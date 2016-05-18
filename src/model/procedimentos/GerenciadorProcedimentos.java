package model.procedimentos;

import java.io.Serializable;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import factory.procedimento.ProcedimentoFactory;
import model.prontuarios.Prontuario;

public class GerenciadorProcedimentos implements Serializable{
	
	private static final long serialVersionUID = 7351303124526973570L;
	private Procedimento procedimento;
	private ProcedimentoFactory procedimentoFactory;
	
	public GerenciadorProcedimentos() {
		this.procedimentoFactory = new ProcedimentoFactory();
	}
	
	public void realizaProcedimento(Prontuario prontuario, String procedimentoSolicitado) throws LogicaException, DadoInvalidoException {
		
		Procedimento procedimento = procedimentoFactory.criaProcedimento(procedimentoSolicitado);		
		procedimento.realizaProcedimento(prontuario);
	}
	
}
