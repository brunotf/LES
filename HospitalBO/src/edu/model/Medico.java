package edu.model;

import java.io.Serializable;

public class Medico implements Serializable {
	private static final long serialVersionUID = -3897598776882103868L;
	private long id;
	private String nome = "";
	private String crm = "";
	private String especialidade = "";
	String dtAdmissao;
	private String turno = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(String dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Dr(a). " + getNome() + "\n");
		sb.append("CRM : " + getCrm());
		return sb.toString();
	}
}
