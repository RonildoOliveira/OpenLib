package entity;

public class Professor extends Usuario {
	
	private int id;
	private String nome;
	private String codigo;
	private int id_usuario;

	public Professor() {
	
	}
	
	public Professor(String codigo, int id_usuario){
		this.codigo = codigo;
		this.id_usuario = id_usuario;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
}
