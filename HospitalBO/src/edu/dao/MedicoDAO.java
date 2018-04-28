package edu.dao;

import edu.model.Medico;

import java.util.LinkedHashSet;

public interface MedicoDAO {
	public void adicionar(Medico m);

	public void atualizar(Medico m);

	public void excluir(Medico m);

	public LinkedHashSet<Medico> pesquisar(String txtNome);

	public long proximoId();
	
}
