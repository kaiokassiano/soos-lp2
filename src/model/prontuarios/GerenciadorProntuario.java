package model.prontuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import banco.dados.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.PermissaoException;
import model.usuarios.PermissaoFuncionario;
import validacao.prontuarios.ValidacaoProntuarios;

public class GerenciadorProntuario implements Serializable {
	
	private static final long serialVersionUID = -8996993358051123045L;
	private List<Prontuario> prontuarios;
	
	public GerenciadorProntuario(){
		this.prontuarios = new ArrayList<Prontuario>();
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
	
	public Prontuario retornaProntuarioPeloNome(String nome) {
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
	

}