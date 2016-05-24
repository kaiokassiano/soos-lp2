package banco.dados;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.hospital.HospitalController;
import model.usuarios.Funcionario;

/**
 * Gerenciador de todos os dados do sistema, utiliza arquivos para realizar a
 * persistência dos dados entre sessões diferentes
 */
public class BancoDeDados {

	private static BancoDeDados instance = null;

	private final String directory = "system_data";

	// Boolean que representa se o sistema esta liberado
	private boolean sistemaLiberado;

	// Arquivo e stream representando o controller
	private ObjectOutputStream hospitalControllerOutput;
	private ObjectInputStream hospitalControllerInput;
	private final String hospitalControllerFile = "soos.dat";
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
		// initSistemaLiberado();
		initHospitalController();
	}

	/**
	 * Fecha o banco, salvando todos os dados que estavam na memória em arquivos
	 */
	public void fechar() {
		// fecharSistemaLiberado();
		fecharHospitalController();
	}

	private void initHospitalController() {
		try {
			hospitalControllerInput = new ObjectInputStream(
					new FileInputStream(directory + "/" + hospitalControllerFile));

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
			hospitalControllerOutput = new ObjectOutputStream(
					new FileOutputStream(directory + "/" + hospitalControllerFile));

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
