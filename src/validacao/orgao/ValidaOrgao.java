package validacao.orgao;

import exceptions.dado.DadoInvalidoException;
import exceptions.logica.TipoSanguineoInvalidoException;

public class ValidaOrgao {

	public static void validaNomeOrgao(String nome) throws DadoInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new DadoInvalidoException("Nome do orgao nao pode ser vazio.");
		}

	}

	public static void validaTipoSanguineo(String tipoSanguineo) throws DadoInvalidoException, TipoSanguineoInvalidoException {
		if (tipoSanguineo == null || tipoSanguineo.trim().isEmpty()) {
			throw new DadoInvalidoException("Tipo sanguineo nao pode ser vazio.");
		}
		
		String tiposSanguineos = "A+A-B+B-AB+AB-O+O-";
		
		if (!tiposSanguineos.contains(tipoSanguineo)) {
			throw new TipoSanguineoInvalidoException("Tipo sanguineo invalido.");
		}
	}

}
