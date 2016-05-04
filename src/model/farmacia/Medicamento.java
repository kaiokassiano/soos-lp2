package model.farmacia;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.TreeSet;

import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import validacao.medicamentos.ValidacaoMedicamentos;

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
		defineTipo(tipo);
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

	public void atualizaMedicamento(String atributo, Double novoValor) {
		
		int valorInteiro = novoValor.intValue();
		
		switch (atributo) {
		
			case "preco":
				
				if (this.tipo.equals("Generico")) {
					setPreco(novoValor * 0.6);
					break;
				}
				setPreco(novoValor);
				break;
			
			case "quantidade":
				setQuantidade(valorInteiro);
				break;
				
		}
		
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
		if (this.tipo.equals("Generico")) {
			setPreco((this.preco * 60) / 100);
		}
	}

	private String getTipo() {
		return this.tipo;
	}

	private void defineTipo(String tipo) {
		if (tipo.equals("generico")) {
			this.tipo = "Generico";
		} else {
			this.tipo = "de Referencia";
		}
	}
	
	private int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	private String getCategorias() {
		
		TreeSet<String> treeset = new TreeSet<String>();
		
		for (CategoriaMedicamento categoriaMedicamento : categorias) {
			treeset.add(categoriaMedicamento.toString());
		}
		
		return String.join(",", treeset);
		
	}

}