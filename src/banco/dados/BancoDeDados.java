package banco.dados;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import controller.hospital.HospitalController;
import model.usuarios.Funcionario;

/**
 * Gerenciador de todos os dados do sistema, utiliza arquivos para realizar a
 * persistência dos dados entre sessões diferentes
 */
public class BancoDeDados {

	private static BancoDeDados instance = null;
	
	private static final String directory = "system_data";

	// Arquivo e streams do numero de cadastros
	private DataOutputStream qtdCadastrosOutput;
	private DataInputStream qtdCadastrosInput;
	private final String qtdCadastrosFile = "cadastros.dat";
	private int qtdCadastros = 0;

	// Arquivo e stream representando se o sistema foi liberado
	private DataOutputStream sistemaLiberadoOutput;
	private DataInputStream sistemaLiberadoInput;
	private final String sistemaLiberadoFile = "sistema_liberado.dat";
	private boolean sistemaLiberado;

	// Arquivo e stream representando o controller
	private ObjectOutputStream hospitalControllerOutput;
	private ObjectInputStream hospitalControllerInput;
	private final String hospitalControllerFile = "hospital_controller.dat";
	private HospitalController hospitalController;
	
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
	 * Inicia o banco, colocando na memória todos os dados que estavam guardados
	 * nos arquivos
	 */
	public void init() {
		initQtdCadastros();
		initSistemaLiberado();
		initHospitalController();
	}

	/**
	 * Fecha o banco, salvando todos os dados que estavam na memória em arquivos
	 */
	public void fechar() {
		fecharQtdCadastros();
		fecharSistemaLiberado();
		fecharHospitalController();
	}

	/**
	 * Carrega os dados referentes a quantidade de cadastros realizados
	 */
	private void initQtdCadastros() {
		try {
			qtdCadastrosInput = new DataInputStream(new FileInputStream(directory + "/" + qtdCadastrosFile));

			try {
				qtdCadastros = qtdCadastrosInput.readInt();
			} catch (EOFException e) {
				qtdCadastros = 1;
			}

			qtdCadastrosInput.close();
		} catch (FileNotFoundException e) {
			qtdCadastros = 1;

			// cria novo arquivo
			File f = new File(directory + "/" + qtdCadastrosFile);

			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e1) {
				System.err.println("IOException: " + e.getMessage());
			}
		} catch (IOException e) {
			
		}
	}

	/**
	 * Salva os dados referentes a quantidade de cadastros realizados
	 */
	private void fecharQtdCadastros() {
		try {
			qtdCadastrosOutput = new DataOutputStream(new FileOutputStream(directory + "/" + qtdCadastrosFile));

			qtdCadastrosOutput.writeInt(qtdCadastros);

			qtdCadastrosOutput.close();
		} catch (FileNotFoundException e) {
			// cria novo arquivo
			File f = new File(directory + "/" + qtdCadastrosFile);

			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e1) {
				System.err.println("IOException: " + e.getMessage());
			}
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}

	/**
	 * Pega a quantidade atual de cadastros a ser utilizada para a criação de
	 * matriculas, e atualiza essa quantidade
	 * 
	 * @return Quantidade de cadastros realizados
	 */
	public int getProximoId() {
		return qtdCadastros++;
	}

	private void initHospitalController() {
		try {
			hospitalControllerInput = new ObjectInputStream(new FileInputStream(directory + "/" + hospitalControllerFile));
			
			try {
				hospitalController = (HospitalController) hospitalControllerInput.readObject();
			} catch (ClassNotFoundException | EOFException e) {
				hospitalController = new HospitalController();
			}
			
			hospitalControllerInput.close();
		} catch (FileNotFoundException e) {
			hospitalController = new HospitalController();
			
			File f = new File(directory + "/" + hospitalControllerFile);
			
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e2) {
				System.err.println("IOException: " + e.getMessage());
			}
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	private void fecharHospitalController() {
		try {
			hospitalControllerOutput = new ObjectOutputStream(new FileOutputStream(directory + "/" + hospitalControllerFile));
			
			hospitalControllerOutput.writeObject(hospitalController);

			hospitalControllerOutput.close();
		} catch (FileNotFoundException e) {
			File f = new File(directory + "/" + hospitalControllerFile);

			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e1) {
				System.err.println("IOException: " + e.getMessage());
			}
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	public HospitalController getHospitalController() {
		return this.hospitalController;
	}
	
	private void initSistemaLiberado() {
		try {
			sistemaLiberadoInput = new DataInputStream(new FileInputStream(directory + "/" + sistemaLiberadoFile));

			try {
				sistemaLiberado = sistemaLiberadoInput.readBoolean();
			} catch (EOFException e) {
				sistemaLiberado = false;
			}

			sistemaLiberadoInput.close();
		} catch (FileNotFoundException e) {
			File f = new File(directory + "/" + sistemaLiberadoFile);

			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e1) {
				System.err.println("IOException: " + e.getMessage());
			}
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}

	private void fecharSistemaLiberado() {
		try {
			sistemaLiberadoOutput = new DataOutputStream(new FileOutputStream(directory + "/" + sistemaLiberadoFile));

			sistemaLiberadoOutput.writeBoolean(sistemaLiberado);
		} catch (FileNotFoundException e) {
			File f = new File(directory + "/" + sistemaLiberadoFile);

			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e1) {
				System.err.println("IOException: " + e.getMessage());
			}
		} catch (IOException e) {
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
