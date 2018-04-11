package edu.dao;

import edu.model.Medico;

import java.sql.SQLException;
import java.util.LinkedHashSet;

public interface MedicoDAO {
	public void adicionar(Medico m) throws SQLException;

	public void atualizar(Medico m) throws SQLException;

	public void excluir(Medico m) throws SQLException;

	public LinkedHashSet<Medico> pesquisar(String txtNome) throws SQLException;

	public long proximoId() throws SQLException;
	
}
