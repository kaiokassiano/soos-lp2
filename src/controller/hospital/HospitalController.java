package controller.hospital;

import java.util.ArrayList;

import data.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.CategoriaInexistenteException;
import exceptions.logica.CategoriaInvalidaException;
import exceptions.logica.ComparacaoInvalidaException;
import exceptions.logica.LogicaException;
import exceptions.logica.LogoutException;
import exceptions.logica.ObjetoInexistenteException;
import exceptions.logica.OperacaoInvalidaException;
import model.farmacia.Farmacia;
import model.farmacia.Medicamento;
import model.paciente.Paciente;
import model.paciente.Prontuario;
import model.usuarios.GerenciadorFuncionarios;

/**
 * Controller principal da aplicação, faz o gerenciamento de todas as áreas e
 * assim como o do sistema
 */
public class HospitalController {

	private GerenciadorFuncionarios gerenciadorFuncionarios;
	private Farmacia farmacia;

	private ArrayList<Prontuario> prontuarios;
	private BancoDeDados bancoDeDados;

	public HospitalController() {
		gerenciadorFuncionarios = new GerenciadorFuncionarios();
		farmacia = new Farmacia();

		prontuarios = new ArrayList<Prontuario>();

		bancoDeDados = BancoDeDados.getInstance();
	}

	public void iniciaSistema() {
		bancoDeDados.init();
	}

	/**
	 * Fecha o sistema, junto com o banco de dados, verificando se alguém ainda
	 * está logado e, caso esteja, joga um erro
	 */
	public void fechaSistema() throws LogicaException {
		gerenciadorFuncionarios.fecharGerenciadorFuncionarios();
		bancoDeDados.fechar();
	}

	public String liberaSistema(String chave, String nome, String dataNascimento)
			throws LogicaException, DadoInvalidoException {
		return gerenciadorFuncionarios.liberaSistema(chave, nome, dataNascimento);
	}

	public void login(String matricula, String senha) throws NullStringException, LogicaException {
		gerenciadorFuncionarios.login(matricula, senha);
	}

	public void logout() throws LogoutException {
		gerenciadorFuncionarios.logout();
	}

	public String getInfoFuncionario(String matricula, String atributo) throws NullStringException, LogicaException {
		return gerenciadorFuncionarios.getInfoFuncionario(matricula, atributo);
	}

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento)
			throws LogicaException, DadoInvalidoException {
		return gerenciadorFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento);
	}
	
	public void excluiFuncionario(String matricula, String senha) throws DadoInvalidoException, LogicaException {
		gerenciadorFuncionarios.excluiFuncionario(matricula, senha);
	}

	public Medicamento getMedicamentoPeloNome(String nome) throws ObjetoInexistenteException {
		return farmacia.getMedicamentoPeloNome(nome);
	}

	public String getMedicamentosPelaCategoria(String categoria)
			throws CategoriaInexistenteException, CategoriaInvalidaException {
		return farmacia.getMedicamentosPelaCategoria(categoria);
	}

	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws DadoInvalidoException, LogicaException {
		return farmacia.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	public String getInfoMedicamento(String requisicao, String nome) throws ObjetoInexistenteException {
		return farmacia.getInfoMedicamento(requisicao, nome);
	}

	public void atualizaMedicamento(String nome, String atributo, String novoValor)
			throws ObjetoInexistenteException, OperacaoInvalidaException {
		farmacia.atualizaMedicamento(nome, atributo, novoValor);
	}

	public String consultaMedCategoria(String categoria) throws LogicaException {
		try {
			return farmacia.getMedicamentosPelaCategoria(categoria);
		} catch (CategoriaInexistenteException | CategoriaInvalidaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}

	public String consultaMedNome(String nome) throws ObjetoInexistenteException {
		return farmacia.consultaMedNome(nome);
	}

	public String getEstoqueFarmacia(String tipoOrdenacao) throws ComparacaoInvalidaException {

		return farmacia.getEstoqueFarmacia(tipoOrdenacao);
	}

	public String getInfoPaciente(String nomePaciente, String atributo) throws Exception {

		Paciente paciente = retornaPacientePeloNome(nomePaciente);

		String saida = "";

		switch (atributo) {
		case "Nome":
			saida = paciente.getNome();
		case "Data":
			saida = paciente.getData();
		case "Sexo":
			saida = paciente.getSexo();
		case "Genero":
			saida = paciente.getGenero();
		case "TipoSanguineo":
			saida = paciente.getTipoSanguineo();
		case "Peso":
			saida = paciente.getPeso();
		}
		return saida;
	}

	public void cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) throws Exception {

		Paciente p = retornaPacientePeloNome(nome);
		if (p != null) {
			throw new Exception("Nao foi possivel cadastrar o paciente. Paciente ja cadastrado.");
		}

		try {
			Paciente paciente = new Paciente(nome, data, peso, sexo, genero, tipoSanguineo);
			Prontuario prontuario = new Prontuario(paciente);
			this.prontuarios.add(prontuario);
		} catch (Exception e) {
			throw new Exception("Nao foi possivel cadastrar o paciente." + e.getMessage());
		}
	}

	public Paciente retornaPacientePeloNome(String nome) throws Exception {
		try {
			for (int i = 0; i < prontuarios.size(); i++) {
				if (prontuarios.get(i).getPaciente().getNome().equals(nome)
						&& prontuarios.get(i).getPaciente() != null) {
					return prontuarios.get(i).getPaciente();
				}
			}
		} catch (Exception e) {
			throw new Exception("Paciente nao encontrado.");
		}
		return null;
	}

}
