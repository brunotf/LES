package modelo;

public class Jogo {
	private int id;
	private String titulo;
	private String dificuldade;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Título: " + titulo + '\n');
		sb.append("Dificuldade: " + dificuldade + '\n');
		return sb.toString();
	}
}
