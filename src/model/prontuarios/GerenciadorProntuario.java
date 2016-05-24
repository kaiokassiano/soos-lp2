package model.prontuarios;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import banco.dados.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.PacienteNaoCadastradoException;
import exceptions.logica.PermissaoException;
import model.procedimentos.GerenciadorProcedimentos;
import model.usuarios.PermissaoFuncionario;
import validacao.prontuarios.ValidacaoProntuarios;

/**
 * Model gerenciador do prontuario dos pacientes do sistema
 */
public class GerenciadorProntuario implements Serializable {

	private static final long serialVersionUID = -8996993358051123045L;

	private static final String diretorioFichas = "fichas_pacientes";

	private List<Prontuario> prontuarios;
	private GerenciadorProcedimentos gerenciadorProcedimento;

	/**
	 * Construtor do prontuario
	 */
	public GerenciadorProntuario() {
		this.prontuarios = new ArrayList<Prontuario>();
		this.gerenciadorProcedimento = new GerenciadorProcedimentos();
	}

	/**
	 * Cadastra um paciente na lista de pacientes do sistema
	 * 
	 * @param nome
	 *            - nome do paciente
	 * @param dataNascimento
	 *            - data de nascimento do paciente de acordo com o formato
	 *            dd/MM/yyyy
	 * @param peso
	 *            - peso do paciente
	 * @param sexoBiologico
	 *            - sexo biologico do paciente
	 * @param genero
	 *            - genero do paciente
	 * @param tipoSanguineo
	 *            - tipo sanguineo do paciente
	 * @return Nome do paciente cadastrado
	 * @throws LogicaException
	 * @throws DadoInvalidoException
	 */
	public String cadastraPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero,
			String tipoSanguineo) throws LogicaException, DadoInvalidoException {

		if (!BancoDeDados.getInstance().getUsuarioLogado().temPermissao(PermissaoFuncionario.CRIACAO_PACIENTES)) {
			throw new PermissaoException("O funcionario " + BancoDeDados.getInstance().getUsuarioLogado().getNome()
					+ " nao tem permissao para cadastrar pacientes.");
		}

		ValidacaoProntuarios.validaDadosProntuario(nome, dataNascimento, peso, tipoSanguineo);

		if (retornaProntuarioPeloNome(nome) != null) {
			throw new LogicaException("Paciente ja cadastrado.");
		}

		Prontuario prontuario = new Prontuario(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
		prontuarios.add(prontuario);

		ordenaPeloNome();

		return prontuario.getInfoPaciente("nome");
	}

	/**
	 * Ordena a lista de prontuarios pelos nomes dos pacientes em ordem
	 * alfabetica
	 */
	public void ordenaPeloNome() {
		Comparador comparador = new Comparador();
		Collections.sort(prontuarios, comparador);
	}

	/**
	 * Consulta alguma informacao do paciente
	 * 
	 * @param nome
	 *            - nome do paciente que deseja consultar
	 * @param atributo
	 *            - atributo que deseja consultar
	 * @return String valor do atributo
	 */
	public String getInfoPaciente(String nome, String atributo) {
		return retornaProntuarioPeloNome(nome).getInfoPaciente(atributo);
	}

	/**
	 * Busca um prontuario pelo nome do paciente
	 * 
	 * @param nome
	 *            - nome do paciente que deseja obter o prontuario
	 * @return Prontuario do paciente
	 */
	public Prontuario retornaProntuarioPeloNome(String nome) {
		for (Prontuario prontuario : prontuarios) {
			if (prontuario.getInfoPaciente("nome").equals(nome)) {
				return prontuario;
			}
		}
		return null;
	}

	/**
	 * Consulta o prontuario na posicao solicitada, na lista de prontuarios
	 * 
	 * @param posicao
	 *            - posicao do prontuario
	 * @return String nome do paciente do prontuario
	 * @throws NumeroNegativoException
	 * @throws DadoInvalidoException
	 */
	public String getProntuario(int posicao) throws NumeroNegativoException, DadoInvalidoException {
		if (posicao < 0) {
			throw new NumeroNegativoException("Indice do prontuario nao pode ser negativo.");
		}
		if (posicao > prontuarios.size()) {
			throw new DadoInvalidoException("Nao ha prontuarios suficientes (max = " + (prontuarios.size()) + ").");
		}
		return prontuarios.get(posicao).getInfoPaciente("nome");
	}

	/**
	 * Busca um prontuario pelo ID do paciente
	 * 
	 * @param idPaciente
	 *            - id do paciente
	 * @return Prontuario do paciente
	 * @throws PacienteNaoCadastradoException
	 */
	public Prontuario getProntuario(String idPaciente) throws PacienteNaoCadastradoException {
		for (Prontuario prontuario : prontuarios) {
			if (prontuario.getInfoPaciente("id").equals(idPaciente)) {
				return prontuario;
			}
		}

		throw new PacienteNaoCadastradoException("Paciente nao existe.");
	}

	/**
	 * Realiza um procedimento no paciente. Nao recebe medicamentos
	 * 
	 * @param procedimentoSolicitado
	 *            - procedimento a ser realizado
	 * @param nomePaciente
	 *            - nome do paciente
	 * @param precoMedicamentos
	 *            - preco total dos medicamentos
	 * @param params
	 *            - parametros adicionais (nome do medico, nome do orgao)
	 * @throws DadoInvalidoException
	 * @throws LogicaException
	 */
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, double precoMedicamentos,
			HashMap<String, Object> params) throws DadoInvalidoException, LogicaException {
		Prontuario prontuario = retornaProntuarioPeloNome(nomePaciente);

		if (prontuario != null) {
			gerenciadorProcedimento.realizaProcedimento(prontuario, procedimentoSolicitado, precoMedicamentos, params);
		}
	}

	/**
	 * Realiza um procedimento no paciente. Recebe medicamentos
	 * 
	 * @param procedimentoSolicitado
	 *            - procedimento a ser realizado
	 * @param nomePaciente
	 *            - nome do paciente
	 * @param params
	 *            - parametros adicionais (nome do medico, nome do orgao)
	 * @throws LogicaException
	 * @throws DadoInvalidoException
	 */
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, HashMap<String, Object> params)
			throws LogicaException, DadoInvalidoException {
		realizaProcedimento(procedimentoSolicitado, nomePaciente, 0.0, params);
	}

	/**
	 * Consulta numero de procedimentos realizados pelo paciente
	 * 
	 * @param nomePaciente
	 *            - nome do paciente
	 * @return Inteiro numero de procedimentos
	 */
	public int getTotalProcedimento(String nomePaciente) {
		return retornaProntuarioPeloNome(nomePaciente).getTotalProcedimento();
	}

	/**
	 * Consulta os gastos totais do paciente
	 * 
	 * @param nomePaciente
	 *            - nome do paciente
	 * @return String gastos totais do paciente
	 */
	public String getGastosPaciente(String nomePaciente) {
		return retornaProntuarioPeloNome(nomePaciente).getGastosPaciente();
	}

	/**
	 * Consulta os pontos de fidelidade do paciente
	 * 
	 * @param nomePaciente
	 *            - nome do paciente
	 * @return Inteiro pontos de fidelidade
	 */
	public int getPontosFidelidade(String nomePaciente) {
		return retornaProntuarioPeloNome(nomePaciente).getPontosFidelidade();
	}

	/**
	 * Salva a ficha do paciente no arquivo .txt correspondente ao paciente
	 * 
	 * @param idPaciente
	 *            - id do paciente que deseja salvar a ficha
	 * @throws PacienteNaoCadastradoException
	 */
	public void exportaFichaPaciente(String idPaciente) throws PacienteNaoCadastradoException {
		Prontuario prontuario = getProntuario(idPaciente);

		String data = prontuario.toString();
		String nomeFicha = gerarNomeFichaPaciente(prontuario.getInfoPaciente("nome"));

		PrintWriter output = null;

		try {
			File file = new File(diretorioFichas + "/" + nomeFicha);

			file.getParentFile().mkdirs();

			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("IOException: " + e.getMessage());
			}

			output = new PrintWriter(file);

			output.write(data);
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		} finally {
			output.close();
		}
	}

	/**
	 * Gera o nome da ficha do paciente para o metodo que exporta a ficha do
	 * paciente
	 * 
	 * @param nomePaciente
	 *            - nome do paciente
	 * @return String nome da ficha do paciente
	 */
	private String gerarNomeFichaPaciente(String nomePaciente) {
		LocalDate now = LocalDate.now();

		String res = "";
		String[] nomes = nomePaciente.split(" ");

		for (String nome : nomes) {
			res += nome + "_";
		}

		res += now.getYear() + "_" + String.format("%02d", now.getMonthValue()) + "_"
				+ String.format("%02d", now.getDayOfMonth()) + ".txt";

		return res;
	}

}