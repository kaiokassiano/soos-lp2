package controller.hospital;

import factory.usuarios.*;
import factory.medicamentos.*;
import model.usuarios.*;

public class HospitalController {

	private UsuarioFactory usuarioFactory;
	private MedicamentoFactory medicamentoFactory;
	private Usuario usuarioLogado;
	
	public HospitalController() {
		usuarioFactory = new UsuarioFactory();
		medicamentoFactory = new MedicamentoFactory();
	}
	
	public Medico criarMedico(String nome, String data) /* throws Exception */ {
		if (!usuarioLogado.temPermissao(PermissaoUsuario.criacaoUsuarios)) {
			// throw new PermissaoException();
		}
		return (Medico) usuarioFactory.criaFuncionario(nome, data, TipoUsuario.Medico);
	}
	
	public TecnicoAdministrativo criarTecnicoAdministrativo(String nome, String data) /* throws PermissaoException */ {
		if (!usuarioLogado.temPermissao(PermissaoUsuario.criacaoUsuarios)) {
			// throw new PermissaoException();
		}
		return (TecnicoAdministrativo) usuarioFactory.criaFuncionario(nome, data, TipoUsuario.TecnicoAdministrativo);
	}
}
