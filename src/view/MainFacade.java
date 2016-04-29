package view;

import controller.hospital.HospitalController;

public class MainFacade {

	private HospitalController hospital;
	
	public MainFacade() {
		hospital = new HospitalController();
	}
}
