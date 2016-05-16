package validacao.prontuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.dado.DadoInvalidoException;
import exceptions.dado.NullStringException;
import exceptions.logica.DataInvalidaException;
import exceptions.logica.NumeroNegativoException;
import exceptions.logica.StringVaziaException;

public class ValidacaoProntuarios {
	
	public static boolean validaDadosProntuario(String nome, String dataNascimento, double peso, String tipoSanguineo) throws StringVaziaException, NumeroNegativoException, DataInvalidaException, DadoInvalidoException{
		
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo");
		}
		
		else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome do paciente nao pode ser vazio.");
			
		} else if (peso < 0.0) {
			throw new NumeroNegativoException("Peso do paciente nao pode ser negativo.");
		
			
		} else if (!contemTipoSanguineo(tipoSanguineo)) {
			throw new DadoInvalidoException("Tipo sanguineo invalido.");
			
		} try {
			parseData(dataNascimento);
		} catch (Exception e) {
			throw new DataInvalidaException(e.getMessage());
		
		}
		return true;
	}
	
		private static boolean contemTipoSanguineo(String tipoSanguineo) {
			String[] tiposSanguineos = new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }; // TODO
			for (int i = 0; i < tiposSanguineos.length; i++) {
				if (tipoSanguineo.toUpperCase().equals(tiposSanguineos[i])) {
					return true;
				}
			}
			return false;
		}
	
		private static LocalDate parseData(String dataNascimento) throws DataInvalidaException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			return LocalDate.parse(dataNascimento, formato);
		}
		catch (Exception e) {
			throw new DataInvalidaException("Data invalida.");
		}
	}

}
