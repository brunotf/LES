package edu.dao;

import edu.model.Medico;

import java.sql.SQLException;
import java.util.HashSet;

public interface MedicoDAO {
	public void adicionar(Medico m) throws SQLException;

	public void atualizar(Medico m) throws SQLException;

	public void excluir(Medico m) throws SQLException;

	public HashSet<Medico> consultar() throws SQLException;

	public long proximoId() throws SQLException;
	
}
