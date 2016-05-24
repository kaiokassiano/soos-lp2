package view;

import banco.dados.BancoDeDados;
import controller.hospital.HospitalController;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.OrgaoInexistenteException;

/**
 * View principal para a aplicação
 */
public class MainFacade {

	private HospitalController hospitalController;
	private BancoDeDados bancoDeDados;
	
	/**
	 * Construtor da Facade
	 */
	public MainFacade() {
		bancoDeDados = BancoDeDados.getInstance();
	}

	public void iniciaSistema() {
		bancoDeDados.init();
		hospitalController = bancoDeDados.getHospitalController();
	}

	public void fechaSistema() throws LogicaException {
		hospitalController.fechaController();
		bancoDeDados.fechar();
	}

	public String liberaSistema(String chave, String nome, String dataNascimento) throws LogicaException {
		return hospitalController.liberaSistema(chave, nome, dataNascimento);
	}

	public void login(String matricula, String senha) throws LogicaException {
		hospitalController.login(matricula, senha);
	}

	public void logout() throws LogicaException {
		hospitalController.logout();
	}

	public String getInfoFuncionario(String matricula, String atributo) throws LogicaException {
		return hospitalController.getInfoFuncionario(matricula, atributo);
	}

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) throws LogicaException {
		return hospitalController.cadastraFuncionario(nome, cargo, dataNascimento);
	}
	
	public void excluiFuncionario(String matricula, String senha) throws LogicaException {
		hospitalController.excluiFuncionario(matricula, senha);
	}

	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor) throws LogicaException {
		hospitalController.atualizaInfoFuncionario(matricula, atributo, novoValor);
	}
	
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws LogicaException {
		hospitalController.atualizaInfoFuncionario(atributo, novoValor);
	}
	
	public void atualizaSenha(String antigaSenha, String novaSenha) throws LogicaException {
		hospitalController.atualizaSenha(antigaSenha, novaSenha);
	}
	
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws LogicaException {
		return hospitalController.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	public String getInfoMedicamento(String requisicao, String nome) throws LogicaException {
		return hospitalController.getInfoMedicamento(requisicao, nome);
	}

	public void atualizaMedicamento(String nome, String atributo, String novoValor) throws LogicaException {
		hospitalController.atualizaMedicamento(nome, atributo, novoValor);
	}

	public String consultaMedCategoria(String categoria) throws LogicaException {
		return hospitalController.consultaMedCategoria(categoria);
	}
	
	public String consultaMedNome(String nome) throws LogicaException {
		return hospitalController.consultaMedNome(nome);
	}
	
	public String getEstoqueFarmacia(String tipoOrdenacao) throws LogicaException{
		return hospitalController.getEstoqueFarmacia(tipoOrdenacao);
	}
	
	public String cadastraPaciente(String nome, String dataNascimento, double peso, String sexoBiologico, String genero, String tipoSanguineo) throws LogicaException {
		return hospitalController.cadastraPaciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo);
	}
	
	public String getInfoPaciente(String nome, String atributo) throws NullStringException, LogicaException {
		return hospitalController.getInfoPaciente(nome, atributo);
	}
	
	public String getProntuario(int posicao) throws LogicaException {
		return hospitalController.getProntuario(posicao);
	}

	public void cadastraOrgao(String nome, String tipoSanguineo) throws LogicaException {
		hospitalController.cadastraOrgao(nome, tipoSanguineo);
	}
	
	public String buscaOrgPorSangue(String tipoSanguineo) throws LogicaException {
		return hospitalController.buscaOrgPorSangue(tipoSanguineo);
	}
	
	public String buscaOrgPorNome(String nome) throws LogicaException {
		return hospitalController.buscaOrgPorNome(nome);
	}
	
	public boolean buscaOrgao(String nome, String tipoSanguineo) throws LogicaException {
		return hospitalController.buscaOrgao(nome, tipoSanguineo);
	}
	
	public boolean retiraOrgao(String nome, String tipoSanguineo) throws LogicaException {
		return hospitalController.retiraOrgao(nome, tipoSanguineo);
		
	}
	public int qtdOrgaos(String nome) throws LogicaException {
		return hospitalController.qtdOrgaos(nome);
	}
	
	public int totalOrgaosDisponiveis() {
		return hospitalController.totalOrgaosDisponiveis();
	}
	
	public String getPacienteID(String nome) throws LogicaException {
		return hospitalController.getPacienteID(nome);
	}
	
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, String medicamentos) throws LogicaException {
		hospitalController.realizaProcedimento(procedimentoSolicitado, nomePaciente, medicamentos);
	}
	
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente, String nomeOrgao, String medicamentos) throws LogicaException {
		hospitalController.realizaProcedimento(procedimentoSolicitado, nomePaciente, nomeOrgao, medicamentos);
	}
	
	public void realizaProcedimento(String procedimentoSolicitado, String nomePaciente) throws LogicaException {
		hospitalController.realizaProcedimento(procedimentoSolicitado, nomePaciente);
	}
	
	public int getTotalProcedimento(String nomePaciente) {
		return hospitalController.getTotalProcedimento(nomePaciente);
	}
	
	public int getPontosFidelidade(String nomePaciente) {
		return hospitalController.getPontosFidelidade(nomePaciente);
	}
	
	public String getGastosPaciente(String nomePaciente){
		return hospitalController.getGastosPaciente(nomePaciente);
	}
	
	public void exportaFichaPaciente(String idPaciente) throws LogicaException {
		hospitalController.exportaFichaPaciente(idPaciente);
	}
	
}
