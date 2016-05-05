package data;

import java.io.*;

/**
 * Classe responsavel pelo armazenamento dos dados cadastrais;
 * 
 * @author Lucas Cordeiro
 *
 */

public class BancoDeDados {

	private static BancoDeDados instance = null;

	// Arquivo e streams do numero de cadastros
	private DataOutputStream qtdCadastrosOutput;
	private DataInputStream qtdCadastrosInput;
	private final String qtdCadastrosFile = "cadastros.dat";
	private int qtdCadastros = 0;

	private BancoDeDados() {
	}

	/**
	 * Instancia um novo objeto da Classe BancoDeDados.
	 * 
	 * @return
	 */

	public static BancoDeDados getInstance() {
		if (instance == null) {
			instance = new BancoDeDados();
		}
		return instance;
	}

	/**
	 * Inicia o banco de dados, puxando todos os dados para a memória.
	 */
	public void init() {
		initQtdCadastros();
	}

	/**
	 * Chama o metodo fecharQtdCadastros().
	 */
	public void fechar() {
		fecharQtdCadastros();
	}

	private void initQtdCadastros() {
		try {
			qtdCadastrosInput = new DataInputStream(new FileInputStream(qtdCadastrosFile));

			try {
				qtdCadastros = qtdCadastrosInput.readInt();
			} catch (EOFException e) {
				qtdCadastros = 1;
			}

			qtdCadastrosInput.close();
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado: " + qtdCadastrosFile);
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}

	private void fecharQtdCadastros() {
		try {
			qtdCadastrosOutput = new DataOutputStream(new FileOutputStream(qtdCadastrosFile));

			qtdCadastrosOutput.writeInt(qtdCadastros);

			qtdCadastrosOutput.close();
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado: " + qtdCadastrosFile);
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}

	/**
	 * Gera uma nova ID para a criacao de um novo cadastro.
	 * 
	 * @return A ID do proximo cadastro.
	 */

	public int getProximoId() {
		return qtdCadastros++;
	}

}
