package dao;

import java.util.LinkedList;

import modelo.Jogo;

public interface JogoDAO {
	public void adicionar(Jogo j);
	
	public LinkedList<Jogo> pesquisar(String titulo);
	
}
