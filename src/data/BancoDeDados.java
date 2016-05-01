package data;

import java.io.*;

public class BancoDeDados {
	
	private static BancoDeDados instance = null;
	
	// Arquivo e streams do numero de cadastros
	private DataOutputStream qtdCadastrosOutput;
	private DataInputStream qtdCadastrosInput;
	private String qtdCadastrosFile = "cadastros.txt";

	private BancoDeDados() {
	}
	
	public static BancoDeDados getInstance() {
		if (instance == null) {
			instance = new BancoDeDados();
		}
		return instance;
	}
	
	public void init() {
		try {
			qtdCadastrosInput = new DataInputStream(new FileInputStream(qtdCadastrosFile));
			qtdCadastrosOutput = new DataOutputStream(new FileOutputStream(qtdCadastrosFile));
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado: " + qtdCadastrosFile);
		}
	}
	
	public void fechar() {
		try {
			qtdCadastrosInput.close();
			qtdCadastrosOutput.close();
		}
		catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	public int getProximoId() {
		int cadastros = 0;
		
		try {
			cadastros = qtdCadastrosInput.readInt();
		}
		catch (IOException e) {
			if (cadastros == 0) {
				cadastros = 1;
			}
		}
		
		aumentaQtdCadastros(cadastros + 1);
		
		return cadastros;
	}
	
	private void aumentaQtdCadastros(int quantidade) {
		try {
			qtdCadastrosOutput.writeInt(quantidade);
		}
		catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
}
