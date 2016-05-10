package banco.orgaos;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class BancoDeOrgaos {

	private TreeMap<String, ArrayList<Orgao>> bancoDeOrgaos;

	public BancoDeOrgaos() {
		bancoDeOrgaos = new TreeMap<String, ArrayList<Orgao>>();
	}

	public String obterOrgaoPeloNome(String nome, String tipoSanguineo) {

		for (Map.Entry<String, ArrayList<Orgao>> entry : bancoDeOrgaos.entrySet()) {

			if (entry.getKey().equals(nome)) {

				// TODO refatorar essa bagaça

				for (int i = 0; i < entry.getValue().size(); i++) {

					if (entry.getValue().get(i).getTipoSanguineo().equals(tipoSanguineo)) {

						return entry.getValue().get(i).toString();

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

		for (Map.Entry<String, ArrayList<Orgao>> entry : bancoDeOrgaos.entrySet()) {
			retorno += entry.getValue().size();
		}

		return retorno;
	}

	public boolean temOrgao(String nome, String tipoSanguineo) {

		for (Map.Entry<String, ArrayList<Orgao>> entry : bancoDeOrgaos.entrySet()) {

			if (entry.getKey().equals(nome)) {

				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).getTipoSanguineo().equals(tipoSanguineo)) {
						entry.getValue().remove(i);
						return true;
					}
				}
			}
		}

		return false;
	}

}
