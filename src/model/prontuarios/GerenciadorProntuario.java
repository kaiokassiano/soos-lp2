package model.prontuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import banco.dados.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.PacienteNaoCadastradoException;
import exceptions.logica.PermissaoException;
import model.procedimentos.GerenciadorProcedimentos;
import model.usuarios.PermissaoFuncionario;
import validacao.procedimentos.ValidaProcedimento;
import validacao.prontuarios.ValidacaoProntuarios;

public class GerenciadorProntuario implements Serializable {
	
	private static final long serialVersionUID = -8996993358051123045L;
	private List<Prontuario> prontuarios;
	private GerenciadorProcedimentos gerenciadorProcedimento;
	
	public GerenciadorProntuario(){
		this.prontuarios = new ArrayList<Prontuario>();
		this.gerenciadorProcedimento = new GerenciadorProcedimentos();
	}

	public String cadastraPaciente(String nome,  String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws LogicaException, DadoInvalidoException {
		
		if (!BancoDeDados.getInstance().getUsuarioLogado().temPermissao(PermissaoFuncionario.CRIACAO_PACIENTES)) {
			throw new PermissaoException("O funcionario " + BancoDeDados.getInstance().getUsuarioLogado().getNome() + " nao tem permissao para cadastrar pacientes.");
		}
		
		ValidacaoProntuarios.validaDadosProntuario(nome, dataNascimento, peso, tipoSanguineo);
		
		if (retornaProntuarioPeloNome(nome) != null) {
			throw new LogicaException("Paciente ja cadastrado.");
		}
		

		Prontuario prontuario = new Prontuario(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
		prontuarios.add(prontuario);
				
		ordenaPeloNome(nome);
		
		return prontuario.getNome();
		
	}
	
	public void ordenaPeloNome(String nome){
		Comparador comparador = new Comparador();
		Collections.sort(prontuarios, comparador);
		
	}

	public String getInfoPaciente(String nome, String atributo) {
		return retornaProntuarioPeloNome(nome).getInfoPaciente(nome, atributo);	
	}
	
	public Prontuario retornaProntuarioPeloNome(String nome){
		for (Prontuario prontuario : prontuarios) {
			if (prontuario.getNome().equals(nome)) {
				return prontuario;
			}
		}
		return null;
	}

	public String getProntuario(int posicao) throws NumeroNegativoException, DadoInvalidoException {
		if (posicao < 0) {
			throw new NumeroNegativoException("Erro ao consultar prontuario. Indice do prontuario nao pode ser negativo.");
		} if (posicao > prontuarios.size()) {
			throw new DadoInvalidoException("Erro ao consultar prontuario. Nao ha prontuarios suficientes (max = " + (prontuarios.size()) + ").");
		}
		return prontuarios.get(posicao).getNome();
		
	}
	
	// Sobrecarga de metodo. Esse metodo aqui nao recebe o orgao
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, String medicamentos) throws DadoInvalidoException, LogicaException {
		Prontuario prontuario = retornaProntuarioPeloNome(nomePaciente);
		if (prontuario != null) {
			gerenciadorProcedimento.realizaProcedimento(prontuario, procedimentoSolicitado);
			prontuario.adicionaProcedimento(procedimentoSolicitado);
		}
	}
		
	// Sobrecarga de metodo. Esse metodo aqui recebe o orgao
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, String nomeOrgao, String medicamentos) throws LogicaException, DadoInvalidoException {
		Prontuario prontuario = retornaProntuarioPeloNome(nomePaciente);
		if (prontuario != null) {
			gerenciadorProcedimento.realizaProcedimento(prontuario, procedimentoSolicitado);
			prontuario.adicionaProcedimento(procedimentoSolicitado);
		}
	}

	public int getTotalProcedimento(String nomePaciente) {
		return retornaProntuarioPeloNome(nomePaciente).getTotalProcedimento();
	}
	
}