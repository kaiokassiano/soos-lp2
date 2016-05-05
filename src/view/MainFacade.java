package view;

import controller.hospital.HospitalController;

import data.BancoDeDados;
import exceptions.dado.*;
import exceptions.logica.*;

/**
 * View principal para a aplicação
 */
public class MainFacade {

<<<<<<< HEAD
	private HospitalController hospitalController; // composicao

=======
	private HospitalController hospitalController;
	
	/**
	 * Construtor da Facade
	 */
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
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
<<<<<<< HEAD

	public String liberaSistema(String chave, String nome, String dataNascimento) throws LogicaException {
=======
	
	public String liberaSistema(String chave, String nome, String dataNascimento) {
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
		try {
			return hospitalController.liberaSistema(chave, nome, dataNascimento);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro ao liberar o sistema. " + e.getMessage());
		}
	}
<<<<<<< HEAD

	public void login(String matricula, String senha) throws LogicaException {
=======
	
	public void login(String matricula, String senha) {
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
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
<<<<<<< HEAD

	public String getInfoFuncionario(String matricula, String atributo) throws LogicaException {
=======
	
	public String getInfoFuncionario(String matricula, String atributo) {
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
		try {
			return hospitalController.getInfoFuncionario(matricula, atributo);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro na consulta de funcionario. " + e.getMessage());
		}
	}
<<<<<<< HEAD

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) throws LogicaException {
=======
	
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) {
>>>>>>> 6b2ac5fd4269c96360d0732c87a71c1badb1554a
		try {
			return hospitalController.cadastraFuncionario(nome, cargo, dataNascimento);
		} catch (LogicaException | DadoInvalidoException e) {
			throw new LogicaException("Erro no cadastro de funcionario. " + e.getMessage());
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

	public String consultaMedCategoria(String categoria) {
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

}
