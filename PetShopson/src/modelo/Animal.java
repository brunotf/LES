package modelo;

import java.io.Serializable;
import java.util.Date;

public class Animal implements Serializable {
	private static final long serialVersionUID = 8299152977089092191L;

	private int id;
	private String nome;
	private Date nascimento;
	private float peso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return nome;
	}

}
