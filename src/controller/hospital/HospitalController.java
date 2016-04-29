package controller.hospital;

import factory.usuarios.*;
import factory.medicamentos.*;
import model.usuarios.*;

public class HospitalController {

	private FuncionarioFactory funcionarioFactory;
	private MedicamentoFactory medicamentoFactory;
	private Funcionario usuarioLogado;
	
	public HospitalController() {
		funcionarioFactory = new FuncionarioFactory();
		medicamentoFactory = new MedicamentoFactory();
	}
	
	public Diretor criarDiretor(String nome, String data) /* throws Exception */ {
		if (!usuarioLogado.temPermissao(PermissaoFuncionario.criacaoDiretor)) {
			// throw new PermissaoException();
		}
		return (Diretor) funcionarioFactory.criaFuncionario(nome, data, TipoFuncionario.Diretor);
	}
	
	public Medico criarMedico(String nome, String data) /* throws Exception */ {
		if (!usuarioLogado.temPermissao(PermissaoFuncionario.criacaoUsuarios)) {
			// throw new PermissaoException();
		}
		return (Medico) funcionarioFactory.criaFuncionario(nome, data, TipoFuncionario.Medico);
	}
	
	public TecnicoAdministrativo criarTecnicoAdministrativo(String nome, String data) /* throws PermissaoException */ {
		if (!usuarioLogado.temPermissao(PermissaoFuncionario.criacaoUsuarios)) {
			// throw new PermissaoException();
		}
		return (TecnicoAdministrativo) funcionarioFactory.criaFuncionario(nome, data, TipoFuncionario.TecnicoAdministrativo);
	}
}
