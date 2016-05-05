package controller.hospital;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import data.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.AtributoInvalidoException;
import exceptions.logica.CategoriaInexistenteException;
import exceptions.logica.CategoriaInvalidaException;
import exceptions.logica.ChaveIncorretaException;
import exceptions.logica.ComparacaoInvalidaException;
import exceptions.logica.LogicaException;
import exceptions.logica.ObjetoInexistenteException;
import exceptions.logica.OperacaoInvalidaException;
import exceptions.logica.PermissaoException;
import exceptions.logica.SenhaIncorretaException;
import exceptions.logica.StringVaziaException;
import factory.funcionarios.FuncionarioFactory;
import factory.medicamentos.MedicamentoFactory;
import model.farmacia.Comparador;
import model.farmacia.Medicamento;
import model.usuarios.Diretor;
import model.usuarios.Funcionario;
import model.usuarios.PermissaoFuncionario;
import valida.dados.ValidaMatricula;
import valida.dados.ValidaNomes;
import valida.dados.ValidaSenhas;
import validacao.medicamentos.ValidacaoMedicamentos;

/**
 * Controller principal da aplica��o, faz o gerenciamento de todas as �reas
 * e assim como o do sistema
 */
public class HospitalController {

	private static final String chaveSistema = "c041ebf8";
	
	private FuncionarioFactory funcionarioFactory;
	private MedicamentoFactory medicamentoFactory;
	
	private Funcionario usuarioLogado;
	private HashMap<String, Funcionario> funcionarios;
	private HashMap<String, Medicamento> medicamentos;
	private BancoDeDados bancoDeDados;
	
	private boolean sistemaLiberado;
	
	/**
	 * Construtor do HospitalController
	 */
	public HospitalController() {
		funcionarioFactory = new FuncionarioFactory();
		medicamentoFactory = new MedicamentoFactory();
		
		funcionarios = new HashMap<String, Funcionario>();
		medicamentos = new HashMap<String, Medicamento>();
		
		bancoDeDados = BancoDeDados.getInstance();
	}
	
	/**
	 * Inicia o sistema, juntamente com o banco de dados
	 */
	public void iniciaSistema() {
		bancoDeDados.init();
	}
	
	/**
	 * Fecha o sistema, junto com o banco de dados, verificando 
	 * se algu�m ainda est� logado e, caso esteja, joga um erro
	 */
	public void fechaSistema() {
		if (isUsuarioLogado()) {
			throw new LogicaException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
		}
		bancoDeDados.fechar();
	}
	
	/**
	 * Libera o sistema, criando um usu�rio com privil�gios de Diretor
	 * e retornando o seu numero de matr�cula
	 * 
	 * @param chave          Chave para liberar o sistema
	 * @param nome           Nome do diretor
	 * @param dataNascimento Data de nascimento do diretor conforme o padr�o dd/MM/yyyy
	 * @return               Matr�cula do diretor
	 */
	public String liberaSistema(String chave, String nome, String dataNascimento) {
		if (chave == null) {
			throw new NullStringException("Chave nao pode ser nulo.");
		}
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo.");
		}
		if (dataNascimento == null) {
			throw new NullStringException("Data nao pode ser nulo.");
		}
		if (sistemaLiberado) {
			throw new LogicaException("Sistema liberado anteriormente.");
		}
		if (!chave.equals(chaveSistema)) {
			throw new ChaveIncorretaException("Chave invalida.");
		}
		sistemaLiberado = true;
		
		Diretor diretor = (Diretor) funcionarioFactory.criaFuncionario(nome, "Diretor Geral", dataNascimento);
		funcionarios.put(diretor.getMatricula(), diretor);
		
		return diretor.getMatricula();
	}
	
	/**
	 * Verifica se tem algum usu�rio logado
	 * 
	 * @return Boleano indicando se tem algum usu�rio logado
	 */
	public boolean isUsuarioLogado() {
		return usuarioLogado != null;
	}
	
	/**
	 * Realiza o login de um usu�rio
	 * 
	 * @param matricula Matr�cula do usu�rio
	 * @param senha     Senha do usu�rio
	 */
	public void login(String matricula, String senha) {
		if (!sistemaLiberado) {
			throw new LogicaException("Sistema nao liberado.");
		}
		else if (isUsuarioLogado()) {
			throw new LogicaException("Um funcionario ainda esta logado: " + usuarioLogado.getNome() + ".");
		}
		else if (matricula == null) {
			throw new NullStringException("Matricula nao pode ser nulo.");
		}
		else if (senha == null) {
			throw new NullStringException("Senha nao pode ser nulo.");
		}
		
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		
		if (!funcionario.getSenha().equals(senha)) {
			throw new SenhaIncorretaException("Senha incorreta.");
		}
		
		usuarioLogado = funcionario;
	}
	
	/**
	 * Realiza o logout de um usu�rio, e joga um erro caso n�o
	 * tenha usu�rios logados
	 */
	public void logout() {
		if (!isUsuarioLogado()) {
			throw new LogicaException("Nao ha um funcionario logado.");
		}
		usuarioLogado = null;
	}
	
	/**
	 * Retorna um funcion�rio dado seu numero de matr�cula, e joga um erro
	 * caso n�o exista um funcion�rio com aquela matr�cula
	 * 
	 * @param matricula Matr�cula do usu�rio
	 * @return          Inst�ncia de um objeto Funcionario
	 */
	public Funcionario getFuncionarioPorMatricula(String matricula) {
		Funcionario funcionario = funcionarios.get(matricula);
		
		if (funcionario == null) {
			throw new ObjetoInexistenteException("Funcionario nao cadastrado.");
		}
		
		return funcionario;
	}
	
	/**
	 * Cadastra um funcion�rio, retornando seu n�mero de matr�cula
	 * 
	 * @param nome           Nome do funcion�rio
	 * @param cargo          Cargo do funcion�rio
	 * @param dataNascimento Data de nascimento do funcion�rio
	 * @return               Matr�cula do funcion�rio criado
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) {
		if (nome == null) {
			throw new NullStringException("Nome do funcionario nao pode ser nulo.");
		}
		else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome do funcionario nao pode ser vazio.");
		}
		if (cargo == null) {
			throw new NullStringException("Nome do cargo nao pode ser nulo.");
		}
		else if (cargo.trim().isEmpty()) {
			throw new StringVaziaException("Nome do cargo nao pode ser vazio.");
		}
		
		if (!sistemaLiberado) {
			throw new LogicaException("O sistema esta bloqueado.");
		}
		else if (!isUsuarioLogado()) {
			throw new LogicaException("Usuario nao esta logado.");
		}
		else if (!usuarioLogado.temPermissao(PermissaoFuncionario.criacaoUsuarios)) {
			throw new PermissaoException("O funcionario " + usuarioLogado.getNome() + " nao tem permissao para cadastrar funcionarios.");
		}
		else if (cargo.equalsIgnoreCase("Diretor Geral")) {
			throw new PermissaoException("Nao eh possivel criar mais de um Diretor Geral.");
		}
		
		Funcionario funcionario = funcionarioFactory.criaFuncionario(nome, cargo, dataNascimento);
		funcionarios.put(funcionario.getMatricula(), funcionario);
		
		return funcionario.getMatricula();
	}
	
	/**
	 * Pega o atributo do funcion�rio requisitado
	 * 
	 * @param matricula Matr�cula do funcion�rio
	 * @param atributo  Atributo a ser requisitado
	 * @return          Valor do atributo do funcion�rio
	 */
	public String getInfoFuncionario(String matricula, String atributo) {
		if (!isUsuarioLogado()) {
			throw new LogicaException("Usuario nao esta logado.");
		}
		else if (matricula == null) {
			throw new NullStringException("Matricula nao pode ser nulo.");
		}
		else if (atributo == null) {
			throw new NullStringException("Atributo nao pode ser nulo.");
		}
		
		Funcionario funcionario = getFuncionarioPorMatricula(matricula);
		
		String attr = null;
		
		if (atributo.equalsIgnoreCase("Nome")) {
			attr = funcionario.getNome();
		}
		else if (atributo.equalsIgnoreCase("Cargo")) {
			attr = funcionario.getCargo();
		}
		else if (atributo.equalsIgnoreCase("Data")) {
			attr = funcionario.getDataNascimento();
		}
		else if (atributo.equalsIgnoreCase("Senha")) {
			if (usuarioLogado.getMatricula().equals(funcionario.getMatricula())) {
				attr = funcionario.getSenha();
			}
			else {
				throw new PermissaoException("A senha do funcionario eh protegida.");
			}
		}
		else {
			throw new AtributoInvalidoException("Atributo invalido.");
		}
		
		return attr;
	}

	public Medicamento getMedicamentoPeloNome(String nome) throws ObjetoInexistenteException {
		Medicamento medicamento = medicamentos.get(nome);

		ValidacaoMedicamentos.validaObjetoMedicamento(medicamento);
		
		return medicamento;
	}
	
	public String getMedicamentosPelaCategoria(String categoria) throws CategoriaInexistenteException, CategoriaInvalidaException{

		if (!"analgesico,antibiotico,antiemetico,antiinflamatorio,antitermico,hormonal".contains(categoria)) {
			throw new CategoriaInvalidaException("Categoria invalida.");
		}
		
		ArrayList<Medicamento> arrayCategorias = new ArrayList<Medicamento>();

		for (Map.Entry<String, Medicamento> entry : medicamentos.entrySet()) {

			if (entry.getValue().getInfoMedicamento("categorias").contains(categoria)) {
				arrayCategorias.add(entry.getValue());
			}

		}
		
		if (arrayCategorias.isEmpty()) {
			throw new CategoriaInexistenteException("Nao ha remedios cadastrados nessa categoria.");
		}
		
		Collections.sort(arrayCategorias);
		String saida = "";
		
		for (int i = 0; i < arrayCategorias.size(); i++) {
			if (i == arrayCategorias.size() -1) {
				saida += arrayCategorias.get(i).getInfoMedicamento("nome");
			} else {
				saida += arrayCategorias.get(i).getInfoMedicamento("nome") + ",";
			}
		}
		
		return saida;
		
	}
	
	public String cadastraMedicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws DadoInvalidoException, LogicaException {
		Medicamento medicamento = medicamentoFactory.criaMedicamento(nome, tipo, preco, quantidade, categorias);
		medicamentos.put(nome, medicamento);

		return nome;
	}

	public String getInfoMedicamento(String requisicao, String nome) throws ObjetoInexistenteException {
		Medicamento medicamento = getMedicamentoPeloNome(nome);

		return medicamento.getInfoMedicamento(requisicao);
	}

	public void atualizaMedicamento(String nome, String atributo, String novoValor)
			throws ObjetoInexistenteException, OperacaoInvalidaException {
		getMedicamentoPeloNome(nome).atualizaMedicamento(atributo, novoValor);
	}

	public String consultaMedCategoria(String categoria) throws CategoriaInexistenteException, CategoriaInvalidaException{
		try {
			return getMedicamentosPelaCategoria(categoria);		
		} catch (CategoriaInexistenteException | CategoriaInvalidaException e) {
			throw new LogicaException("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}
	
	public String consultaMedNome(String nome) throws ObjetoInexistenteException{
		return getMedicamentoPeloNome(nome).toString();
	}
	
	public String getEstoqueFarmacia (String tipoOrdenacao) throws ComparacaoInvalidaException{
		
		if (!tipoOrdenacao.equals("preco") && !tipoOrdenacao.equals("alfabetica")) {
			throw new ComparacaoInvalidaException("Tipo de ordenacao invalida.");
		}
		
		ArrayList<Medicamento> arrayMedicamentos = new ArrayList<Medicamento>();
		
		for (Map.Entry<String, Medicamento> entry : medicamentos.entrySet()) {
			
			arrayMedicamentos.add(entry.getValue());

		}
		
		Comparador comparador = new Comparador();
		
		if (tipoOrdenacao.equals("preco")) {
			Collections.sort(arrayMedicamentos);
		} else if (tipoOrdenacao.equals("alfabetica")) {
			Collections.sort(arrayMedicamentos, comparador);
		}
		String saida = "";
		
		for (int i = 0; i < arrayMedicamentos.size(); i++) {
			if (i == arrayMedicamentos.size() -1) {
				saida += arrayMedicamentos.get(i).getInfoMedicamento("nome");
			} else {
				saida += arrayMedicamentos.get(i).getInfoMedicamento("nome") + ",";
			}
		}
		
		return saida;
		
	}
	
}