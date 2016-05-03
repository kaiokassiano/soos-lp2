package model.farmacia;

import java.util.HashSet;

import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import validacao.medicamentos.*;

public class Medicamento {

	private String nome;
	private String tipo;
	private double preco;
	private int quantidade;
	private HashSet<CategoriaMedicamento> categorias;

	public Medicamento(String nome, String tipo, double preco, int quantidade, String categoriasRecebidas)
			throws NullStringException, LogicaException {

		ValidacaoMedicamentos.validaDadosMedicamento(nome, tipo, preco, quantidade);

		this.nome = nome;
		this.tipo = tipo;
		this.preco = preco;
		this.quantidade = quantidade;
		categorias = new HashSet<CategoriaMedicamento>();

		defineCategoriasMedicamento(categoriasRecebidas);
		defineDesconto();

	}

	private void defineCategoriasMedicamento(String categoriasAnalisar) throws NullStringException {
		String[] arrayCategorias = categoriasAnalisar.split(",");

		for (int i = 0; i < arrayCategorias.length; i++) {
			CategoriaMedicamento categoria = CategoriaMedicamento.converteString(arrayCategorias[i]);

			if (ValidacaoMedicamentos.validaCategoriasMedicamento(arrayCategorias)) {
				categorias.add(categoria);
			}
		}
	}

	public Object getInfoMedicamento(String requisicao) {

		Object objeto = null;

		switch (requisicao) {

		case "nome":
			return getNome();

		case "tipo":
			return getTipo();

		case "preco":
			return getPreco();

		case "quantidade":
			return getQuantidade();

		case "categorias":
			return getCategorias();

		}

		return objeto;

	}

	private String getNome() {
		return nome;
	}

	private double getPreco() {
		return preco;
	}

	private void setPreco(double preco) {
		this.preco = preco;
	}

	public void defineDesconto() {
		if (this.tipo.equals("generico")) {
			setPreco(this.preco * 0.6);
		}
	}

	private String getTipo() {
		return this.tipo;
	}

	private int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	private String getCategorias() {
		String stringRetorno = "";

		for (CategoriaMedicamento categoria : categorias) {
			stringRetorno = stringRetorno + categoria.toString();
		}

		return stringRetorno;

	}

}