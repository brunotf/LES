package edu.model;

import java.io.Serializable;

public class Aluno implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7986838444753566168L;

	private int id;
	
	private String ra = "";
	
	private String nome = "";
	
	private int idade;
	
	private String sexo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Aluno: " + getNome() + " - ");
		sb.append("RA: " + getRa() +  " ");
		
		return sb.toString();
	}

}
