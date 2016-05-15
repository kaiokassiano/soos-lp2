package view;

import controller.hospital.HospitalController;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.DataInvalidaException;
import exceptions.logica.LogicaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.OrgaoInexistenteException;
import exceptions.logica.StringVaziaException;

/**
 * View principal para a aplicação
 */
public class MainFacade {

	private HospitalController hospitalController;
	
	/**
	 * Construtor da Facade
	 */
	public MainFacade() {
		hospitalController = new HospitalController();
	}

	public void iniciaSistema() {
		hospitalController.iniciaSistema();
	}

	public void fechaSistema() throws LogicaException {
		try {
			hospitalController.fechaSistema();
		} catch (LogicaException e) {
			throw new LogicaException("Nao foi possivel fechar o sistema. " + e.getMessage());
		}
	}

	public String liberaSistema(String chave, String nome, String dataNascimento) throws LogicaException {
		try {
			return hospitalController.liberaSistema(chave, nome, dataNascimento);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao liberar o sistema. " + e.getMessage());
		}
	}

	public void login(String matricula, String senha) throws LogicaException {
		try {
			hospitalController.login(matricula, senha);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Nao foi possivel realizar o login. " + e.getMessage());
		}
	}

	public void logout() throws LogicaException {
		try {
			hospitalController.logout();
		} catch (LogicaException e) {
			throw new LogicaException("Nao foi possivel realizar o logout. " + e.getMessage());
		}
	}

	public String getInfoFuncionario(String matricula, String atributo) throws LogicaException {
		try {
			return hospitalController.getInfoFuncionario(matricula, atributo);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro na consulta de funcionario. " + e.getMessage());
		}
	}

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) throws LogicaException {
		try {
			return hospitalController.cadastraFuncionario(nome, cargo, dataNascimento);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro no cadastro de funcionario. " + e.getMessage());
		}
	}
	
	public void excluiFuncionario(String matricula, String senha) throws LogicaException {
		try {
			hospitalController.excluiFuncionario(matricula, senha);
		}
		catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao excluir funcionario. " + e.getMessage());
		}
	}

	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor) throws LogicaException {
		try {
			hospitalController.atualizaInfoFuncionario(matricula, atributo, novoValor);
		}
		catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}
	
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws LogicaException {
		try {
			hospitalController.atualizaInfoFuncionario(atributo, novoValor);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}
	
	public void atualizaSenha(String antigaSenha, String novaSenha) throws LogicaException {
		try {
			hospitalController.atualizaSenha(antigaSenha, novaSenha);
		}
		catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}
	
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws LogicaException {
		try {
			return hospitalController.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro no cadastro de medicamento. " + e.getMessage());
		}
	}

	public String getInfoMedicamento(String requisicao, String nome) throws LogicaException {
		try {
			return hospitalController.getInfoMedicamento(requisicao, nome);
		} catch (LogicaException e) {
			throw new LogicaException("Erro no cadastro de medicamento. " + e.getMessage());
		}
	}

	public void atualizaMedicamento(String nome, String atributo, String novoValor) throws LogicaException {
		try {
			hospitalController.atualizaMedicamento(nome, atributo, novoValor);
		} catch (LogicaException e) {
			throw new LogicaException("Erro ao atualizar medicamento. " + e.getMessage());
		}
	}

	public String consultaMedCategoria(String categoria) throws LogicaException {
		return hospitalController.consultaMedCategoria(categoria);
	}
	
	public String consultaMedNome(String nome) throws LogicaException {
		try {
			return hospitalController.consultaMedNome(nome);
		} catch (LogicaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}
	
	public String getEstoqueFarmacia(String tipoOrdenacao) throws LogicaException{
		try {
			return hospitalController.getEstoqueFarmacia(tipoOrdenacao);
		} catch (LogicaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}
	
	public String cadastraPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero, String tipoSanguineo) throws LogicaException {
		try {
			return hospitalController.cadastraPaciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
		}
		catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Nao foi possivel cadastrar o paciente. " + e.getMessage());
		}
	}
	
	public String getInfoPaciente(String nome, String atributo) throws DataInvalidaException, StringVaziaException, NullStringException, NumeroNegativoException {
		return hospitalController.getInfoPaciente(nome, atributo);
	}
	
	public String getProntuario(int posicao) throws NumeroNegativoException, DadoInvalidoException {
		return hospitalController.getProntuario(posicao);
	}

	public void cadastraOrgao(String nome, String tipoSanguineo) throws LogicaException {
		hospitalController.cadastraOrgao(nome, tipoSanguineo);
	}
	
	public String buscaOrgPorSangue(String tipoSanguineo) throws LogicaException {
		try {
			return hospitalController.buscaOrgPorSangue(tipoSanguineo);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}
	
	public String buscaOrgPorNome(String nome) throws LogicaException {
		try {
			return hospitalController.buscaOrgPorNome(nome);
		} catch (DadoInvalidoException | OrgaoInexistenteException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}
	
	public boolean buscaOrgao(String nome, String tipoSanguineo) throws LogicaException {
		try {
			return hospitalController.buscaOrgao(nome, tipoSanguineo);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}
	
	public boolean retiraOrgao(String nome, String tipoSanguineo) throws LogicaException {
		try {
			return hospitalController.retiraOrgao(nome, tipoSanguineo);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("Erro na retirada de orgaos. " + e.getMessage());
		}
	}
	public int qtdOrgaos(String nome) throws LogicaException {
		try {
			return hospitalController.qtdOrgaos(nome);
		} catch (DadoInvalidoException | LogicaException e) {
			throw new LogicaException("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}
	
	public int totalOrgaosDisponiveis() {
		return hospitalController.totalOrgaosDisponiveis();
	}
	
}
