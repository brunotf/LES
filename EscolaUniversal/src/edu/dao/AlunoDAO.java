package edu.dao;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import edu.model.Aluno;

public interface AlunoDAO {

	public void adicionar(Aluno a) throws SQLException;

	public LinkedHashSet<Aluno> pesquisar() throws SQLException;

	public void atualizar(Aluno a) throws SQLException;

	public void excluir(Aluno a) throws SQLException;
}
