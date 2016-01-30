package entity;

public class Autor extends Usuario {
	
	private String nome;
	private int id;
	private int id_usuario;
	
	public Autor() {

	}
	
	public Autor(String nome,int id_usuario) {
		this.nome = nome;
		this.id_usuario = id_usuario;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	
}
