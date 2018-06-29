package dao;

import java.util.LinkedList;

import modelo.Jogo;

public interface JogoDAO {
	public void adicionar(Jogo jogo);
	
	public LinkedList<Jogo> pesquisar(String titulo);
	
}
