package banco.orgaos;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class BancoDeOrgaos {

	private TreeMap<String, ArrayList<String>> bancoDeOrgaos;

	public BancoDeOrgaos() {
		bancoDeOrgaos = new TreeMap<String, ArrayList<String>>();
	}

	public String obterOrgaoPeloNome(String nome, String tipoSanguineo) {

		for (Map.Entry<String, ArrayList<String>> entry : bancoDeOrgaos.entrySet()) {

			if (entry.getKey().equals(nome)) {

				// TODO refatorar essa bagaça

				for (int i = 0; i < entry.getValue().size(); i++) {

					if (entry.getValue().get(i).equals(tipoSanguineo)) {

						Orgao orgaoRetorno = new Orgao(nome, tipoSanguineo);
						return orgaoRetorno.toString();

					}
				}
			}
		}
		return null;
	}

	public int getQtdOrgaos(String nome) {
		return bancoDeOrgaos.get(nome).size();
	}

	public int getQtdTotal() {
		int retorno = 0;

		for (Map.Entry<String, ArrayList<String>> entry : bancoDeOrgaos.entrySet()) {
			retorno += entry.getValue().size();
		}

		return retorno;
	}

	public boolean temOrgao(String nome, String tipoSanguineo) {

		for (Map.Entry<String, ArrayList<String>> entry : bancoDeOrgaos.entrySet()) {

			if (entry.getKey().equals(nome)) {

				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).equals(tipoSanguineo)) {
						entry.getValue().remove(i);
						return true;
					}
				}
			}
		}

		return false;
	}

}
