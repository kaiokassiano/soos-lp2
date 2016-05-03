package model.farmacia;

import java.util.HashSet;

import exceptions.dado.NullStringException;
import exceptions.logica.StringVaziaException;

public class Medicamento {

	private final String GENERICO = "generico";
	private final String REFERENCIA = "referencia";
	private String nome;
	private double preco;
	private static HashSet<CategoriaMedicamento> categoriasMedicamento;
	
	public Medicamento(String nome, String tipo, double preco, int quantidade, String categorias) throws NullStringException, StringVaziaException {
		
		if (nome == null) {
			throw new NullStringException("Nome nao pode ser nulo");
		} else if (nome.trim().isEmpty()) {
			throw new StringVaziaException("Nome nao pode ser vazio");
		} else if (tipo == null) {
			throw new NullStringException("Tipo nao pode ser nulo");
		} else if (tipo.trim().isEmpty()) {
			throw new StringVaziaException("Tipo nao pode ser vazio");
		} 
		
		if (tipo.equals(GENERICO)) {
			defineDesconto();
		}
		
	}
	
	public void defineCategoriasMedicamento(String categorias) throws NullStringException {
		String[] arrayCategorias = categorias.split(",");
		
		for (int i = 0; i < arrayCategorias.length; i++) {
			CategoriaMedicamento categoria = CategoriaMedicamento.converteString(arrayCategorias[i]);
			
			if (categoria == null) {
				throw new NullStringException("Categoria invalida");
			} else {
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
		setPreco(this.preco * 0.6);
	}
	
}