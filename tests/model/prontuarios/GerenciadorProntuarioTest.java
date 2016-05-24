package model.prontuarios;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.hospital.HospitalController;
import exceptions.logica.PacienteNaoCadastradoException;

public class GerenciadorProntuarioTest {

	private GerenciadorProntuario gerenciadorProntuario;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HospitalController hospitalController = new HospitalController();
		
		String matriculaDiretor = hospitalController.liberaSistema("c041ebf8", "Diretor", "07/11/1967");
		hospitalController.login(matriculaDiretor, "19671201");
	}
	
	@Before
	public void setUp() throws Exception {
		gerenciadorProntuario = new GerenciadorProntuario();
	}

	@Test
	public void testExportaFichaPaciente() {
		try {
			gerenciadorProntuario.cadastraPaciente("Lucas", "17/11/1997", 70, "masculino", "masculino", "A+");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
		
		String id = gerenciadorProntuario.getInfoPaciente("Lucas", "id");
		
		try {
			gerenciadorProntuario.exportaFichaPaciente(id);
		} catch (PacienteNaoCadastradoException e) {
			fail();
		}
	}

}
