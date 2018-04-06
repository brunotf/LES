package edu.dao;

import java.sql.SQLException;
import java.util.HashSet;

import edu.model.Aluno;

public interface AlunoDAO {
	
	public void adicionar(Aluno a) throws SQLException;
	
	public HashSet<Aluno> pesquisar() throws SQLException;

}
