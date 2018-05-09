package controller;

import java.util.LinkedList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Animal;

@ManagedBean
@ViewScoped
public class AnimalMB {

	private LinkedList<Animal> lista = new LinkedList<>();
	private Animal animal = new Animal();

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

	public void adicionar() {
		lista.add(animal);
		FacesMessage msg = new FacesMessage("Animal adicionado.", "O animal " + animal.getNome() + " foi adicionado.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		animal = new Animal();
	}

}