package model.procedimentos;

import java.io.Serializable;
import java.util.HashMap;

import banco.dados.BancoDeDados;
import exceptions.dado.DadoInvalidoException;
import exceptions.logica.LogicaException;
import exceptions.logica.PermissaoException;
import factory.procedimento.ProcedimentoFactory;
import model.prontuarios.Prontuario;
import model.usuarios.Funcionario;
import model.usuarios.PermissaoFuncionario;

/**
 * Model gerenciador dos procedimentos do sistema
 */
public class GerenciadorProcedimentos implements Serializable {

	private static final long serialVersionUID = 7351303124526973570L;
	private ProcedimentoFactory procedimentoFactory;

	/**
	 * Construtor do gerenciador de procedimentos
	 */
	public GerenciadorProcedimentos() {
		this.procedimentoFactory = new ProcedimentoFactory();
	}

	/**
	 * Realiza um procedimento em um paciente
	 * 
	 * @param prontuario
	 *            - prontuario do paciente
	 * @param procedimentoSolicitado
	 *            - procedimento desejado
	 * @param precoMedicamentos
	 *            - preco dos medicamentos
	 * @param params
	 *            - parametros adicionais (nome do medico, nome do orgao)
	 * @throws LogicaException
	 * @throws DadoInvalidoException
	 */
	public void realizaProcedimento(Prontuario prontuario, String procedimentoSolicitado, double precoMedicamentos,
			HashMap<String, Object> params) throws LogicaException, DadoInvalidoException {
		Funcionario usuarioLogado = BancoDeDados.getInstance().getUsuarioLogado();
		if (!usuarioLogado.temPermissao(PermissaoFuncionario.CRIACAO_PROCEDIMENTOS)) {
			throw new PermissaoException(
					"O funcionario " + usuarioLogado.getNome() + " nao tem permissao para realizar procedimentos.");
		}

		Procedimento procedimento = procedimentoFactory.criaProcedimento(procedimentoSolicitado);
		procedimento.realizaProcedimento(prontuario, precoMedicamentos, params);

		prontuario.adicionaProcedimento(procedimento);
	}
}
