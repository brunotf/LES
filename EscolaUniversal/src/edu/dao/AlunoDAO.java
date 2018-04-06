package edu.dao;

import java.sql.SQLException;
import java.util.TreeSet;

import edu.model.Aluno;

public interface AlunoDAO {
	
	public void adicionar(Aluno a) throws SQLException;
	
	public TreeSet<Aluno> pesquisar() throws SQLException;

}
