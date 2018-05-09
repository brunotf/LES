package controller;

import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Animal;

@ManagedBean
@ViewScoped
public class AnimalMB {

	private LinkedList<Animal> lista = new LinkedList<>();
	private Animal animal;;

	public LinkedList<Animal> getLista() {
		return lista;
	}

	public void setLista(LinkedList<Animal> lista) {
		this.lista = lista;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String adicionar() {
		System.out.println("Seu animal foi adicionado com sucessi" + animal.toString());
		lista.add(animal);
		return null;
	}

}