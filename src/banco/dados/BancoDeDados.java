package banco.dados;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import model.usuarios.Funcionario;

/**
 * Gerenciador de todos os dados do sistema, utiliza arquivos
 * para realizar a persistência dos dados entre sessões diferentes
 */
public class BancoDeDados {
	
	private static BancoDeDados instance = null;
	
	// Arquivo e streams do numero de cadastros
	private DataOutputStream qtdCadastrosOutput;
	private DataInputStream qtdCadastrosInput;
	private final String qtdCadastrosFile = "data/cadastros.dat";
	private int qtdCadastros = 0;
	
	// Arquivo e stream representando se o sistema foi liberado
	private DataOutputStream sistemaLiberadoOutput;
	private DataInputStream sistemaLiberadoInput;
	private final String sistemaLiberadoFile = "data/sistema_liberado.dat";
	private boolean sistemaLiberado;
	
	// Usuário logado no sistema
	private Funcionario usuarioLogado;

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
		initSistemaLiberado();
	}
	
	/**
	 * Fecha o banco, salvando todos os dados que estavam na memória
	 * em arquivos
	 */
	public void fechar() {
		fecharQtdCadastros();
		fecharSistemaLiberado();
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
			qtdCadastros = 1;
			
			// cria novo arquivo
			File f = new File(qtdCadastrosFile);

			f.getParentFile().mkdirs(); 
			try {
				f.createNewFile();
			} catch (IOException e1) {
				System.err.println("IOException: " + e.getMessage());
			}
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
			// cria novo arquivo
			File f = new File(qtdCadastrosFile);

			f.getParentFile().mkdirs(); 
			try {
				f.createNewFile();
			} catch (IOException e1) {
				System.err.println("IOException: " + e.getMessage());
			}
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

	public void initSistemaLiberado() {
		try {
			sistemaLiberadoInput = new DataInputStream(new FileInputStream(sistemaLiberadoFile));
			
			try {
				sistemaLiberado = sistemaLiberadoInput.readBoolean();
			}
			catch (EOFException e) {
				sistemaLiberado = false;
			}
			
			sistemaLiberadoInput.close();
		}
		catch (FileNotFoundException e) {
			File f = new File(sistemaLiberadoFile);

			f.getParentFile().mkdirs(); 
			try {
				f.createNewFile();
			} catch (IOException e1) {
				System.err.println("IOException: " + e.getMessage());
			}
		}
		catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	public void fecharSistemaLiberado() {
		try {
			sistemaLiberadoOutput = new DataOutputStream(new FileOutputStream(sistemaLiberadoFile));
			
			sistemaLiberadoOutput.writeBoolean(sistemaLiberado);
		}
		catch (FileNotFoundException e) {
			File f = new File(sistemaLiberadoFile);

			f.getParentFile().mkdirs(); 
			try {
				f.createNewFile();
			} catch (IOException e1) {
				System.err.println("IOException: " + e.getMessage());
			}
		}
		catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	public boolean isSistemaLiberado() {
		return sistemaLiberado;
	}
	
	public void setSistemaLiberado(boolean status) {
		this.sistemaLiberado = status;
	}

	public void setUsuarioLogado(Funcionario funcionario) {
		this.usuarioLogado = funcionario;
	}

	public Funcionario getUsuarioLogado() {
		return this.usuarioLogado;
	}
}
