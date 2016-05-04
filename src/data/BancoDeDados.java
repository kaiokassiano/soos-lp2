package data;

import java.io.*;

/**
 * Gerenciador de todos os dados do sistema, utiliza arquivos
 * para realizar a persistência dos dados entre sessões diferentes
 */
public class BancoDeDados {
	
	private static BancoDeDados instance = null;
	
	// Arquivo e streams do numero de cadastros
	private DataOutputStream qtdCadastrosOutput;
	private DataInputStream qtdCadastrosInput;
	private final String qtdCadastrosFile = "cadastros.dat";
	private int qtdCadastros = 0;

	/**
	 * Construtor do banco
	 */
	private BancoDeDados() {
	}
	
	/**
	 * Retorna a única instância do objeto (singleton)
	 * 
	 * @return Instância do objeto BancoDeDados
	 */
	public static BancoDeDados getInstance() {
		if (instance == null) {
			instance = new BancoDeDados();
		}
		return instance;
	}
	
	/**
	 * Inicia o banco, colocando na memória todos os dados que estavam
	 * guardados nos arquivos
	 */
	public void init() {
		initQtdCadastros();
	}
	
	/**
	 * Fecha o banco, salvando todos os dados que estavam na memória
	 * em arquivos
	 */
	public void fechar() {
		fecharQtdCadastros();
	}
	
	/**
	 * Carrega os dados referentes a quantidade de cadastros realizados
	 */
	private void initQtdCadastros() {
		try {
			qtdCadastrosInput = new DataInputStream(new FileInputStream(qtdCadastrosFile));
			
			try {
				qtdCadastros = qtdCadastrosInput.readInt();
			}
			catch (EOFException e) {
				qtdCadastros = 1;
			}
			
			qtdCadastrosInput.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado: " + qtdCadastrosFile);
		}
		catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	/**
	 * Salva os dados referentes a quantidade de cadastros realizados
	 */
	private void fecharQtdCadastros() {
		try {
			qtdCadastrosOutput = new DataOutputStream(new FileOutputStream(qtdCadastrosFile));
			
			qtdCadastrosOutput.writeInt(qtdCadastros);
			
			qtdCadastrosOutput.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado: " + qtdCadastrosFile);
		}
		catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	/**
	 * Pega a quantidade atual de cadastros a ser utilizada para a criação
	 * de matriculas, e atualiza essa quantidade
	 * 
	 * @return Quantidade de cadastros realizados
	 */
	public int getProximoId() {
		return qtdCadastros++;
	}

}
