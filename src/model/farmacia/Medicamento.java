package model.farmacia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.TreeSet;

import exceptions.dado.NullStringException;
import exceptions.logica.LogicaException;
import exceptions.logica.OperacaoInvalidaException;
import validacao.medicamentos.ValidacaoMedicamentos;

public class Medicamento implements Comparable<Medicamento>, Serializable {

	private static final long serialVersionUID = 9144424243399198829L;

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

	public String getInfoMedicamento(String requisicao) {

		Object objeto = null;

		switch (requisicao) {

			case "nome":
				return getNome();
	
			case "tipo":
				return getTipo();
	
			case "preco":
				objeto = getPreco();
				return objeto.toString();
	
			case "quantidade":
				objeto = getQuantidade();
				return objeto.toString();
	
			case "categorias":
				return getCategorias();

		}

		return objeto.toString();

	}

	public void atualizaMedicamento(String atributo, String novoValor) throws OperacaoInvalidaException {
		
		
		
		switch (atributo) {
		
			case "preco":
				
				double valorDouble = Double.parseDouble(novoValor);
				
				if (this.tipo.equals("Generico")) {
					setPreco(valorDouble * 0.6);
					break;
				}
				setPreco(valorDouble);
				break;
			
			case "quantidade":
				int valorInteiro = Integer.valueOf(novoValor);
				setQuantidade(valorInteiro);
				break;
				
			case "nome":
				throw new OperacaoInvalidaException("Nome do medicamento nao pode ser alterado.");
				
			case "tipo":
				throw new OperacaoInvalidaException("Tipo do medicamento nao pode ser alterado.");
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
			treeset.add(categoriaMedicamento.getCategoria());
		}
		
		return String.join(",", treeset);
		
	}
	
	@Override
	public String toString() {
		return String.format("Medicamento %s: %s - Preco: R$ %.2f - Disponivel: %d - Categorias: %s", this.getTipo(),this.getNome(), this.getPreco(), this.getQuantidade(), this.getCategorias());
	}

	@Override
	public int compareTo(Medicamento medicamento) {
		if (medicamento instanceof Medicamento) {
			Medicamento novoMedicamento = (Medicamento) medicamento;
			if (this.getPreco() < novoMedicamento.getPreco()) {
				return -1;
			} else if (this.getPreco() > novoMedicamento.getPreco()) {
				return 1;
			} else {
				return 0;
			}
		}
		return -1;
	}

}