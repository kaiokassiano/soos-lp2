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
	private HashSet<CategoriaMedicamento> categoriasMedicamento;

	public Medicamento(String nome, String tipo, double preco, int quantidade, String categorias)
			throws NullStringException, LogicaException {

		ValidacaoMedicamentos.validaDadosMedicamento(nome, tipo, preco, quantidade);

		this.nome = nome;
		this.tipo = tipo;
		this.preco = preco;
		this.quantidade = quantidade;
		categoriasMedicamento = new HashSet<CategoriaMedicamento>();
		
		defineCategoriasMedicamento(categorias);
		defineDesconto();

	}

	private void defineCategoriasMedicamento(String categorias) throws NullStringException {
		String[] arrayCategorias = categorias.split(",");

		for (int i = 0; i < arrayCategorias.length; i++) {
			CategoriaMedicamento categoria = CategoriaMedicamento.converteString(arrayCategorias[i]);

			if (ValidacaoMedicamentos.validaCategoriasMedicamento(arrayCategorias)) {
				categoriasMedicamento.add(categoria);
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void defineDesconto() {
		if (this.tipo.equals("generico")) {
			setPreco(this.preco * 0.6);
		}
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}