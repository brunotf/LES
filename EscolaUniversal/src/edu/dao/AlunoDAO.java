package edu.dao;

import java.util.LinkedList;

import edu.model.Aluno;

public interface AlunoDAO {
	public void adicionar(Aluno a);

	public LinkedList<Aluno> pesquisar();

	public void atualizar(Aluno a);

	public void excluir(Aluno a);
}
