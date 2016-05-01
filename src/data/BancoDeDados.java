package data;

import java.io.*;

public class BancoDeDados {
	
	private static BancoDeDados instance = null;
	
	// Arquivo e streams do numero de cadastros
	private DataOutputStream qtdCadastrosOutput;
	private DataInputStream qtdCadastrosInput;
	private final String qtdCadastrosFile = "cadastros.dat";
	private int qtdCadastros = 0;

	private BancoDeDados() {
	}
	
	public static BancoDeDados getInstance() {
		if (instance == null) {
			instance = new BancoDeDados();
		}
		return instance;
	}
	
	public void init() {
		initQtdCadastros();
	}
	
	public void fechar() {
		fecharQtdCadastros();
	}
	
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
	
	public int getProximoId() {
		return qtdCadastros++;
	}

}
